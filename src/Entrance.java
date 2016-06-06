import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Entrance {

    private Database database;

    private ServerSocket welcome = null;


    public static void main(String[] args){

        Entrance entrance = new Entrance();

    }

    public Entrance(){
        this.database = new Database();
        try{
            welcome = new ServerSocket(2048);


        }catch (IOException e){
            System.out.println("could not establish a server socket");
        }
    }

    private void listen(){
        boolean active = true;

        while(active){
            Socket clientSocket = null;

            try{
                clientSocket = welcome.accept();


            }catch (IOException e){
                System.out.println("Could not connect user");
            }

        }


    }

}
