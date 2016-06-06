package server.main;

/**
 * Created by Stephan on 06-06-2016.
 */
public class User {

    private String name,password;

    public User(String username,String pass){
        this.name = username;
        this.password = pass;

    }

    public String getUsername(){
        return name;
    }
    public String getPassword(){

        return password;
    }
}
