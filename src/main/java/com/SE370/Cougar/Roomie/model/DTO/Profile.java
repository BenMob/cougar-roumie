package com.SE370.Cougar.Roomie.model.DTO;

/*
This class will serve as a DTO for populating the profile page.
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public class Profile {

    private String first_name;
    private String last_name;
    private String major;
    private String headline;
    int gender;
    private Optional<FileTypeData> image;


    public Profile(){this.first_name = null; this.last_name = null; this.gender = 0; this.major = null; this.headline = null;}
    public Profile(String fName, String lName, int gender, String major, String headline){
        this.setFirst_name(fName);
        this.setLast_name(lName);
        this.setGender(gender);
        this.setMajor(major);
        this.setHeadline(headline);
    }

    public Profile (CustomUserDetails user, Optional<FileTypeData> image) {
        this.first_name = user.getFirstName();
        this.last_name = user.getLastName();
        this.major = user.getMajor();
        this.headline = user.getHeadline();
        this.gender = user.getGender();
        this.image = image;
    }

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

    public Optional<FileTypeData> getImage() {
        return image;
    }
    public void setImage(Optional<FileTypeData> image) {
        this.image = image;
    }
}
