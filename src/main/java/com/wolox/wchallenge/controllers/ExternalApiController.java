package com.wolox.wchallenge.controllers;

import com.wolox.wchallenge.ApiConfig;
import com.wolox.wchallenge.models.Album;
import com.wolox.wchallenge.models.Photo;
import com.wolox.wchallenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ExternalApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    @GetMapping(value = ApiConfig.USERS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(apiConfig.getExternalUserBasePath(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = ApiConfig.USERS_PATH + "/{USER_ID}" + ApiConfig.ALBUMS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAlbumsByUser(@PathVariable(value = "USER_ID") String userId) {

        ResponseEntity<List<Album>> response = restTemplate.exchange(apiConfig.getExternalAlbumsByUserPath(userId), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Album>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = ApiConfig.USERS_PATH + "/{USER_ID}" + ApiConfig.PHOTOS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPhotosByUser(@PathVariable(value = "USER_ID") String userId) {

        ResponseEntity<List<Photo>> response = restTemplate.exchange(apiConfig.getExternalPhotosByUserPath(userId), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Photo>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = ApiConfig.PHOTOS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Photo>> getPhotos() {
        ResponseEntity<List<Photo>> response = restTemplate.exchange(apiConfig.getExternalPhotosBasePath(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Photo>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = ApiConfig.ALBUMS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Album>> getAlbums() {
        ResponseEntity<List<Album>> response = restTemplate.exchange(apiConfig.getExternalAlbumBasePath(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Album>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
