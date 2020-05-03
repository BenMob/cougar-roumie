package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.model.DTO.AssessmentForm;
import com.SE370.Cougar.Roomie.model.DTO.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.FileTypeData;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import com.SE370.Cougar.Roomie.model.entities.Answer;
import com.SE370.Cougar.Roomie.model.entities.Image;
import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


// Do not use this object with scoped components
@Component
public class ObjectConverter {


    // updates with profile and casts to Authentication
    public Authentication updateAuth(Authentication auth, Profile incomingForm) {
        CustomUserDetails unwrapped = (CustomUserDetails) auth.getPrincipal();

        // update with profile fields
        unwrapped.setFirstName(incomingForm.getFirst_name());
        unwrapped.setLastName(incomingForm.getLast_name());
        unwrapped.setGender(incomingForm.getGender());
        unwrapped.setMajor(incomingForm.getMajor());
        unwrapped.setHeadline(incomingForm.getHeadline());

        return new UsernamePasswordAuthenticationToken(unwrapped, auth.getCredentials(), auth.getAuthorities());
    }

    // updates with match score and casts to Authentication
    public Authentication updateAuth (Authentication auth, int matchScore) {
        CustomUserDetails unwrapped = (CustomUserDetails) auth.getPrincipal();
        unwrapped.setMatchScore(matchScore);
        return new UsernamePasswordAuthenticationToken(unwrapped, auth.getCredentials(), auth.getAuthorities());
    }


    public Image convertToEntity(MultipartFile file, int userId) throws IOException {
        FileTypeData unconverted = new FileTypeData(file);

        return new Image(userId,
                unconverted.getFileName(),
                unconverted.getFileType(),
                unconverted.getData());
    }

    public Answer convertToEntity(AssessmentForm unconverted, int userId) {
        return new Answer(userId,
                unconverted.getAnswer1(),
                unconverted.getAnswer2(),
                unconverted.getAnswer3(),
                unconverted.getAnswer4(),
                unconverted.getAnswer5(),
                unconverted.getAnswer6(),
                unconverted.getAnswer7());
    }

    public User convertToEntity(Authentication auth) {
        return new User((CustomUserDetails) auth.getPrincipal());
    }




}
