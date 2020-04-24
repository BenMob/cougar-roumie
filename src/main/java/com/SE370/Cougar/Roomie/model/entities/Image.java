package com.SE370.Cougar.Roomie.model.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "files")
public class Image {

    @Id
    private int userId;
    private String fileName;
    private byte[] data;

    public Image(){};

    public Image(int userId, MultipartFile fileContent) throws IOException {
        setUserId(userId);
        setFileName(fileContent.getOriginalFilename());
        setData(fileContent.getBytes());
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

}
