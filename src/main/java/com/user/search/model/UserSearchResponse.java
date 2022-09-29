package com.user.search.model;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Model class to represent user details
 */
@ApiResponse
public class UserSearchResponse {


    private Integer id;

    private String email;

    private String phone;


    public Integer getId() {
        return id;
    }

    public UserSearchResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSearchResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserSearchResponse setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "UserSearchResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}