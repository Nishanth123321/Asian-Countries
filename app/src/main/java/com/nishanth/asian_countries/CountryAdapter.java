package com.nishanth.asian_countries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewAdapter>{

    private ArrayList<Country> countries;
    private Context context;

    public CountryAdapter(ArrayList<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.country_itemlayout, parent, false);
        return new CountryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewAdapter holder, int position) {

        Country country=countries.get(position);

        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.population.setText(country.getPopulation());
        holder.region.setText(country.getRegion());
        holder.subregion.setText(country.getSubregion());
        String borders="";
        String languages="";
       ArrayList<String> borderlist=country.getBorders();
        ArrayList<String> languagelist=country.getLanguages();

        for(int i=0;i<borderlist.size();i++)
        {
            borders=borders+borderlist.get(i);

            if(i!=borderlist.size()-1)
            {
                borders=borders+", ";
            }

        }
        holder.borders.setText(borders);
        for(int i=0;i<languagelist.size();i++)
        {
            languages=languages+languagelist.get(i);

            if(i!=languagelist.size()-1)
            {
                languages=languages+", ";
            }

        }
        holder.languages.setText(languages);
        Picasso.get().load(country.getFlag()).into(holder.flag);


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CountryViewAdapter extends RecyclerView.ViewHolder {

        TextView name, capital,  region, subregion, population, borders, languages;

        ImageView flag;
        public CountryViewAdapter(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
           capital=itemView.findViewById(R.id.capital);
            region=itemView.findViewById(R.id.region);
            subregion=itemView.findViewById(R.id.subregion);
            population=itemView.findViewById(R.id.population);
            borders=itemView.findViewById(R.id.borders);
            languages=itemView.findViewById(R.id.languages);
            flag=itemView.findViewById(R.id.flag);



        }
    }
}
