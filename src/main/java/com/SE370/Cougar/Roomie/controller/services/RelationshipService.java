package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.DTO.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.entities.Relationship;
import com.SE370.Cougar.Roomie.model.repositories.RelationshipRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This Class contains the methods I think will help us implement this bitch ass LIKE button
 *
 * I broke down the tasks into multiple methods so that the process is easy to follow but after
 * we have a proof of concept, I am sure you will find ways to chain them them in your favorite one or two lines of code.
 *
 */


@Service
public class RelationshipService {

    @Autowired
    UserService userservice;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RelationshipRepo relationshipRepo;

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
    public void startRelationship(CustomUserDetails thisUser, String friend_username, int status){
        relationshipRepo.save( new Relationship(thisUser.getUser_id() ,thisUser.getUsername(), status, friend_username, 0));
    }

    /***********************************************************************************
     * This gets called from the top-most method if a relationship already exists
     *
     * If the logged in user1 has been liked by the user2 already, then this method should not create another relationship
     *      instead, it should update an existing relationship by having both status be 1 and therefore a match.
     *
     * @param thisUser
     * @param friend_username
     */
    public void updateRelationship(CustomUserDetails thisUser, String friend_username){




    }



    /***********************************************************************************
     * Given two user_names this should query their relationship row, if they have one
     * Could be useful in the isMatch() method below
     * @param username_one
     * @param username_two
     * @return
     */
    public  Relationship getRelationshipBetweenTwoUsers(String username_one, String username_two){
        // QUESTION: How do I query the relationship table given these two user_names?
        // Can JPA do this for us?

        return new Relationship();
    }

    /***********************************************************************************
     * Given two user_names this method should
     * call getRelationshipBetweenTwoUsers(String username_one, String username_two);
     * Then return true or false depending on whether they match or not
     *
     * @param  username_one
     * @param username_two
     * @return
     */
    public boolean isMatch(String username_one, String username_two){


        return true;
    }

}
