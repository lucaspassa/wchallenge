package com.wolox.wchallenge.controllers;

import com.wolox.wchallenge.ApiConfig;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Photo>> getPhotos() {
        ResponseEntity<List<Photo>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/photos", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Photo>>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPhoto(@PathVariable(value = "ID") int id) {

        ResponseEntity<Photo> response = restTemplate.exchange(apiConfig.getPHOTOS_PATH() + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Photo>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
