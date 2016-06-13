package server.main;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Theme {

    private Random randomGenerator;
    private String title;

    private HashMap<String, Question> questionHashMap;


    public Theme(String id, Database database){

        this.title = id;
        questionHashMap = new HashMap<String, Question>();


    }

    public void makeQuestion(String q, String a1,String a2,String a3, String a4,String id){

        Question question = new Question(q,a1,a2,a3,a4,this);
        questionHashMap.put(id,question);

    }

    public String getTitle() {
        return this.title;
    }




    public Question getRandomQuestion() {

        List<String> keysAsArray = new ArrayList<String>(this.questionHashMap.keySet());
        Random r = new Random();

        return this.questionHashMap.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
    }

}
