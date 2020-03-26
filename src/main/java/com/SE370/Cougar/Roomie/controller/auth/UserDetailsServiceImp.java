package com.SE370.Cougar.Roomie.controller.auth;

import com.SE370.Cougar.Roomie.model.user.User;
import com.SE370.Cougar.Roomie.model.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Error Not Found: " + userName));

        return user.map(UserDetails::new).get();
    }

}
