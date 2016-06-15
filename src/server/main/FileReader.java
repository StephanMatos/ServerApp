package server.main;

import java.io.*;
import java.util.Scanner;

class FileReader {
    static void loadUsers(Database database) throws FileNotFoundException {
        File folder = new File("data/users/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
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
        }
        else {
            System.out.println("No files found in: /data/users/");
        }
    }

    static void loadQuestions(Database database) throws FileNotFoundException {
        File folder = new File("data/questions/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    String fileName = file.getName().substring(0, file.getName().length() - 4) + " ";
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
        }
        else {
            System.out.println("No files found in: /data/questions/");
        }
    }

    static void createUserFile(User user) {
        String ID = user.getUsername() + ".txt";
        if (new File("data/users/" + ID).exists()) {
            return;
        }
        File chatFile = new File("data/users/" + ID);
        try {
            FileWriter outFile = new FileWriter(chatFile, true);
            PrintWriter out = new PrintWriter(outFile);

            out.println(user.getPassword());

            out.close();
            outFile.close();

            if (chatFile.createNewFile()) {
                System.out.println("Successfully created new file:" + ID);
            }
        } catch (IOException e) {
            System.out.println("Failed creating file:" + ID);
        }

    }
}
