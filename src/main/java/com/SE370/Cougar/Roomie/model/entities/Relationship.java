package com.SE370.Cougar.Roomie.model.entities;

import javax.persistence.*;

@Entity(name = "Relationship")
@Table(name = "relationships")
public class Relationship {
    //#############################################################################
    @Id
    @GeneratedValue
    private Long relationship_id;
    private int user_one_id;
    private int user_two_id;
    private int action_user_id;
    private int status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//#########################################################
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id")
    /*@JoinColumns({
            @JoinColumn(name = "use_one_id"),
            @JoinColumn(name = "user_two_id"),
            @JoinColumn(name = "action_user_id")})*/
    private User user;

    public Relationship(){};
    public Relationship(int user1_id, int user2_id, int action_user_id, int status ){
        this.setUser_one_id(user1_id);
        this.setUser_two_id(user2_id);
        this.setStatus(status);
        this.setAction_user_id(action_user_id);
    };

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;
        return (this.relationship_id != null && relationship_id.equals(((Relationship) o).getRelationship_id()));
    }

    @Override
    public int hashCode() {
        return 31;
    }
*/
    public int getUser_one_id() {
        return this.user_one_id;
    }

    public long getRelationship_id() {
        return this.relationship_id;
    }

    public void setUser_one_id(int user_one_id) {
        this.user_one_id = user_one_id;
    }

    public int getUser_two_id() {
        return user_two_id;
    }

    public void setUser_two_id(int user_two_id) {
        this.user_two_id = user_two_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAction_user_id() {
        return action_user_id;
    }

    public void setAction_user_id(int action_user_id) {
        this.action_user_id = action_user_id;
    }
}
