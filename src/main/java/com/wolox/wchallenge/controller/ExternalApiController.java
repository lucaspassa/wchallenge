package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.ApiConfig;
import com.wolox.wchallenge.model.*;
import com.wolox.wchallenge.service.SharedAlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ExternalApiController {
    private static final Logger logger = LoggerFactory.getLogger(ExternalApiController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;
    @Autowired
    private SharedAlbumService sharedAlbumService;

    @GetMapping(value = ApiConfig.USERS_PATH)
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String albumId,
                                               @RequestParam(required = false) String permissionValue) {

        if (albumId == null && permissionValue == null) {
            ResponseEntity<List<User>> response = restTemplate.exchange(apiConfig.getExternalUserBasePath(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<User>>() {
                    });
            List<User> users = response.getBody();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        Permission permission;
        try {
            permission = Permission.valueOf(permissionValue);
        } catch (IllegalArgumentException ex) {
            String errorMessange = "ERROR: El parámetro {permissionValue} con valor " + permissionValue +  " no es válido. " ;
            errorMessange += "Los valores posibles son: " + Arrays.asList(Permission.values());
            return new ResponseEntity(errorMessange, HttpStatus.BAD_REQUEST);
        }

        List<User> userAlbumList = new ArrayList<>();
        if (albumId != null && !albumId.isEmpty()) {
            List<SharedAlbum> sharedAlbumList = sharedAlbumService.findAll();

            for (SharedAlbum item : sharedAlbumList) {
                if (item.getAlbumId().equals(albumId) && item.getPermission().equals(permission)) {

                    ResponseEntity<User> responseUser = restTemplate.exchange(apiConfig.getExternalUserBasePath()
                                    + "/" + item.getUserId(), HttpMethod.GET, null,
                            new ParameterizedTypeReference<User>() {
                            });
                    userAlbumList.add(responseUser.getBody());
                }
            }
        }
        return new ResponseEntity<>(userAlbumList, HttpStatus.OK);
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

    @GetMapping(value = ApiConfig.COMMENTS_PATH)
    public ResponseEntity<List<Comment>> getComments(@RequestParam(required = false) String name) {
        ResponseEntity<List<Comment>> response = restTemplate.exchange(apiConfig.getExternalCommentsPath(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Comment>>() {
                });

        List<Comment> commentList = response.getBody();

        if (commentList != null && !commentList.isEmpty()
                && name != null && !name.isEmpty()) {
            List<Comment> comentsFilterByName = new ArrayList<>();
            for (Comment item : commentList) {
                if (item.getName() != null && !item.getName().isEmpty()
                        && item.getName().contains(name)) {
                        comentsFilterByName.add(item);
                }
            }
            return new ResponseEntity<>(comentsFilterByName, HttpStatus.OK);
        }

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }
}
