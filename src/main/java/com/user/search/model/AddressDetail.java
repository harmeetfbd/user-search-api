package com.user.search.model;

public class AddressDetail {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private GeoDetail geo;

    public String getStreet() {
        return street;
    }

    public AddressDetail setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getSuite() {
        return suite;
    }

    public AddressDetail setSuite(String suite) {
        this.suite = suite;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDetail setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AddressDetail setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public GeoDetail getGeo() {
        return geo;
    }

    public AddressDetail setGeo(GeoDetail geo) {
        this.geo = geo;
        return this;
    }
}
