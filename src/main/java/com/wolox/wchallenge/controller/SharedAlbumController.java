package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.ApiConfig;
import com.wolox.wchallenge.model.SharedAlbum;
import com.wolox.wchallenge.model.User;
import com.wolox.wchallenge.service.SharedAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value = ApiConfig.SHARED_ALBUMS_PATH)
public class SharedAlbumController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;
    @Autowired
    private SharedAlbumService sharedAlbumService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SharedAlbum>> getSharedAlbums() {

        List<SharedAlbum> sharedAlbums = sharedAlbumService.findAll();
        return new ResponseEntity<>(sharedAlbums, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SharedAlbum> postSharedAlbums(@RequestBody SharedAlbum sharedAlbum){
        sharedAlbum = sharedAlbumService.save(sharedAlbum);
        return new ResponseEntity<>(sharedAlbum, HttpStatus.CREATED);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SharedAlbum> putSharedAlbums(@RequestBody SharedAlbum sharedAlbum){
        sharedAlbum = sharedAlbumService.save(sharedAlbum);
        return new ResponseEntity<>(sharedAlbum, HttpStatus.OK);
    }


}
