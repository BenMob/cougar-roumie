package com.SE370.Cougar.Roomie.model.DTO;

/*
This class will serve as a DTO for populating the profile page.
 */

import com.SE370.Cougar.Roomie.model.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Profile {

    private String first_name;
    private String last_name;
    private String major;
    private String headline;
    private String image_src;
  //  private String fileName;
  //  private String fileType;
  //  private byte[] fileData;
    int gender;
    MultipartFile profileImage; // MultipartFile type is the DS containing all the image information (bytes, name, etc ...)


    public Profile(){this.first_name = null; this.last_name = null; this.gender = 0; this.major = null; this.headline = null;}
    public Profile(String fName, String lName, int gender, String major, String headline){
        this.setFirst_name(fName);
        this.setLast_name(lName);
        this.setGender(gender);
        this.setMajor(major);
        this.setHeadline(headline);
    }

    public Image mapImageToUser(int id) throws IOException {
        return new Image(
                id,
                profileImage.getOriginalFilename(),
                profileImage.getContentType(),
                profileImage.getBytes());
    }
/*
    public void setProfileImageData(String fileName, String fileType, String filePath, byte[] fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileData = fileData;
    }
*/
/*
    public void prepareProfileImage() throws IOException {
        this.fileName = profileImage.getOriginalFilename();
        this.fileType = profileImage.getContentType();
        this.filePath = StringUtils.cleanPath(profileImage.getOriginalFilename());
        this.fileData = profileImage.getBytes();
    }
*/
    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public int getGender() { return gender; }
    public void setGender(int gender) { this.gender = gender; }
    public String getHeadline() { return headline; }
    public void setHeadline(String headline) { this.headline = headline; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public MultipartFile getProfileImage() { return profileImage; }
    public void setProfileImage(MultipartFile profileImage) { this.profileImage = profileImage; }
    public String getImage_src() { return image_src; }
    public void setImage_src(String image_src) { this.image_src = image_src; }
}
