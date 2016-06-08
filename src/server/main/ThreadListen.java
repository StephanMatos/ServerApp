package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Stephan on 06-06-2016.
 */
public class ThreadListen extends Thread {

    private User user;
    private BufferedReader buf;
    private PrintWriter pw;
    private boolean active;
    private Entrance entrance;
    private Board currentBoard;
    private String id;

    public ThreadListen( Socket clientSocket, Entrance entrance){
        this.entrance = entrance;


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
                switch (firstMessage) {
                    case "REGISTER": {
                        String username = buf.readLine();
                        String password = buf.readLine();
                        entrance.database.newUser(username, password);
                        pw.println("OK");
                        pw.flush();
                        break;
                    }
                    case "LOGIN": {
                        String username = buf.readLine();
                        String password = buf.readLine();
                        System.out.println(username+password);
                        entrance.database.login(username,password);
                        pw.println("OK");
                        pw.flush();
                        System.out.println("Hej med dig jeg virkede");
                        break;
                    }
                    case "REFRESH": {
                        // TODO something here
                        break;
                    }
                    case "INVITE": {
                        String theme = buf.readLine();
                        String user2 = buf.readLine();
                        if(!entrance.database.getUsers().containsKey(user2)){
                            pw.println("User does not exist");
                            pw.flush();

                        }else{
                            this.currentBoard = new Board(user,entrance.database.getUser(user2),entrance.database,entrance.database.getTheme(theme));
                            String id = String.valueOf(System.currentTimeMillis());
                            entrance.database.CreateBoard(id,currentBoard);
                            pw.println("OK");
                            pw.flush();
                            Question question = currentBoard.setRandomQuestion();
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
                        }
                        break;
                    }
                    case "THEME":{
                        // TODO something here
                        break;
                    }
                    case "ANSWER":{
                        String answer = buf.readLine();
                        if(currentBoard.getCurrentQuestion().checkAnswer(answer)) {
                            currentBoard.setScore(user);
                        }
                        break;
                    }
                    case "NEXT_QUESTION":{

                        Question question = currentBoard.setNextQuestion();

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

                        break;
                    }
                    default: {
                        System.out.println("Should have recieved type but got: " + firstMessage);
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println("e");
            }



        }
    }
}

