package com.SE370.Cougar.Roomie.Models;

/*
This is the Question class

Constructor Parameters:
    1. String: This is the actual question
    2. Set <String> : A set of options (2 to 5 options)
 */

public class Question {

    // Question data
    private String prompt;
    private String[] choices;
    private int numberOfChoices;

    // Constructor
    public Question(String prompt, String[] choices) {

        // Number of Choices Restricted between 2 and 5
        if (validateChoices(choices)) {
            this.prompt = prompt;
            this.numberOfChoices = choices.length;
            this.choices = choices;
        }
    }

    // Validate number of answer choices
    private boolean validateChoices(String[] choices){
        // Min and Max number of answer choices
        byte MIN_CHOICE = 2;
        byte MAX_CHOICE = 5;
        return (choices.length >= MIN_CHOICE && choices.length <= MAX_CHOICE);
    }


    // To update the question prompt
    void updatePrompt(String prompt){
        this.prompt = prompt;
    }

    // To update answer options
    public void updateChoices(String[] newChoices){
        if(validateChoices(newChoices))
            this.choices = newChoices;
    }

    // prompt setter and getter
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }


    // Answer choices setter and getter
    public String[] getChoices() {
        return choices;
    }
    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    // numberOfChoices getter and setter
    public int getNumberOfChoices() {
        return numberOfChoices;
    }
    public void setNumberOfChoices(int numberOfChoices) {
        this.numberOfChoices = numberOfChoices;
    }
}
