package com.wolox.wchallenge.controllers;

import com.wolox.wchallenge.ApiConfig;
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
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(apiConfig.getUSERS_PATH(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable(value = "ID") int id) {

        ResponseEntity<User> response = restTemplate.exchange(apiConfig.getUSERS_PATH() + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<User>() {
                });
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
