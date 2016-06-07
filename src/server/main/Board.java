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

    public Board(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.points1 = 0;
        this.points2 = 0;
    }

    public void setRandomQuestion() {
        this.currentQuestion = entrance.database.getThemes().get(this.theme).getRandomQuestion();
        String message = this.currentQuestion.getQuestion();
        for (String answer : this.currentQuestion.getAnswers()) {
            message = message + "§" + answer;
        }
        // Opretteren starter

        // TODO Første svare den ene det spørgsmål også den anden
    }

    public void userAnswered(String answer) {
        if (this.currentQuestion.checkAnswer(answer)) {
            // TODO give point
        }
        else {
            // TODO prevent any further answers
        }
    }

}
