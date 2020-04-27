package com.SE370.Cougar.Roomie.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "profile_images")
public class Image {

    @Id
    private int userId;
    private String fileName;
    private String fileType;
    private byte[] data;

    public Image(){};

    public Image(int userId, String fileName, String fileType, byte[] data){
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public Image(Image image) {
        this.userId = image.getUserId();
        this.fileName = image.getFileName();
        this.fileType = image.getFileType();
        this.data = image.getData();
    }

    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "FILE_DATA")
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Column(name = "FILE_TYPE")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
