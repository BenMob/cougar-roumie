package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import com.SE370.Cougar.Roomie.model.repositories.RelationshipRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RelationshipService {

    @Autowired
    UserService userservice;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RelationshipRepo relationshipRepo;


    /***********************************************************************************
     * Given two user_names this should query their relationship row, if they have one
     * Could be useful in the isMatch() method below
     * @param user1
     * @param user2
     * @return Found or Created Relationship object
     */
    public Relationship getRelationshipBetweenTwoUsers(String user1, String user2){
        // Grab relation from db if none exist create it pass back to component

        // This only accounts for one direction... Still need to account for the other eg user2, user1
        return relationshipRepo.findByUsername1AndUsername2(user1, user2)
                .orElseGet(() -> new Relationship(user1,user2)); // Create new relationship
    }

    // Updates entry in repo with like value
    public void submitLike(Relationship incoming, String actionUser) {
        if (incoming.getUsername1().equals(actionUser)) {
            incoming.setUser_one_status(1);
        } else {
            incoming.setUser_two_status(1);
        }
        relationshipRepo.save(incoming);
    }

    // Updates entry in repo with dislike value
    public void submitDislike(Relationship incoming, String actionUser) {
        if (incoming.getUsername1().equals(actionUser)) {
            incoming.setUser_one_status(2);
        } else {
            incoming.setUser_two_status(2);
        }
        relationshipRepo.save(incoming);
    }
    // Returns a list of matched relationships (Both status must be 1)
    public List<Relationship> getMatchedRelationships(String username){
        return relationshipRepo.findByUsername1AndUseronestatusAndUsertwostatus ("Ben", 1, 1);
    }

    public boolean isMatch(Relationship incoming){
        if (incoming.getUser_one_status() == 1 && incoming.getUser_two_status() == 1) {
            return true;
        } else return false;
    }



}
