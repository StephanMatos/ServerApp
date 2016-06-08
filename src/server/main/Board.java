package server.main;

/**
 * Created by Mwthorn on 07-06-2016.
 */
public class Board {

    private User user1;
    private User user2;
    private int points1;
    private int points2;
    private Theme theme;
    private Question currentQuestion;
    private Database database;


    public Board(User user1, User user2,Database database, Theme theme) {
        this.user1 = user1;
        this.user2 = user2;
        this.points1 = 0;
        this.points2 = 0;
        this.database = database;
    }

    public Question setRandomQuestion() {

        this.currentQuestion = database.getThemes().get(this.theme).getRandomQuestion();

        return this.currentQuestion;
    }

    public Question setNextQuestion(){

        return setRandomQuestion();
    }

    public void userAnswered(String answer) {
        if (this.currentQuestion.checkAnswer(answer)) {
            // TODO give point
        }
        else {
            // TODO prevent any further answers
        }
    }

    public Question getCurrentQuestion(){
        return currentQuestion;
    }

    public User getUser1(){
        return user1;
    }

    public User getUser2(){
        return  user2;
    }

    public Integer setScore(User user){
        if(user.equals(user1)){
            points1++;
        }
        if(user.equals(user2)){
            points2++;
        }
        return points1+points2;

    }

}
