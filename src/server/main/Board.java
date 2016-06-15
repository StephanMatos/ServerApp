package server.main;

/**
 * Created by Mwthorn on 07-06-2016.
 */
public class Board {

    private User user1,user2;
    private boolean user1answered, user2answered, AcceptedInvitation;
    private int points1,points2;
    private Theme theme;
    private Question currentQuestion;
    private Database database;
    private int count1,count2;


    public Board(User user1, User user2,Database database, Theme theme) {
        this.user1 = user1;
        this.user2 = user2;
        this.user1answered = false;
        this.user2answered = false;
        this.points1 = 0;
        this.points2 = 0;
        this.database = database;
        this.theme = theme;
        this.currentQuestion = null;

    }

    public void changeTheme(String newtheme) {
        this.theme = database.getTheme(newtheme);
    }

    public Question setRandomQuestion() {

        System.out.println(database.getThemes()+" SetrandomQuestion 1");

        System.out.println(theme.getTitle()+"lalalalalalalalalalalaallalalalaall");

        System.out.println(theme.getRandomQuestion()+"Denne er null");

        this.currentQuestion = theme.getRandomQuestion();
        return this.currentQuestion;
    }

    public void setInvitation(boolean b) {
        this.AcceptedInvitation = b;
    }

    public boolean hasInvitation() {
        return AcceptedInvitation;
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

    public Integer Point1(){
        return points1;
    }
    public Integer Point2(){
        return points2;
    }

    public void resetAnsweredUsers() {
        this.user1answered = false;
        this.user2answered = false;
    }

    public void setAnsweredUser1(Boolean b) {
        this.user1answered = b;
    }

    public void setAnsweredUser2(Boolean b) {
        this.user2answered = b;
    }

    public boolean getansweredUser2() {
        return this.user2answered;
    }

    public boolean getansweredUser1() {
        return this.user1answered;
    }

    public Theme getTheme() {
        return this.theme;
    }

    public void userAnswered(User user) {
        if(user.equals(user1)){
            this.user1answered = true;
        }
        else {
            this.user2answered = true;
        }
    }

    public int getuser1Points() {
        return points1;
    }

    public int getuser2Points() {
        return points2;
    }
}
