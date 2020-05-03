package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.UserInfo;
import com.SE370.Cougar.Roomie.model.entities.Relationship;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.RelationshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {

    @Autowired
    UserService userService;

    @Autowired
    RelationshipRepo relationshipRepo;

    public void createRelationship(CustomUserDetails thisUser, UserInfo found){

        User user = new User(thisUser);
        int status = 0;
        Relationship rel = new Relationship(user.getId(), found.getId(), status, user.getId(), user);
        relationshipRepo.save(rel);
    }


}
