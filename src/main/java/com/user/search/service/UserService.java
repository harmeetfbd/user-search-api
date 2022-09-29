package com.user.search.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.user.search.client.UserListQueryClient;
import com.user.search.model.UserDetail;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to call a Third party API to fetch user list
 */
@Service
@Slf4j
public class UserService {

    private final UserListQueryClient userListQueryClient;

    public UserService(UserListQueryClient userListQueryClient) {
        this.userListQueryClient = userListQueryClient;
    }


    public List<UserDetail> extractJsonResponse() {
        try {
            Response response = userListQueryClient.query();
            boolean isResponseOk = validateResponse(response);
            if (isResponseOk) {
                InputStream inputStream = response.body().asInputStream();
                Reader reader = new InputStreamReader(inputStream);
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(reader);
                String userListJson = jsonElement.getAsJsonArray().toString();
                inputStream.close();
                return parseResponse(userListJson);
            }
        } catch (Exception ex) {
            log.error("Error Occurred while extracting third party response", ex);
        }
        return List.of();
    }

    private boolean validateResponse(Response response) {
        if (response.status() != HttpStatus.OK.value()) {
            log.error("There is an issue with provided API [{}]", response.body().toString());
            return false;
        }
        return true;
    }

    private List<UserDetail> parseResponse(String userListJson) {
        if (null != userListJson) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<UserDetail>>() {
            }.getType();
            return gson.fromJson(userListJson, userListType);
        }
        return List.of();
    }
}
