package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<Image, Integer> {
    Optional<Image> findByUserId(int userId); // Returns a user's profile Image if they have one
}
