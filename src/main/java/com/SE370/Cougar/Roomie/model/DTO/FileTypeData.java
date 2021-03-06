package com.SE370.Cougar.Roomie.model.DTO;

import com.SE370.Cougar.Roomie.model.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class FileTypeData {

    private String fileName;
    private String fileType;
    private String src;
    private byte[] data;

    public FileTypeData(){};

    public FileTypeData(Image image){
        this.fileName = image.getFileName();
        this.fileType = image.getFileType();
        this.data = image.getData();
        this.src = Base64.getEncoder().encodeToString(image.getData());
    }

    public FileTypeData(MultipartFile file) throws IOException {
        setFileName(file.getOriginalFilename());
        setFileType(file.getContentType());
        setData(file.getBytes());
        setSrc(Base64.getEncoder().encodeToString(file.getBytes()));
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}


