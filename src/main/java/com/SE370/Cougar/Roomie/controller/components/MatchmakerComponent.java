package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.controller.services.RelationshipService;
import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    private static final Logger logger = LoggerFactory.getLogger(MatchmakerComponent.class);

    private Optional<String> userName;
    private Optional<Integer> matchScore;
    private int count = -1;

    private boolean candidateMatch() throws RuntimeException {

        if (count < matchList.size()) {
            int usr1 = relationshipList.get(count).getUser_one_status();
            int usr2 = relationshipList.get(count).getUser_two_status();

            if ((usr1 == 1 && usr2 == 1) || (usr1 == 2 || usr2 == 2)) {
                return false;
            } else {
                return true;
            }
        } else {
            throw new RuntimeException("Nothing Found...");
        }
    }

    public UserInfo getMatch() throws RuntimeException {
        if (count == -1) {
            throw new RuntimeException("Not Initialized..");
        } else {
            while (!candidateMatch()) {
                count++;
                logger.info("counter: " + count);
            }
            count++;
            return matchList.get(count-1);
        }
    }


    // These functions work because we have an ongoing
    // count therefore we know which relationship is tied to which user....
    public void submitLike() {
        relationshipService.submitLike(relationshipList.get(count-1), this.userName.get());
    }

    public void submitDislike() {
        relationshipService.submitDislike(relationshipList.get(count-1), this.userName.get());
    }


    // Should be the first function called, fills the necessary details since we cannot access SecurityContext directly.
    public boolean init(String userName, int matchScore) throws RuntimeException {
        if (count == -1) {
            // Initialize our component
            this.userName = Optional.ofNullable(userName);
            this.matchScore = Optional.ofNullable(matchScore);
            logger.info("Requesting matches for " + userName + " with " + matchScore);
            matchList = userService.getMatches(this.userName.orElseThrow(RuntimeException::new), this.matchScore.orElseThrow(RuntimeException::new));
            // Fill relationship list
            relationshipList = new ArrayList<Relationship>();
            for (UserInfo match : matchList){
                logger.info("Getting Relationship");
                relationshipList.add(relationshipService.getRelationshipBetweenTwoUsers(match.getUserName(), this.userName.get()));
            }
            count++;
            return true;
        }
        return false;
    }



}