package server.main;

/**
 * Created by Mwthorn on 07-06-2016.
 */
public class Board {

    private User user1,user2;
    private boolean user1answered, user2answered;
    private int points1,points2;
    private Theme theme;
    private Question currentQuestion;
    private Database database;


    public Board(User user1, User user2,Database database, Theme theme) {
        this.user1 = user1;
        this.user2 = user2;
        this.user1answered = false;
        this.user2answered = false;
        this.points1 = 0;
        this.points2 = 0;
        this.database = database;
    }

    public void changeTheme(String newtheme) {
        this.theme = database.getTheme(newtheme);
    }

    public Question setRandomQuestion() {
        this.currentQuestion = database.getThemes().get(this.theme).getRandomQuestion();
        return this.currentQuestion;
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

    public Integer givePoint(User user){
        if(user.equals(user1)){
            if (!user1answered) {
                points1++;
            }
            return points1;
        }
        else {
            if (!user2answered) {
                points2++;
            }
            points2++;
            return points2;
        }

    }

    public void resetAnsweredUsers() {
        this.user1answered = false;
        this.user2answered = false;
    }
}
