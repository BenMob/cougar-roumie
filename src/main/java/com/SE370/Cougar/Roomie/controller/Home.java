package com.SE370.Cougar.Roomie.controller;

import com.SE370.Cougar.Roomie.model.assessment.Answer;
import com.SE370.Cougar.Roomie.model.assessment.AnswerRepo;
import com.SE370.Cougar.Roomie.model.assessment.Question;
import com.SE370.Cougar.Roomie.model.assessment.QuestionRepo;
import com.SE370.Cougar.Roomie.model.message.Conversation;
import com.SE370.Cougar.Roomie.model.message.ConversationRepo;
import com.SE370.Cougar.Roomie.model.message.Message;
import com.SE370.Cougar.Roomie.model.message.MessageRepo;
import com.SE370.Cougar.Roomie.model.user.User;
import com.SE370.Cougar.Roomie.model.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    @Autowired
    AnswerRepo AnswerRepo;
    @Autowired
    QuestionRepo QuestionRepo;
    @Autowired
    ConversationRepo ConversationRepo;
    @Autowired
    MessageRepo MessageRepo;
    @Autowired
    UserRepo UserRepo;



    // Templates need to be made, for now these are test cases to talk to database
    @GetMapping("/getconversations")
    public List<Conversation> t1() {
        List<Conversation> conversations = ConversationRepo.findAll();
        return conversations;
    }

    @GetMapping("/getquestions")
    public List<Question> t2() {
        List<Question> questions = QuestionRepo.findAll();
        return questions;
    }

    @PostMapping("/getansbyuser")
    public Answer t3(@RequestBody test Test) {
        Answer ans = AnswerRepo.findByUserId(Test.getId());
        return ans;
    }
    @GetMapping("/getusers")
    public List<User> t4() {
        List<User> users = UserRepo.findAll();
        return users;
    }
    @PostMapping("/getmsgbyconversation")
    public List<Message> t5(@RequestBody test Test) {
        List<Message> msgs = MessageRepo.findAllByConversationId(Test.getId());
        return msgs;
    }

}
