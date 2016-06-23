package server.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Entrance {

    Database database;

    private ServerSocket server = null;


    public static void main(String[] args){

        Entrance entrance = new Entrance();
        entrance.listen();

    }

    private Entrance(){
        this.database = new Database();

        try {
            FileReader.loadUsers(database);
            FileReader.loadQuestions(database);
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
                active = false;
                System.out.println("Could not connect user");
            }
            ThreadListen thread = new ThreadListen(clientSocket,this);
            thread.start();

        }
    }
}
