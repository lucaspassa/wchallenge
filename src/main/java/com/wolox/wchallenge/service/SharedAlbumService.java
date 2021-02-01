package com.wolox.wchallenge.service;

import com.wolox.wchallenge.model.SharedAlbum;
import com.wolox.wchallenge.repository.SharedAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedAlbumService implements ISharedAlbumService {

    @Autowired
    private SharedAlbumRepository sharedAlbumRepository;

    @Override
    public List<SharedAlbum> findAll() {
        return sharedAlbumRepository.findAll();
    }

    @Override
    public SharedAlbum save(SharedAlbum sharedAlbum) {
        return sharedAlbumRepository.save(sharedAlbum);
    }


}
