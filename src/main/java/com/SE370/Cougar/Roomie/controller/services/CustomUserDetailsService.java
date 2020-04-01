package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import com.SE370.Cougar.Roomie.view.FirstTimeLoginForm;
import com.SE370.Cougar.Roomie.view.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Error Not Found: " + userName));

        return user.map(CustomUserDetails::new).get();
    }

    @Transactional
    public User createNewUser(RegistrationForm registrationForm) { // Very basic implementation -- ADD validation
        User user = new User();
        user.setEmail(registrationForm.getEmail());
        user.setUserName(registrationForm.getUser_name());
        user.setPassword(registrationForm.getPassword());
        user.setActive(true);
        return userRepository.save(user);
    }

    @Transactional
    public User updateFirstTimeUser(FirstTimeLoginForm secondaryInfoForm){
        CustomUserDetails customUser =  (((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()));

        User user = customUser.map(User::new).get();
        user.setFirstName(secondaryInfoForm.getFirst_name());
        user.setLastName(secondaryInfoForm.getLast_name());

        return userRepository.save(user);
    }

}
