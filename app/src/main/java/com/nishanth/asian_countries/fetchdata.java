package com.nishanth.asian_countries;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class fetchdata  extends AsyncTask<Void, Void, Void> {

    ArrayList<Country> countriesinfthdta=new ArrayList<>();

    String data="";
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url=new URL("https://restcountries.eu/rest/v2/region/asia");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();

            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while(line!=null)
            {

                line=reader.readLine();
                data=data+line;
            }


            JSONArray ja=new JSONArray(data);

            for(int i=0;i<ja.length();i++)
            {
                JSONObject object=(JSONObject)ja.get(i);
                String name= (String) object.get("name");

                String capital= (String) object.get("capital");
                String flag= (String) object.get("flag");
                String region= (String) object.get("region");
                String subregion=(String) object.get("subregion");
                String population =String.valueOf(object.get("population"));
                ArrayList<String> borders= new ArrayList<>();
                JSONArray bordersja= (JSONArray) object.get("borders");
                for(int j=0;j<bordersja.length();j++)
                {


                   borders.add((String) bordersja.get(j));
                }

                ArrayList<String> languages=new ArrayList<>();

                JSONArray languagesja=(JSONArray) object.get("languages");

                for(int j=0;j<languagesja.length();j++)
                {
                    JSONObject languagesjo=(JSONObject)languagesja.get(i);

                    languages.add((String) languagesjo.get("name"));

                }

            Country country=new Country(name, capital, flag, region, subregion, population, borders, languages);

                countriesinfthdta.add(country);


            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.setCountries(countriesinfthdta);
    }
}
