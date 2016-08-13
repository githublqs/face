package com.face.po;

public class UserfaceImg {
    private Long id;

    private String username;

    private String faceimg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFaceimg() {
        return faceimg;
    }

    public void setFaceimg(String faceimg) {
        this.faceimg = faceimg == null ? null : faceimg.trim();
    }
}