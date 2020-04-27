package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MatchmakerComponent {

    @Autowired
    UserService userService;

    private List<UserInfo> matchList;
    private Optional<String> userName;
    private Optional<Integer> matchScore;
    private int count = -1;

    public UserInfo getMatch() throws RuntimeException {
        if (count == -1) {
            this.matchList = userService.getMatches(userName.orElseThrow(() -> new RuntimeException("Not Initialized")),
                    matchScore.orElseThrow(() -> new RuntimeException("Assessment Never Taken")));
            count++;
        }

        if (count < matchList.size()) {
            count++;
            return matchList.get(count-1);
        } else {
            throw new RuntimeException("No matches found...");
        }

    }

    public void init(String userName, Integer matchScore) {
        this.userName = Optional.ofNullable(userName);
        this.matchScore = Optional.ofNullable(matchScore);
    }
}
