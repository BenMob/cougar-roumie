package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.components.ChatComponent;
import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.Message;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private ChatComponent chatComponent;
    @Autowired
    SimpMessageSendingOperations messsageOperations;
    @Autowired
    UserService userService;

    @GetMapping("/user/chat")
    public String chat(Model model, Authentication auth) {
        model.addAttribute("users", userService.getAllUsers());
        return "chat";
    }

    @MessageMapping("/updates.getConversation")
    public void getOldMessages(@Payload Message message, Authentication authentication) throws RuntimeException {
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        chatComponent.setThisUserID(Optional.ofNullable(details.getUser_id()));
        chatComponent.setThisUserName(Optional.ofNullable(details.getUsername()));
        chatComponent.setOtherUserName(Optional.ofNullable(message.getReciever()));

        // check if username is set if it is, get messages and for each one send it to the requesting user
        chatComponent.getThisUserName()
                .ifPresent(userName -> {
                    chatComponent.getOldMessages().stream()
                            .forEach(msg -> {
                                messsageOperations.convertAndSendToUser(userName, "/queue/updates", msg);
                            });
                });
    }

    @MessageMapping("/updates")
    public void sendMessage(@Payload Message chatMessage) {

        chatComponent.getThisUserName().ifPresent(userName -> {
            chatComponent.getOtherUserName().ifPresent(otherUser -> {
                chatMessage.setSender(userName);
                chatMessage.setReciever(otherUser);
                chatComponent.saveMessage(chatMessage);

                // Send to other user
                this.messsageOperations.convertAndSendToUser(otherUser, "/queue/updates", chatMessage);
                // Send the message to logged in user
                this.messsageOperations.convertAndSendToUser(userName, "/queue/updates", chatMessage);
            });
        });
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }
}
