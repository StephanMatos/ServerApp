package server.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Entrance {

    public Database database;

    private ServerSocket server = null;


    public static void main(String[] args){

        Entrance entrance = new Entrance();
        entrance.listen();

    }

    public Entrance(){
        this.database = new Database();

        try {
            FileAPI.loadQuestions("data/questions.yml", database);
            FileAPI.loadUsers("data/users.yml", database);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            ThreadListen thread = new ThreadListen(clientSocket,this);
            thread.start();

        }
    }
}
