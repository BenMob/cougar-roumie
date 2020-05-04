package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RelationshipRepo extends JpaRepository<Relationship, Integer> {
    Optional<Relationship> findByUsername1AndUsername2(String user1, String user2);

}
