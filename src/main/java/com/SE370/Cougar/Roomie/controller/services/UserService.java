package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.entities.Image;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.FileRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import com.SE370.Cougar.Roomie.model.DTO.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    FileRepo fileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Try to find user from db
        Optional<User> user = userRepository.findByUserName(userName);

        // Below works as follows, convert user into a CustomUserDetails object, if user is null throw error
        return user
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Error Not Found: " + userName));
    }

    /*
    Role: Grab user information from the DB for profile set up.
    Parameter: Profile DTO
     */
    public Profile prepareProfile(CustomUserDetails user){
        if(profileInfoNotComplete(user))
            return new Profile(); // empty Profile DTO
        else {
            Profile profile = new Profile(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getMajor(),
                    user.getHeadline());  // filled Profile DTO

            return profile;
        }

    }

    // Task: Helper function to check if all required info are present for the current user
    private Boolean profileInfoNotComplete(CustomUserDetails thisUser){
        return (thisUser.getFirstName() == null ||
                thisUser.getLastName() == null ||
                thisUser.getGender() == 0);
    }

    // Helper function that creates a new image entity
    private Image createProfileImage(int user_Id, MultipartFile fileInfo) throws IOException {
        return new Image(user_Id, fileInfo);
    }

    @Transactional
    public User createNewUser(RegistrationForm registrationForm) throws RuntimeException {

        if (!userRepository.existsByUserName(registrationForm.getUser_name())) { // check if username exists
            User user = new User();
            user.setEmail(registrationForm.getEmail());
            user.setUserName(registrationForm.getUser_name());
            user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
            user.setActive(true);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Sorry, that username is not available");
        }
    }

    @Transactional
    public List<UserInfo> getAllUsers () {
        CustomUserDetails thisUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findAll().stream()
                .filter(user -> !user.getUserName().equals(thisUser.getUsername()))
                .map(foundUser -> {
                    UserInfo converted = new UserInfo();
                    converted.setId(foundUser.getId());
                    converted.setUserName(foundUser.getUserName());
                    return converted;
                }).collect(Collectors.toList());
    }

    @Transactional
    public User updateFirstTimeUser(Profile profileInfoForm) throws IOException {
        User user = new User((((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())));

        Image profileImage = createProfileImage(user.getId(), profileInfoForm.getProfileImage());
        fileRepository.save(profileImage); // Saves the profile image

        return userRepository.save(user);
    }
}
