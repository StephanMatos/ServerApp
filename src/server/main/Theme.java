package server.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class Theme {

    private String title;

    private HashMap<String, Question> questionHashMap;


    Theme(String id){

        this.title = id;
        questionHashMap = new HashMap<>();


    }

    void makeQuestion(String q, String a1,String a2,String a3, String a4,String id){

        Question question = new Question(q,a1,a2,a3,a4);
        questionHashMap.put(id,question);

    }

    String getTitle() {
        return this.title;
    }

    Question getRandomQuestion() {

        List<String> keysAsArray = new ArrayList<>(this.questionHashMap.keySet());
        Random r = new Random();

        return this.questionHashMap.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
    }

}
