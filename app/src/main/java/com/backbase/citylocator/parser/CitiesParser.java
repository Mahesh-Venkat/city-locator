package com.backbase.citylocator.parser;

import android.app.Activity;

import com.backbase.citylocator.transferobjects.City;
import com.backbase.citylocator.utils.CityLocatorUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitiesParser {
    private static final String TAG_PLANS_LIST = "plans";
    private static final String CITIES_JSON_FILE_NAME = "cities.json";

    private Activity activity;

    public CitiesParser(Activity mActivity) {
        this.activity = mActivity;
    }

    public List<City> getCityList() {
        String jsonString = CityLocatorUtils.readRawTextFile(activity, CITIES_JSON_FILE_NAME);

        Type type = new TypeToken<List<City>>() {
        }.getType();

        Gson gson = new Gson();
        List<City> cities = gson.fromJson(jsonString, type);

        //cities.stream().sorted(Comparator.comparing(City::getName)); Can be done this way in newer JAVA versions
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase());
            }
        });

        return cities;
    }
}
