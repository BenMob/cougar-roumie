package com.SE370.Cougar.Roomie.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "answers") // just to keep things looking uniform in the database
public class Answer {
    @Id
    private int userId;
    private int answer1;
    private int answer2;
    private int answer3;
    private int answer4;
    private int answer5;
    private int answer6;
    private int answer7;
    private float score;

    public Answer() {
    }

    public Answer(int user, int a1, int a2, int a3, int a4, int a5, int a6, int a7) {
        this.userId = user;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;
        this.answer5 = a5;
        this.answer6 = a6;
        this.answer7 = a7;
        this.score = (answer1 + answer2 + answer3 + answer4 + answer5 + answer6 + answer7)/7;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnswer1() {
        return answer1;
    }

    public void setAnswer1(int answer1) {
        this.answer1 = answer1;
    }

    public int getAnswer2() {
        return answer2;
    }

    public void setAnswer2(int answer2) {
        this.answer2 = answer2;
    }

    public int getAnswer3() {
        return answer3;
    }

    public void setAnswer3(int answer3) {
        this.answer3 = answer3;
    }

    public int getAnswer4() {
        return answer4;
    }

    public void setAnswer4(int answer4) {
        this.answer4 = answer4;
    }

    public float getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
