package com.wolox.wchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class SharedAlbum {

    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String albumId;
    private Permission permission;

    public SharedAlbum() {
    }

    public SharedAlbum(String userId, String albumId, Permission permission) {
        this.userId = userId;
        this.albumId = albumId;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserAlbumPermission{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", albumId='" + albumId + '\'' +
                '}';
    }
}
