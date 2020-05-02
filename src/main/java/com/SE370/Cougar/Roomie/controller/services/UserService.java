package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.controller.view.MatchController;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.*;
import com.SE370.Cougar.Roomie.model.entities.Image;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.FileRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO take the image logic out and move it in its own service class

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepository;
    @Autowired
    AssessmentService assessmentService;

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


    public Image queryImageById(int id) throws DataRetrievalFailureException {
        Optional<Image> image = fileRepository.findByUserId(id);

        return image
                .map(Image::new)
                .orElseThrow(() -> new DataRetrievalFailureException("Failure to retrieve Image for " + id));
    }

    public Profile prepareProfile(CustomUserDetails thisUser){
        if(profileInfoNotComplete(thisUser))
            return new Profile(); // empty Profile DTO
        else {
            return new Profile(
                    thisUser.getFirstName(),
                    thisUser.getLastName(),
                    thisUser.getGender(),
                    thisUser.getMajor(),
                    thisUser.getHeadline());  // filled Profile DTO
        }
    }
    // TODO Figure out how t check for image independently from other fields
    public FileTypeData getProfileImage(CustomUserDetails thisUser){
        if(profileInfoNotComplete(thisUser))
            return new FileTypeData();
        else {
            Image profileImage = queryImageById(thisUser.getUser_id());
            return new FileTypeData(profileImage);
        }
    }

    private Boolean profileInfoNotComplete(CustomUserDetails thisUser){
        return (thisUser.getFirstName() == null ||
                thisUser.getLastName() == null ||
                thisUser.getGender() == 0);
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
                    converted.setMatchScore(foundUser.getMatchScore());
                    return converted;
                }).collect(Collectors.toList());
    }

    public List<UserInfo> getMatches(String userName, int matchScore) {
        logger.info("looking for matches with score of: " + matchScore);
        return userRepository.findAllByMatchScoreBetween(matchScore-1, matchScore+1).stream()
                .filter(match -> !match.getUserName().equals(userName))
                .map(foundMatch -> {
                    UserInfo conv = new UserInfo();
                    conv.setUserName(foundMatch.getUserName());
                    conv.setId(foundMatch.getId());
                    conv.setMatchScore(foundMatch.getMatchScore());
                    conv.setName(foundMatch.getFirstName() + " " + foundMatch.getLastName());
                    conv.setBio(foundMatch.getHeadline());
                    return conv;
                }).collect(Collectors.toList());
    }
    @Transactional
    public User submitAssessment(AssessmentForm assessmentForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails custom = (CustomUserDetails) auth.getPrincipal();

        // Calculate match score and submit assessment to db
        custom.setMatchScore(assessmentService.submitAssessment(assessmentForm, custom.getUser_id()));

        // rebuild auth
        Authentication newAuth = new UsernamePasswordAuthenticationToken(custom, auth.getCredentials(), auth.getAuthorities());

        // set auth
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // update user in repo (returns updated user object)
        return userRepository.save(new User(custom));
    }


    @Transactional
    public User updateFirstTimeUser(Profile profileInfoForm, FileTypeData profileImage) throws IOException {

        // Grab auth token
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // cast auth into Custom User Details -> fills all available fields...
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        // Update Custom User Details Object....
        user.setFirstName(profileInfoForm.getFirst_name());
        user.setLastName(profileInfoForm.getLast_name());
        user.setGender(profileInfoForm.getGender());
        user.setMajor(profileInfoForm.getMajor());
        user.setHeadline(profileInfoForm.getHeadline());

        // Rebuild Authentication
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), auth.getAuthorities());

        // Set Authentication
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        fileRepository.save(
                new Image(user.getUser_id(),
                        profileImage.getFileName(),
                        profileImage.getFileType(),
                        profileImage.getData()));

        return userRepository.save(new User(user));
    }
}
