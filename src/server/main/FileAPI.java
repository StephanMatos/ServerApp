/*
package server.main;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class FileAPI {

    //Map<String, YamlConfiguration> yamls = new HashMap<String, YamlConfiguration>();
    private static String setupQT, setupQuestion, setupQuestionAnswer1, setupQuestionAnswer2, setupQuestionAnswer3, setupQuestionAnswer4;
    private static String setupUser, setupPass;
    private static Theme theme;

    public static void loadQuestions(String fileName, Database database) throws FileNotFoundException {
        Yaml yaml = new Yaml();

        //System.out.println(yaml.dump(yaml.load(new FileInputStream(new File(fileName)))));

        Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml
                .load(new FileInputStream(new File(fileName)));

        for (String key : values.keySet()) {
            Map<String, String> subValues = values.get(key);
            //System.out.println(key);

            for (String subValueKey : subValues.keySet()) {
                //System.out.println(String.format("\t%s === %s", subValueKey, subValues.get(subValueKey)));
                switch (subValueKey) {
                    case "theme": {
                        setupQT = subValues.get(subValueKey);
                        break;
                    }
                    case "question": {
                        setupQuestion = subValues.get(subValueKey);
                        break;
                    }
                    case "answer1": {
                        setupQuestionAnswer1 = subValues.get(subValueKey);
                        break;
                    }
                    case "answer2": {
                        setupQuestionAnswer2 = subValues.get(subValueKey);
                        break;
                    }
                    case "answer3": {
                        setupQuestionAnswer3 = subValues.get(subValueKey);
                        break;
                    }
                    case "answer4": {
                        setupQuestionAnswer4 = subValues.get(subValueKey);
                        break;
                    }
                    default: {
                        System.out.println("Error: " + subValueKey);
                        break;
                    }
                }
            }
            if (!database.getThemes().containsKey(setupQT)) {
                theme = database.createTheme(setupQT);
            }
            theme.makeQuestion(setupQuestion, setupQuestionAnswer1, setupQuestionAnswer2, setupQuestionAnswer3, setupQuestionAnswer4, key);

        }

    }

    public static void loadUsers(String fileName, Database database) throws FileNotFoundException {
        Yaml yaml = new Yaml();

        //System.out.println(yaml.dump(yaml.load(new FileInputStream(new File(fileName)))));

        Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml
                .load(new FileInputStream(new File(fileName)));

        for (String key : values.keySet()) {
            Map<String, String> subValues = values.get(key);
            //System.out.println(key);

            for (String subValueKey : subValues.keySet()) {
                //System.out.println(String.format(subValueKey + " == " + subValues.get(subValueKey)));

                String stringValue = subValueKey;
                switch (stringValue) {
                    case "username": {
                        setupUser = subValues.get(subValueKey);
                        break;
                    }
                    case "password": {
                        setupPass = subValues.get(subValueKey);
                        break;
                    }
                    default: {
                        System.out.println("Error: " + stringValue);
                        break;
                    }
                }
            }
            database.newUser(setupUser, setupPass);
        }

    }

}

*/