package com.wolox.wchallenge.controllers;

import com.wolox.wchallenge.ApiConfig;
import com.wolox.wchallenge.models.User;
import com.wolox.wchallenge.models.UserAlbumPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(ApiConfig.PERMISSION_PATH)
public class PermissionController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postPermission(@RequestBody UserAlbumPermission userAlbumPermission) {
        HttpEntity<UserAlbumPermission> request = new HttpEntity<>(userAlbumPermission);
        ResponseEntity<UserAlbumPermission> response = restTemplate.exchange(ApiConfig.JSON_SERVER_PATH,
                HttpMethod.POST, request, UserAlbumPermission.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
