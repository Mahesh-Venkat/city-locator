package com.backbase.citylocator.transferobjects;

import java.util.List;

public class Cities {
    private List<City> cities;

    private static final Cities ourInstance = new Cities();

    public static Cities getInstance() {
        return ourInstance;
    }

    private Cities() {
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
