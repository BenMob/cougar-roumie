package com.SE370.Cougar.Roomie.controller.view;
import com.SE370.Cougar.Roomie.controller.components.MatchmakerComponent;
import com.SE370.Cougar.Roomie.controller.services.RelationshipService;
import com.SE370.Cougar.Roomie.model.DTO.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.MatchForm;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {

    @Autowired
    MatchmakerComponent match;
    @Autowired
    SimpMessageSendingOperations messageOperations;

    @Autowired
    RelationshipService relationshipService;

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);


    @GetMapping("user/matches")
    public String showMatches() {
        return "matchmaking";
    }


    // This SHOULD be the first message from the client, if its not MatchMakerComponent will throw an error
    @MessageMapping("/matchmaking.initialize")
    public void initialConnect(Authentication auth, @Payload MatchForm submitResult) {
        logger.info("trying to init matchmaking");
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        match.init(user.getUsername(), user.getMatchScore());

        submitResult.setType(MatchForm.MessageType.INIT);
        this.messageOperations.convertAndSendToUser(user.getUsername(), "/queue/matchmaking", submitResult);
    }

    @MessageMapping("/matchmaking.getMatch")
    public void getMatch(Authentication auth, @Payload MatchForm submitResult) {
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        // TODO: Right now all we are doing is logging what the front end does, logic needs to be developed for like/dislike
        switch(submitResult.getType()) {
            case DISLIKE:
                logger.info("Disliked: " + submitResult.getUserName());
                break;
            case LIKE:
                logger.info("Liked: " + submitResult.getUserName());
                // TODO: use the methods in relationship service here to decide what happens
                // For now I am calling the startRelationship so you can see how the relationship table gets populated in pgAdmin
                relationshipService.startRelationship(user, submitResult.getUserName(), 1);
                break;
            case REQUEST:
                logger.info("Request Match");
                break;
        }

        // Get match or send error to client...
        try {
            UserInfo found = match.getMatch();
            MatchForm msg = new MatchForm(found);
            msg.setType(MatchForm.MessageType.NEWMATCH);
            this.messageOperations.convertAndSendToUser(auth.getName(), "/queue/matchmaking", msg);

        } catch (RuntimeException e) {
            MatchForm error = new MatchForm();
            error.setType(MatchForm.MessageType.ERROR);
            error.setUserName(e.getMessage());
            this.messageOperations.convertAndSendToUser(auth.getName(),"/queue/matchmaking", error);
        }

    }
}
