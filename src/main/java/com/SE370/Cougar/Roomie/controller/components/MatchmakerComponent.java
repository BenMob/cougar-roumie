package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.controller.services.RelationshipService;
import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// This is a websocket scoped component meaning that this object is created EVERY time a websocket
// connection is established and destroyed as soon as they refresh/leave
@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MatchmakerComponent {

    @Autowired
    UserService userService;
    @Autowired
    RelationshipService relationshipService;

    // These will be in order with each other
    private List<UserInfo> matchList;
    private List<Relationship> relationshipList;


    private Optional<String> userName;
    private Optional<Integer> matchScore;
    private int count = -1;

    public UserInfo getMatch() throws RuntimeException {
        // If this is the first time calling, fill our local list with matches
        if (count == -1) {
            throw new RuntimeException("Not Initialized..");
        } else if (count < matchList.size()) {
            count++;
            return matchList.get(count - 1);
        } else {
            throw new RuntimeException("Uh oh.... Nothing was found. Did you know matches automatically show up in chat when they like you back?");
        }
    }


    // These functions work because we have an ongoing
    // count therefore we know which relationship is tied to which user....

    public void submitLike(String otherName) {
        relationshipService.submitLike(relationshipList.get(count-1), this.userName.get());
    }

    public void submitDislike(String otherName) {
        relationshipService.submitDislike(relationshipList.get(count-1), this.userName.get());
    }


    // Should be the first function called, fills the necessary details since we cannot access SecurityContext directly.
    public void init(String userName, int matchScore) throws RuntimeException {
        if (count == -1) {

            // Initialize our component
            this.userName = Optional.ofNullable(userName);
            this.matchScore = Optional.ofNullable(matchScore);
            matchList = userService.getMatches(this.userName.orElseThrow(RuntimeException::new), this.matchScore.orElseThrow(RuntimeException::new));
            // Fill relationship list
            relationshipList = new ArrayList<Relationship>();
            for (UserInfo match : matchList){
                relationshipList.add(relationshipService.getRelationshipBetweenTwoUsers(match.getUserName(), this.userName.get()));
            }
            count++;
        }
    }



}