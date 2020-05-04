package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RelationshipRepo extends JpaRepository<Relationship, Integer> {


    @Query("SELECT r FROM Relationship r WHERE r.username1=:username AND r.useronestatus=1 AND r.usertwostatus =1 OR r.username2=:username AND r.useronestatus=1 AND r.usertwostatus =1")
    List<Relationship> findMatchedRelationships (@Param("username") String username);

    @Query("SELECT r FROM Relationship r WHERE r.username1=:user1 AND r.username2=:user2 OR r.username1=:user2 AND r.username2=:user1")
    Optional<Relationship> findByUsername1AndUsername2(@Param("user1") String user1, @Param("user2")String user2);
}
