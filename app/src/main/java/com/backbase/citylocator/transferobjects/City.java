package com.backbase.citylocator.transferobjects;

public class City {
    private int _id;
    private String name;
    private String country;
    private GPSCoordinates coord;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    private class GPSCoordinates {
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
