package com.SE370.Cougar.Roomie.controller.view;
import com.SE370.Cougar.Roomie.controller.components.MatchmakerComponent;
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

    //@Autowired
    //UserService userService;
    @Autowired
    MatchmakerComponent match;
    @Autowired
    SimpMessageSendingOperations messsageOperations;

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);


    @GetMapping("user/matches")
    public String showMatches(Model model) {
        return "matchmaking";
    }


    // This SHOULD be the first message from the client, if its not MatchMakerComponent will throw an error
    @MessageMapping("/matchmaking.initialize")
    public void initialConnect(Authentication auth, @Payload MatchForm submitResult) {
        logger.info("trying to init matchmaking");
        CustomUserDetails user = (CustomUserDetails) ((Authentication) auth).getPrincipal();
        match.init(user.getUsername(), user.getMatchScore());

        submitResult.setType(MatchForm.MessageType.INIT);
        this.messsageOperations.convertAndSendToUser(user.getUsername(), "/queue/matchmaking", submitResult);
    }

    @MessageMapping("/matchmaking.getMatch")
    public void getMatch(Authentication authentication, @Payload MatchForm submitResult) {
        // TODO: Right now all we are doing is logging what the front end does, logic needs to be developed for like/dislike
        switch(submitResult.getType()) {
            case DISLIKE:
                logger.info("Disliked: " + submitResult.getUserName());
                break;
            case LIKE:
                logger.info("Liked: " + submitResult.getUserName());
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
            this.messsageOperations.convertAndSendToUser(authentication.getName(), "/queue/matchmaking", msg);

        } catch (RuntimeException e) {
            MatchForm error = new MatchForm();
            error.setType(MatchForm.MessageType.ERROR);
            error.setUserName(e.getMessage());
            this.messsageOperations.convertAndSendToUser(authentication.getName(),"/queue/matchmaking", error);
        }

    }
}
