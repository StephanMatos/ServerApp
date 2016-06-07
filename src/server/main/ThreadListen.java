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
                switch (firstMessage) {
                    case "REGISTER":
                        // TODO something here
                        break;
                    case "LOGIN":
                        // TODO something here
                        break;
                    case "REFRESH":
                        // TODO something here
                        break;
                    case "INVITE":
                        // TODO something here
                        break;
                    case "THEME":
                        // TODO something here
                        break;
                    case "ANSWER":
                        // TODO something here
                        break;
                    default:
                        // TODO something here
                        break;
                }

            } catch(IOException e){
                System.out.println("e");
            }



        }

    }
}
