package com.backbase.citylocator;

import com.backbase.citylocator.adapters.CitiesAdapter;
import com.backbase.citylocator.transferobjects.City;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CitiesAdapterTest {
    private List<City> mockedList;
    private CitiesAdapter citiesAdapter;

    @Before
    public void setUp() {

        mockedList = new ArrayList<>();

        mockedList.add(buildCity("Amsterdam", 2759794, "NL", 52.374031,4.88969));
        mockedList.add(buildCity("Fabara", 6362794, "ES", 41.201778,0.16673));
        mockedList.add(buildCity("Mumbai", 1275339, "IN", 19.01441,72.847939));
        mockedList.add(buildCity("Am Dammsteg", 2956627, "DE", 51.183331,6.85));
        mockedList.add(buildCity("Fabbrico", 3177317, "IT", 44.869469,10.81159));
        mockedList.add(buildCity("Prague", 3067696, "CZ", 50.088039,14.42076));
        mockedList.add(buildCity("Faanui", 4034551, "PF", -16.48333,-151.75));
        mockedList.add(buildCity("Vienna", 2761369, "AT", 48.208488,16.37208));

        Collections.sort(mockedList, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase());
            }
        });

        citiesAdapter = new CitiesAdapter(null, mockedList);
    }

    private City buildCity(String cityName, long id, String countryName, double latitude, double longitude) {
        City city = new City();
        city.setName(cityName);
        city.set_id(id);
        city.setCountry(countryName);
        city.setCoordinates(latitude,longitude);

        return city;
    }

    @Test
    public void testValidEntryWithSerachCirteriaA() {
        List<City> cities = citiesAdapter.filterCities("AM");
        assertEquals(2, cities.size());
        assertEquals("Am Dammsteg", cities.get(0).getName());
        assertEquals("Amsterdam", cities.get(1).getName());
    }

    @Test
    public void testValidEntryWithSerachCirteriaM() {
        List<City> cities = citiesAdapter.filterCities("M");

        City expectedCity = buildCity("Mumbai", 1275339, "IN", 19.01441,72.847939);

        assertSame(mockedList.get(5), cities.get(0));
    }

    @Test
    public void testInValidEntryWithSerachCirteriaZ() {
        List<City> cities = citiesAdapter.filterCities("Z");

        assertEquals(0, cities.size());
    }

    @Test
    public void testInValidEntryWithSerachCirteriaSpeacilChars() {
        List<City> cities = citiesAdapter.filterCities("!@#");

        assertEquals(0, cities.size());
    }

}