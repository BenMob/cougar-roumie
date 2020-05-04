package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import com.SE370.Cougar.Roomie.model.repositories.RelationshipRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


    // IDK what to do with these yet... Didn't want to delete your work...


    /**********************************************************************************************************
     * This is the first method to be called once someone clicks LIKE. It checks if they already have a relationship column
     * opened with the person they just liked.
     *
     * If (yes)
     *      it calls updateRelationship()
     * else:
     *      it calls startRelationship()
     *
     * @param thisUser
     * @param friend_username
     */
    /*
    void checkForRelationship(CustomUserDetails thisUser, String friend_username){

        // This is where everything begins


    }

    /***********************************************************************************
     * This gets called when initializing a relationship for the first time
     *
     * When a logged in user1 LIKES a user2 and user2 has not LIKED user1 in the past (i.e no existing relationship between the two),
     * this should initialize a row in the relationship table with user1 status=1 and user2 status = 0.
     *
     * @param thisUser
     * @param friend_username
     * @param status
     */
    /*
    public void startRelationship(CustomUserDetails thisUser, String friend_username, int status){
        relationshipRepo.save( new Relationship(thisUser.getUser_id() ,thisUser.getUsername(), status, friend_username, 0));
    }*/



    /***********************************************************************************
     * This gets called from the top-most method if a relationship already exists
     *
     * If the logged in user1 has been liked by the user2 already, then this method should not create another relationship
     *      instead, it should update an existing relationship by having both status be 1 and therefore a match.
     *
     * @param thisUser
     * @param friend_username
     */
    /*
    public void updateRelationship(CustomUserDetails thisUser, String friend_username){

    }*/





    /***********************************************************************************
     * Given two user_names this method should
     * call getRelationshipBetweenTwoUsers(String username_one, String username_two);
     * Then return true or false depending on whether they match or not
     *
     * @param  username_one
     * @param username_two
     * @return
     */
    /*
    public boolean isMatch(String username_one, String username_two){

        return true;
    }*/



}
