package com.backbase.citylocator.transferobjects;

import java.io.Serializable;

public class City implements Serializable{
    private long _id;
    private String name;
    private String country;
    private GPSCoordinates coord = new GPSCoordinates();

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GPSCoordinates getCoord() {
        return coord;
    }

    public void setCoord(GPSCoordinates coord) {
        this.coord = coord;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.coord.setLon(longitude);
        this.coord.setLat(latitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return _id == city._id &&
                name.equals(city.name) &&
                country.equals(city.country) &&
                this.coord.getLat() == city.coord.getLat() &&
                this.coord.getLon() == city.coord.getLon() ;
    }

    public class GPSCoordinates {
        private double lat;
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "GPSCoordinates{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }
}
