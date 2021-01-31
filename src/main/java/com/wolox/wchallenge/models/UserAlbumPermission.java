package com.wolox.wchallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAlbumPermission {
    private String id;
    private String userId;
    private String albumId;
    private Permission permission;

    public UserAlbumPermission() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                ", permission='" + permission + '\'' +
                '}';
    }
}
