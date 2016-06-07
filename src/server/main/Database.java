package server.main;

import java.util.HashMap;
import java.util.StringJoiner;

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

    public Theme createTheme(String id){

        Theme theme = new Theme(id,this);
        themes.put(id,theme);

        return theme;
    }

    public boolean newUser(String username,String password){

        if(users.containsKey(username)){
            return false;
        }
        User user = new User(username,password);
        users.put(username,user);
        System.out.println("user is created");
        return true;
    }

    public HashMap<String, Theme> getThemes() {
        return this.themes;
    }
    public boolean login(String username, String password){

        if(users.get(username).getPassword().equals(password)){
            return true;
        }

        System.out.println("user does not exist/Wrong password");
        return false;

    }


}
