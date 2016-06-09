package server.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static void loadUsers(Database database) throws FileNotFoundException {
        File folder = new File("data/users/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> ChatIds = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                String user = file.getName().substring(0,file.getName().length()-4);
                Scanner sc = new Scanner(file);
                String pass = sc.nextLine();
                database.newUser(user, pass);
                sc.close();
            }
        }
        //return BuildClients;
    }

    public static void loadQuestions(Database database) throws FileNotFoundException {
        File folder = new File("data/questions/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> ChatIds = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                String fileName = file.getName().substring(0,file.getName().length()-4) + " ";
                Scanner sc = new Scanner(file);
                String theme = sc.nextLine();
                String question = sc.nextLine();
                String answer1 = sc.nextLine();
                String answer2 = sc.nextLine();
                String answer3 = sc.nextLine();
                String answer4 = sc.nextLine();
                database.getTheme(theme).makeQuestion(question, answer1, answer2, answer3, answer4, fileName);
                sc.close();
            }
        }
        //return BuildClients;
    }

    public static void createUserFile(User user) {
        String ID = user.getUsername() + ".txt";
        //String ID = new TimeStamp().toString();
        File chatFile = new File("data/users/" + ID);
        try {
            if (chatFile.createNewFile()) {
                System.out.println("Successfully created new file:" + ID);
            };
        } catch (IOException e) {
            System.out.println("Failed creating file:" + ID);
        }
        try {
            FileWriter outFile = new FileWriter(chatFile, true);
            PrintWriter out = new PrintWriter(outFile);

            out.println(user.getPassword());

            out.close();
            outFile.close();

        }
        catch (Exception e) {
            System.out.println("Failed creating file:" + ID);
        }
    }
}
