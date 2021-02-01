package com.wolox.wchallenge.service;

import com.wolox.wchallenge.model.SharedAlbum;

import java.util.List;

public interface ISharedAlbumService {
    List<SharedAlbum> findAll();
    SharedAlbum save(SharedAlbum sharedAlbum);
}
