package com.user.search.model;

public class UserDetail {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private AddressDetail address;

    private String phone;

    private String website;

    private CompanyDetail company;

    public Integer getId() {
        return id;
    }

    public UserDetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetail setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetail setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressDetail getAddress() {
        return address;
    }

    public UserDetail setAddress(AddressDetail address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDetail setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public UserDetail setWebsite(String website) {
        this.website = website;
        return this;
    }

    public CompanyDetail getCompany() {
        return company;
    }

    public UserDetail setCompany(CompanyDetail company) {
        this.company = company;
        return this;
    }
}
