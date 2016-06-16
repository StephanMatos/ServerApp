package server.main;

class Board {

    private final User user1;
    private final User user2;
    private boolean user1answered, user2answered, AcceptedInvitation;
    private int points1,points2, counter1, counter2;
    private Theme theme;
    private Question currentQuestion;
    private final Database database;

    Board(User user1, User user2,Database database, Theme theme) {
        this.user1 = user1;
        this.user2 = user2;
        this.user1answered = false;
        this.user2answered = false;
        this.points1 = 0;
        this.points2 = 0;
        this.counter1 = 0;
        this.counter2 = 0;
        this.database = database;
        this.theme = theme;
        this.currentQuestion = null;
    }

    void changeTheme(String newTheme) {
        this.theme = database.getTheme(newTheme);
    }

    Question setRandomQuestion() {
        this.currentQuestion = theme.getRandomQuestion();
        return this.currentQuestion;
    }

    void setInvitation(boolean b) {
        this.AcceptedInvitation = b;
    }

    boolean hasInvitation() {
        return AcceptedInvitation;
    }

    Question getCurrentQuestion(){
        return currentQuestion;
    }

    User getUser1(){
        return user1;
    }

    User getUser2(){
        return  user2;
    }

    Integer givePoint(User user){

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

    boolean getUserAnswered(User user) {
        if(user.equals(user1)){
            return this.user1answered;
        }
        else {
            return this.user2answered;
        }
    }

    int getPoints1() {
        return this.points1;
    }

    int getPoints2() {
        return this.points2;
    }

    Theme getTheme() {
        return this.theme;
    }

    void userAnswered(User user) {
        if(user.equals(user1)){
            this.user1answered = true;
        }
        else {
            this.user2answered = true;
        }
    }

    void setAnsweredUser(User user, boolean b) {
        if(user.equals(user1)){
            this.user1answered = b;
        }
        else {
            this.user2answered = b;
        }
    }

    void increaseCounter(User user) {
        if(user.equals(user1)){
            this.counter1++;
        }
        else {
            this.counter2++;
        }
    }

    int getCounter(User user) {
        if(user.equals(user1)){
            return this.counter1;
        }
        else {
            return this.counter2;
        }
    }
}
