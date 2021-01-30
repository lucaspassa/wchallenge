package com.wolox.wchallenge;

import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
    private String API_EXTERNAL_BASE_PATH = "https://jsonplaceholder.typicode.com";
    private String USERS_PATH = API_EXTERNAL_BASE_PATH + "/users";
    private String PHOTOS_PATH = API_EXTERNAL_BASE_PATH + "/photos";

    public String getAPI_EXTERNAL_BASE_PATH() {
        return API_EXTERNAL_BASE_PATH;
    }

    public String getUSERS_PATH() {
        return USERS_PATH;
    }

    public String getPHOTOS_PATH() {
        return PHOTOS_PATH;
    }
}
