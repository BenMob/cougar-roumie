package com.SE370.Cougar.Roomie.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName); // JPA will automatically implement this for us
}

