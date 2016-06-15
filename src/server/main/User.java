package server.main;

class User {

    private String name,password;

    User(String username,String pass){
        this.name = username;
        this.password = pass;

    }

    String getUsername(){
        return name;
    }

    String getPassword(){
        return password;
    }

}
