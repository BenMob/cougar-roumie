package com.SE370.Cougar.Roomie.model.DTO;

/*
This class will serve as a DTO for populating the profile page.
 */

import org.springframework.web.multipart.MultipartFile;

public class Profile {

    String first_name;
    String last_name;
    String major;
    String headline;
    MultipartFile profileImage; // MultipartFile type is the DS containing all the image information (bytes, name, etc ...)
    int gender;

    public Profile(){this.first_name = null; this.last_name = null; this.gender = 0; this.major = null; this.headline = null;}
    public Profile(String fName, String lName, int gender, String major, String headline){
        this.setFirst_name(fName);
        this.setLast_name(lName);
        this.setGender(gender);
        this.setMajor(major);
        this.setHeadline(headline);
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
    public MultipartFile getProfileImage() { return profileImage; }
    public void setProfileImage(MultipartFile profileImage) { this.profileImage = profileImage; }
}
