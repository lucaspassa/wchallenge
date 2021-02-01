package com.wolox.wchallenge.repository;

import com.wolox.wchallenge.model.SharedAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedAlbumRepository extends JpaRepository<SharedAlbum, Integer> {
}
