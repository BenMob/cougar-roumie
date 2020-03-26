package com.SE370.Cougar.Roomie.model.assessment;


import javax.persistence.*;

@Entity
@Table(name = "questions") // just to keep things looking uniform in the database
public class Question {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int questionId;
    private int questionNumber;
    private String questionText;
    private int minRange;
    private int maxRange;

    public Question() {
    }

    public Question(int questionNumber, String text, int min, int max) {
        this.questionNumber = questionNumber;
        this.questionText = text;
        this.minRange = min;
        this.maxRange = max;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }
}
