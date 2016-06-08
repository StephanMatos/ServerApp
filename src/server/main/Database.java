package server.main;

import java.util.HashMap;

public class Database {

    private String username;
    private User user;

    private HashMap<String, Theme> themes;
    private HashMap<String, User> users;
    private HashMap<String,Board> Boards;


    public Database(){

        users = new HashMap<String, User>();
        themes = new HashMap<String, Theme>();
        Boards = new HashMap<String,Board>();

        user = new User("frederik","1234");
        users.put("frederik",user);
        System.out.println(users);

    }

    public Theme createTheme(String id){

        Theme theme = new Theme(id,this);
        themes.put(id,theme);

        return theme;
    }

    public void CreateBoard(String id, Board board){

        Boards.put(id,board);

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

        if(!users.containsKey(username)){
            System.out.println("user does not exist");
            return false;
        }
        users.get(username).getPassword().equals(password);

        return true;
    }

    public HashMap<String, User> getUsers(){
        return users;
    }

    public User getUser(String s){
        return users.get(s);
    }

    public Theme getTheme(String s){
        return themes.get(s);
    }

    public Board getBoard(String s ){
        return Boards.get(s);
    }

}
