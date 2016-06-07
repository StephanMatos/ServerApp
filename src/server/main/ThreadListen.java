package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stephan on 06-06-2016.
 */
public class ThreadListen extends Thread {

    private User user;
    private BufferedReader buf;
    private PrintWriter pw;
    private boolean active;
    private Entrance entrance;

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
                        // TODO something here
                        break;
                    }
                    case "THEME": {
                        // TODO something here
                        break;
                    }
                    case "ANSWER": {
                        String answer = buf.readLine();
                        break;
                    }
                    case "NEXT_QUESTION": {

                        break;
                    }
                    default: {
                        // TODO something here
                        break;
                    }
                }

            } catch(IOException e){
                System.out.println("e");
            }



        }

    }
}
