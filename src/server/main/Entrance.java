package server.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Entrance {

    private static Database database;

    private ServerSocket server = null;


    public static void main(String[] args){

        database = new Database();
        try {
            FileAPI.loadQuestionsFile("data/questions.yml", database);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Entrance entrance = new Entrance();

    }

    public Entrance(){
        try{
            server = new ServerSocket(2048);

        }catch (IOException e){
            System.out.println("could not establish a server socket");
        }
    }

    private void listen(){
        boolean active = true;

        while(active){
            Socket clientSocket = null;

            try{
                clientSocket = server.accept();
                System.out.println("User connected");
            }catch (IOException e){
                System.out.println("Could not connect user");
            }
            ThreadListen thread = new ThreadListen(clientSocket);
            thread.start();

        }
    }
}
