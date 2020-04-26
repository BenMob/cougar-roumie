package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName); // Returns a user if they exist
    List<User> findAllByMatchScoreBetween(int first, int second);
    boolean existsByUserName(String userName); // Will check if a given User Name exists
}

