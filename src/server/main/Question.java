package server.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Question {

    private String question, answer1,answer2,answer3,answer4;
    private Theme theme;

    public Question(String q,String a1, String a2, String a3, String a4, Theme theme ){

        this.question = q;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;
        this.theme = theme;

    }

    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(this.answer1);
        answers.add(this.answer2);
        answers.add(this.answer3);
        answers.add(this.answer4);
        Collections.shuffle(answers);
        return answers;
    }

    public String getQuestion() {
        return this.question;
    }

    public boolean checkAnswer(String answer) {
        return Objects.equals(answer, this.answer1);
    }

    public String getRightAnswer() {
        return this.answer4;
    }

}
