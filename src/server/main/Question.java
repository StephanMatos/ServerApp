package server.main;

import java.util.ArrayList;
import java.util.Collections;

class Question {

    private String question, answer1,answer2,answer3,answer4;

    Question(String q,String a1, String a2, String a3, String a4){

        this.question = q;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;

    }

    ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(this.answer1);
        answers.add(this.answer2);
        answers.add(this.answer3);
        answers.add(this.answer4);
        Collections.shuffle(answers);
        return answers;
    }

    String getQuestion() {
        return this.question;
    }

    String getRightAnswer() {
        return this.answer1;
    }

}
