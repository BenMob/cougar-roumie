package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.controller.services.RelationshipService;
import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

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
            this.matchList = userService.getMatches(userName.orElseThrow(() -> new RuntimeException("Not Initialized")),
                    matchScore.orElseThrow(() -> new RuntimeException("Assessment Never Taken")));
            count++;
        }

        if (count < matchList.size()) {
            count++;
            return matchList.get(count-1);
        } else {
            throw new RuntimeException("Uh oh.... Nothing was found. Did you know matches automatically show up in chat when they like you back?");
        }
    }

    // Should be the first function called, fills the necessary details since we cannot access SecurityContext directly.
    public void init(String userName, int matchScore) {
        this.userName = Optional.ofNullable(userName);
        this.matchScore = Optional.ofNullable(matchScore);
    }
}
