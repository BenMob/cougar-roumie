package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.DTO.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.*;
import com.SE370.Cougar.Roomie.controller.components.ObjectConverter;
import com.SE370.Cougar.Roomie.model.entities.Image;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.FileRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    ObjectConverter converter;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Try to find user from db
        Optional<User> user = userRepository.findByUserName(userName);

        // Create CustomUserDetails out of user entity
        return user
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Error Not Found: " + userName));
    }

    // Finds image and converts all in one step..
    @Transactional
    public Optional<FileTypeData> getImage(int id) {
        return fileRepository.findByUserId(id)
                .map(FileTypeData::new);
    }

    public Profile getProfile () {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Profile(user, getImage(user.getUser_id()));
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

    @Transactional
    public List<UserInfo> getMatches(String userName, int matchScore) {
        logger.info("looking for matches with score of: " + matchScore);
        return userRepository.findAllByMatchScoreBetween(matchScore-1, matchScore+1).stream()
                .filter(match -> !match.getUserName().equals(userName))
                .map(foundMatch -> {
                    return new UserInfo(
                            foundMatch.getUserName(),
                            foundMatch.getFirstName() + " " + foundMatch.getLastName(),
                            foundMatch.getId(),
                            foundMatch.getMatchScore(),
                            foundMatch.getHeadline(),
                            getImage(foundMatch.getId()));
                }).collect(Collectors.toList());
    }
    @Transactional
    public User submitAssessment(AssessmentForm assessmentForm) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Rebuild auth also submits assessment
        auth = converter.updateAuth(auth, assessmentService.submitAndCalculateScore(
             assessmentForm, ((CustomUserDetails) auth.getPrincipal()).getUser_id())
        );

        // Set Authentication
        SecurityContextHolder.getContext().setAuthentication(auth);

        // update user in repo (returns updated user object)
        return userRepository.save(converter.convertToEntity(auth));
    }


    @Transactional
    public User updateFirstTimeUser(Profile profileInfoForm, MultipartFile file) throws IOException {

        // Rebuild Auth
        Authentication updated = converter.updateAuth(
                SecurityContextHolder.getContext().getAuthentication(), profileInfoForm);

        // Set Authentication then convert to Entity
        SecurityContextHolder.getContext().setAuthentication(updated);
        User converted = converter.convertToEntity(updated);

        // Save Profile Pic
        fileRepository.save(converter.convertToEntity(file, converted.getId()));

        // Save updated user to db
        return userRepository.save(converted);
    }

}
