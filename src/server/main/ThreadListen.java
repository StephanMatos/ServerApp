package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Stephan on 06-06-2016.
 */
public class ThreadListen extends Thread {

    private User user;
    private BufferedReader buf;
    private boolean active;
    private Entrance parent;


    public ThreadListen( Socket clientSocket, Entrance entrance){
        this.parent = entrance;

        this.active = true;
        try {
            this.buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Thread i active");
    }

    public void run(){
        while(active){
            try{
                String firstMessage = buf.readLine();

                if(firstMessage.equals("REGISTER")){
                    String username = buf.readLine();
                    String password = buf.readLine();
                    parent.database.newUser(username,password);


                }
                else if(firstMessage.equals("LOGIN")){

                }else if(firstMessage.equals("REFRESH")){

                }else if(firstMessage.equals("INVITE")){

                }else if(firstMessage.equals("THEME")){

                }else if(firstMessage.equals("ANSWER")){

                }

            } catch(IOException e){
                System.out.println("e");
            }



        }

    }
}
