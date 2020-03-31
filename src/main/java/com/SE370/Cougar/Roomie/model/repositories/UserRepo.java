package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName); // JPA will automatically implement this for us
    Optional<User> findById(int id);
}

