package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.controller.view.MatchController;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.*;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepository;
    @Autowired
    AssessmentService assessmentService;

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
        if(user.getFirstName() == null || user.getLastName() == null || user.getGender() == 0)
            return new Profile(); // empty Profile DTO
        else return new Profile(
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getMajor(),
                user.getHeadline());  // filled Profile DTO
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

    public List<UserInfo> getMatches() throws RuntimeException {
        CustomUserDetails thisUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Integer.valueOf(thisUser.getMatchScore()) != null) {
            return userRepository.findAllByMatchScoreBetween(thisUser.getMatchScore()-1, thisUser.getMatchScore()+1).stream()
                    .filter(match -> !match.getUserName().equals(thisUser.getUsername()))
                    .map(foundMatch -> {
                        UserInfo conv = new UserInfo();
                        conv.setUserName(foundMatch.getUserName());
                        conv.setId(foundMatch.getId());
                        conv.setMatchScore(foundMatch.getMatchScore());
                        return conv;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Assessment never filled out");
        }

    }
    @Transactional
    public User submitAssessment(AssessmentForm assessmentForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails custom = (CustomUserDetails) auth.getPrincipal();


        custom.setMatchScore(assessmentService.submitAssessment(assessmentForm, custom.getUser_id()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(custom, auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return userRepository.save(new User(custom));
    }



    @Transactional
    public User updateFirstTimeUser(Profile profileInfoForm){
        User user = new User((((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())));
                
        user.registerProfileInfo(profileInfoForm);

        return userRepository.save(user);
    }

}
