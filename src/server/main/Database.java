package server.main;

import java.util.HashMap;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Database {

    private String username;
    private User user;

    private HashMap<String, Theme> themes;
    private HashMap<String, User> users;



    public Database(){


        users = new HashMap<String, User>();
        themes = new HashMap<String, Theme>();

    }

    public void createTheme(String id){

        Theme theme = new Theme(id,this);
        themes.put(id,theme);

    }

    public boolean newUser(String username,String password){

        if(users.containsKey(username)){
            return false;
        }
        User user = new User(username,password);
        users.put(username,user);
        return true;
    }


}
