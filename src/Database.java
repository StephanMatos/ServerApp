import java.util.HashMap;

/**
 * Created by Stephan on 06-06-2016.
 */
public class Database {

    private String user;

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

    public boolean newUser(String username, User userclass){

        this.user = username;
        if(users.containsKey(username)){
            return false;
        }
        users.put(user,userclass);

        return true;

    }

}
