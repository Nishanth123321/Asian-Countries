package com.nishanth.asian_countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


   static  ArrayList<Country> countries=new ArrayList<>();

   private RecyclerView recycler;
   private  CountryAdapter adapter;

    public static ArrayList<Country> getCountries() {
        return countries;
    }

    public static void setCountries(ArrayList<Country> countries) {
        MainActivity.countries = countries;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler=findViewById(R.id.recycler);


        fetchdata process=new fetchdata();
        process.execute();

        adapter=new CountryAdapter(countries, MainActivity.this);
        recycler.setAdapter(adapter);
        recycler.hasFixedSize();

        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
}