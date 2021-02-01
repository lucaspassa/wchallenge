package com.wolox.wchallenge;

import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
    public static final String API_EXTERNAL_BASE_PATH = "https://jsonplaceholder.typicode.com";
    public static final String USERS_PATH = "/users";
    public static final String PHOTOS_PATH = "/photos";
    public static final String ALBUMS_PATH = "/albums";
    public static final String SHARED_ALBUMS_PATH = "/shared_albums";
    public static final String COMMENTS_PATH = "/comments";

    public String getExternalUserBasePath() {
        return API_EXTERNAL_BASE_PATH + USERS_PATH;
    }

    public String getExternalPhotosBasePath() {
        return API_EXTERNAL_BASE_PATH + PHOTOS_PATH;
    }

    public String getExternalAlbumBasePath() {
        return API_EXTERNAL_BASE_PATH + ALBUMS_PATH;
    }

    public String getExternalAlbumsByUserPath(String userId) {
        return API_EXTERNAL_BASE_PATH + USERS_PATH
                + "/" + userId + "/" + ALBUMS_PATH;
    }

    public String getExternalPhotosByUserPath(String userId) {
        return API_EXTERNAL_BASE_PATH + USERS_PATH
                + "/" + userId + "/" + PHOTOS_PATH;
    }

}
