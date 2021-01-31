package com.wolox.wchallenge;

import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
    public static final String API_EXTERNAL_BASE_PATH = "https://jsonplaceholder.typicode.com";
    public static final String USERS_PATH = "/users";
    public static final String PHOTOS_PATH = "/photos";
    public static final String ALBUMS_PATH = "/albums";

    public String getUserBasePath() {
        return API_EXTERNAL_BASE_PATH + USERS_PATH;
    }

    public String getPhotosBasePath() {
        return API_EXTERNAL_BASE_PATH + PHOTOS_PATH;
    }

    public String getAlbumBasePath() {
        return API_EXTERNAL_BASE_PATH + ALBUMS_PATH;
    }

    public String getAlbumsByUserPath(String userId) {
        return API_EXTERNAL_BASE_PATH + USERS_PATH
                + "/" + userId + "/" + ALBUMS_PATH;
    }

    public String getPhotosByUserPath(String userId) {
        return API_EXTERNAL_BASE_PATH + USERS_PATH
                + "/" + userId + "/" + PHOTOS_PATH;
    }
}
