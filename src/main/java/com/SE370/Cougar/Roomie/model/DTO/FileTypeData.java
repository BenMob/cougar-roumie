package com.SE370.Cougar.Roomie.model.DTO;

import com.SE370.Cougar.Roomie.model.entities.Image;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

public class FileTypeData {

    private String fileName;
    private String fileType;
    private String src;
    private byte[] data;
    private MultipartFile fileInfo;

    public FileTypeData(){};

    public FileTypeData(Image image){
        this.fileName = image.getFileName();
        this.fileType = image.getFileType();
        this.data = image.getData();
        this.src = Base64.getEncoder().encodeToString(image.getData());
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

    public MultipartFile getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(MultipartFile fileInfo) {
        this.fileInfo = fileInfo;
    }

}


