package com.user.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main class of User Search Application
 */
@SpringBootApplication
@EnableFeignClients("com.user.search.client")
public class UserSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSearchApplication.class, args);
    }

}
