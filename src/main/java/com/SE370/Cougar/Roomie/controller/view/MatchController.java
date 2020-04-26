package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.MatchForm;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MatchController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @GetMapping("user/matchmaking")
    public String presentMatchmaking(WebRequest request, Model model) {
        logger.info(String.valueOf(((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMatchScore()));
        model.addAttribute("users", userService.getMatches());
        return "matchmaking";
    }

    @PostMapping("user/submitmatch")
    public String submitMatch(
            @RequestParam(value = "like", required = false) String likeFlag,
            @RequestParam(value = "dislike", required = false) String dislikeFlag,
            @ModelAttribute UserInfo user
            ) {
        if (likeFlag != null) {
            logger.info("user liked " + user.getUserName());
        } else if (dislikeFlag != null) {
            logger.info("user disliked " + user.getUserName());
        } else {
            logger.info("Error in selection");
        }
        return "redirect:matchmaking";
    }
}
