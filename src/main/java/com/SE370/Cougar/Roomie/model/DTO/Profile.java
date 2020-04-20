package com.SE370.Cougar.Roomie.model.DTO;

/*
This class will serve as a DTO for populating the profile page.
TODO: Have the FIrstTimeLogin form use this dto as well.
 */

public class Profile {

    String first_name;
    String last_name;
    int gender;

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public int getGender() { return gender; }
    public void setGender(int gender) {
        this.gender = gender;
    }

}
