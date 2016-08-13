package com.face.po;

import java.util.Date;

public class UploadFace {
    private Long id;

    private String uploadimg;

    private Date uploaddate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadimg() {
        return uploadimg;
    }

    public void setUploadimg(String uploadimg) {
        this.uploadimg = uploadimg == null ? null : uploadimg.trim();
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }
}