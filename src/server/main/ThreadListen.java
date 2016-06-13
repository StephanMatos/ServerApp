package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;


public class ThreadListen extends Thread {

    private User user;
    private BufferedReader buf;
    private PrintWriter pw;
    private boolean active;
    private Entrance entrance;
    private Board currentBoard;
    private String id;
    private Socket ClientSocket;
    private Theme thema;

    public ThreadListen( Socket clientSocket, Entrance entrance){
        this.entrance = entrance;
        this.ClientSocket = clientSocket;


        this.active = true;
        try {
            this.buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pw = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Thread i active");
    }

    public void run(){
        while(active){
            try{
                String firstMessage = buf.readLine();
                if(firstMessage == null){
                    firstMessage = "LOGOUT";
                }
                System.out.println("Got firstMessage type: " + firstMessage);
                switch (firstMessage) {
                    case "REGISTER": {
                        String username = buf.readLine();
                        String password = buf.readLine();
                        System.out.println("Trying to create user: " + username + " " + password);
                        Boolean parsed = true;
                        if (!entrance.database.newUser(username, password)) {
                            parsed = false;
                        }
                        if (!entrance.database.login(username,password)) {
                            parsed = false;
                        }
                        if (parsed) {
                            System.out.println("Created user: OK!");
                            pw.println("OK");
                            pw.flush();
                        }
                        else {
                            System.out.println("Created user: FAILED!");
                            pw.println("FAIL");
                            pw.flush();
                        }
                        break;
                    }
                    case "LOGIN": {
                        String username = buf.readLine();
                        String password = buf.readLine();
                        System.out.println("Trying to login with: " + username + " " + password);
                        Boolean parsed = true;
                        if (!entrance.database.login(username,password)) {
                            parsed = false;
                        }
                        if (parsed) {
                            System.out.println("Login user: OK!");
                            pw.println("OK");
                            pw.flush();
                        }
                        else {
                            System.out.println("Login user: FAILED!");
                            pw.println("FAIL");
                            pw.flush();
                        }
                        break;
                    }
                    case "MATCH": {
                        // The answer if the player accepted or denied the game invited to.
                        System.out.println("");
                        String answer = buf.readLine();
                        if (Objects.equals(answer, "true")) {
                            System.out.println("User Accepted!");
                        }

                        break;
                    }
                    case "REFRESH": {
                        // TODO something here
                        break;
                    }
                    case "INVITE": {
                        System.out.println("Waiting for theme and user inputs...");
                        String theme = buf.readLine();
                        String user2 = buf.readLine();
                        String user1 = buf.readLine();
                        System.out.println("Got Theme:" + theme + " - User: " + user2+"   User 1    "+user1);
                        if(!entrance.database.getUsers().containsKey(user2)){
                            pw.println("User does not exist");
                            pw.flush();

                        }else{
                            pw.println("OK");
                            pw.flush();
                            String id = String.valueOf(System.currentTimeMillis());
                            System.out.println("Dette er ID"+id+"\n Dette er bruger 1"+user1+"\n Dette er bruger 2"+user2+"\n Dette er tema"+theme);
                            Theme thema = entrance.database.getTheme(theme);
                            this.currentBoard = entrance.database.CreateBoard(id,user1,user2,thema);

                            Question question = currentBoard.setRandomQuestion();
                            String q = question.getQuestion();
                            System.out.println("Sent OK and prepares the question: " + q);
                            ArrayList arrayList = question.getAnswers();
                            String a1 = arrayList.get(0).toString();
                            String a2 = arrayList.get(1).toString();
                            String a3 = arrayList.get(2).toString();
                            String a4 = arrayList.get(3).toString();
                            pw.println(q+"\n"+a1+"\n"+a2+"\n"+a3+"\n"+a4);
                            pw.flush();
                            System.out.println("Question sucessfully sent!");
                        }
                        break;
                    }
                    case "THEME":{
                        String changedTheme = buf.readLine();
                        currentBoard.changeTheme(changedTheme);
                        System.out.println("Successfully changed theme in board to: " + changedTheme);
                        break;
                    }
                    case "ANSWER":{
                        String answer = buf.readLine();
                        System.out.println("Got answer:" + answer);
                        String correctOne = currentBoard.getCurrentQuestion().getRightAnswer();
                        if(Objects.equals(correctOne, answer)) {
                            System.out.println("Correct answer!");
                            currentBoard.givePoint(user);
                        }
                        else {
                            System.out.println("Wrong answer!");
                        }
                        pw.println(correctOne);
                        pw.flush();
                        break;
                    }
                    case "NEXT_QUESTION":{
                        System.out.println("Getting next question! (Could be the same)");
                        Question question = currentBoard.setRandomQuestion();
                        currentBoard.resetAnsweredUsers();
                        String q = question.getQuestion();
                        ArrayList arrayList = question.getAnswers();
                        String a1 = arrayList.get(0).toString();
                        String a2 = arrayList.get(1).toString();
                        String a3 = arrayList.get(2).toString();
                        String a4 = arrayList.get(3).toString();
                        pw.println(q);
                        pw.println(a1);
                        pw.println(a2);
                        pw.println(a3);
                        pw.println(a4);
                        pw.flush();
                        System.out.println("Suceesfully sent question!");
                        break;
                    }
                    case"LOGOUT":{
                        pw.close();
                        buf.close();
                        ClientSocket.close();
                        active = false;
                        System.out.print("System closed");

                        break;
                    }
                    default: {
                        System.out.println("ERROR: Should have received type but got: " + firstMessage);
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println("Connection error!");
                active = false;
                pw.close();
                try {
                    ClientSocket.close();
                } catch (IOException e1) {
                    break;
                }
            }

        }
    }
}

