package server.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Database {

    private final HashMap<String, Theme> themes;
    private final HashMap<String, User> users;
    private final HashMap<String, Board> Boards;

    Database() {

        users = new HashMap<>();
        themes = new HashMap<>();
        Boards = new HashMap<>();

        // hard-coded defined themes
        createTheme("Tele-Datanet");
        createTheme("Programmering");
        createTheme("Matematik");
        createTheme("Digital-Kommunikation");
    }

    private void createTheme(String id) {
        Theme theme = new Theme(id);
        themes.put(id, theme);
        System.out.println(themes);
    }

    Board CreateBoard(String id, String user1, String user2, Theme theme) {

        Board board = new Board(this.getUser(user1), this.getUser(user2), this, theme);
        System.out.println(this.getUser(user1) + "" + this.getUser(user2) + "" + theme);
        Boards.put(id, board);
        return board;
    }

    boolean newUser(String username, String password) {

        if (users.containsKey(username)) {
            return false;
        }
        User user = new User(username, password);
        users.put(username, user);
        FileReader.createUserFile(user);
        System.out.println("user created: " + username + " " + password);
        return true;
    }

    boolean login(String username, String password) {

        if (!users.containsKey(username)) {
            System.out.println("User does not exist!");
            return false;
        }
        if (!users.get(username).getPassword().equals(password)) {
            System.out.println("Wrong password! " + users.get(username).getPassword() + " " + password);
            return false;
        }
        return true;
    }

    HashMap<String, User> getUsers() {
        return users;
    }

    User getUser(String s) {
        return users.get(s);
    }

    Theme getTheme(String s) {
        System.out.println("string    " + s + "\n" + themes.get(s));
        return themes.get(s);
    }

    List<String> getBoards() {
        return new ArrayList<>(this.Boards.keySet());
    }

    Board getBoard(String s ){
        return Boards.get(s);
    }

}
