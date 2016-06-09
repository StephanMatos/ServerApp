package server.main;

import java.util.HashMap;

public class Database {

    private String username;

    private HashMap<String, Theme> themes;
    private HashMap<String, User> users;
    private HashMap<String,Board> Boards;

    public Database(){

        users = new HashMap<String, User>();
        themes = new HashMap<String, Theme>();
        Boards = new HashMap<String,Board>();

        // Pre defineret teamer, brugere og spørgsmål
        createTheme("Matematik 1");
        createTheme("Digital");
        createTheme("Programmering");

    }

    public Theme createTheme(String id){

        Theme theme = new Theme(id,this);
        themes.put(id,theme);

        return theme;
    }

    public Board CreateBoard(String id, String user1, String user2, String theme){

        Board board = new Board(this.getUser(user1),this.getUser(user2),this,this.getTheme(theme));
        Boards.put(id,board);
        return board;
    }

    public boolean newUser(String username,String password){

        if(users.containsKey(username)){
            return false;
        }
        User user = new User(username,password);
        users.put(username,user);
        FileReader.createUserFile(user);
        System.out.println("user created: " + username + " " + password);
        return true;
    }

    public HashMap<String, Theme> getThemes() {
        return this.themes;
    }
    public boolean login(String username, String password){

        if(!users.containsKey(username)){
            System.out.println("User does not exist!");
            return false;
        }
        if (users.get(username).getPassword().equals(password)) {
            System.out.println("Wrong password! " + users.get(username).getPassword() + " " + password);
            return false;
        }

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
