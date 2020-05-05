package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import com.SE370.Cougar.Roomie.model.repositories.RelationshipRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RelationshipService {
    private static final Logger logger = LoggerFactory.getLogger(RelationshipService.class);
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
        List <Relationship> found = relationshipRepo.findByUsername1AndUsername2(user1, user2);
        logger.info(" found relationship -> SIZE -> " + found.size());
        if (found.isEmpty()) {
            logger.info("Generating new relationship for:" + user1 + " and " + user2);
            return new Relationship(user1, user2);
        } else {

            return found.get(0);
        }
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
    public List<String> getMatchedRelationships(String username){

        return relationshipRepo.findMatchedRelationships(username)
                .stream().map(found -> {
                    if (found.getUsername1().equals(username)) {
                        return found.getUsername2();
                    } else {
                        return found.getUsername1();
                    }
                }).collect(Collectors.toList());
    }

    public boolean isMatch(Relationship incoming){
        if (incoming.getUser_one_status() == 1 && incoming.getUser_two_status() == 1) {
            return true;
        } else return false;
    }



}
