package com.SE370.Cougar.Roomie.Models;

import java.util.List;

public class Assessment {

    private List<Question> questions;

    // Constructor
    public Assessment(List <Question> questionList){
        this.questions = questionList;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    // Setter and Getter
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
