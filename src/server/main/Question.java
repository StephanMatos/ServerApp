package server.main;

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

}
