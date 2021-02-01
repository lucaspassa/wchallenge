package com.wolox.wchallenge.controller;

import com.wolox.wchallenge.ApiConfig;
import com.wolox.wchallenge.model.Permission;
import com.wolox.wchallenge.model.SharedAlbum;
import com.wolox.wchallenge.service.SharedAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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

    @GetMapping(value = "")
    public ResponseEntity<List<SharedAlbum>> getSharedAlbums() {

        List<SharedAlbum> sharedAlbums = sharedAlbumService.findAll();
        return new ResponseEntity<>(sharedAlbums, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SharedAlbum> postSharedAlbums(@RequestBody SharedAlbum sharedAlbum){

        String errorMessange = "";
        if (!validateFields(sharedAlbum)) {
            errorMessange += "ERROR: Hay campos vacíos. Los campos obligatorios son: ";
            errorMessange += " userId, albumId, y permission (" + Arrays.asList(Permission.values()) + ") ";
            return new ResponseEntity(errorMessange, HttpStatus.BAD_REQUEST);
        } else if (!validateInsert(sharedAlbum)) {
            errorMessange += "ERROR: Ya existe un album compartido para albumId: " + sharedAlbum.getAlbumId() ;
            errorMessange += " y userId: " + sharedAlbum.getUserId();
            return new ResponseEntity(errorMessange, HttpStatus.BAD_REQUEST);
        }

        sharedAlbum = sharedAlbumService.save(sharedAlbum);
        return new ResponseEntity<>(sharedAlbum, HttpStatus.CREATED);
    }

    private boolean validateInsert(SharedAlbum sharedAlbum) {
        boolean valid = true;
        List<SharedAlbum> sharedAlbumList = sharedAlbumService.findAll();
        for (SharedAlbum item : sharedAlbumList) {
            if (item.getAlbumId().equals(sharedAlbum.getAlbumId())
                    && item.getUserId().equals(sharedAlbum.getUserId())) {
                valid = false;
            }
        }
        return valid;
    }

    private boolean validateFields(SharedAlbum sharedAlbum) {
        boolean valid = true;

        if (sharedAlbum.getAlbumId() == null || sharedAlbum.getUserId() == null
                || sharedAlbum.getPermission() == null) {
            valid = false;
        }
        return valid;
    }

    @PutMapping(value = "")
    public ResponseEntity<SharedAlbum> putSharedAlbums(@RequestBody SharedAlbum sharedAlbum){

        String errorMessange = "";
        if (!validateFields(sharedAlbum)) {
            errorMessange += "ERROR: Hay campos vacíos. Los campos obligatorios son: ";
            errorMessange += " userId, albumId, y permission (" + Arrays.asList(Permission.values()) + ") ";
            return new ResponseEntity(errorMessange, HttpStatus.BAD_REQUEST);
        }

        List<SharedAlbum> sharedAlbumList = sharedAlbumService.findAll();
        for (SharedAlbum item : sharedAlbumList) {
            if (item.getAlbumId().equals(sharedAlbum.getAlbumId())
                        && item.getUserId().equals(sharedAlbum.getUserId())){
                item.setPermission(sharedAlbum.getPermission());

                sharedAlbumService.save(item);
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
