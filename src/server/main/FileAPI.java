package server.main;

import org.yaml.snakeyaml.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by Stephan on 06-06-2016.
 */
public class FileAPI {

    //Map<String, YamlConfiguration> yamls = new HashMap<String, YamlConfiguration>();
    private static String setupQT, setupQuestion, setupQuestionAnswer1, setupQuestionAnswer2, setupQuestionAnswer3, setupQuestionAnswer4;
    private static String setupUser, setupPass;
    private static Theme theme;

    public static void loadQuestions(String fileName, Database database) throws FileNotFoundException {
        Yaml yaml = new Yaml();

        System.out.println(yaml.dump(yaml.load(new FileInputStream(new File(
                fileName)))));

        Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml
                .load(new FileInputStream(new File(fileName)));

        for (String key : values.keySet()) {
            Map<String, String> subValues = values.get(key);
            System.out.println(key);

            for (String subValueKey : subValues.keySet()) {
                //System.out.println(String.format("\t%s === %s", subValueKey, subValues.get(subValueKey)));
                switch (subValueKey) {
                    case "title":
                        setupQT = subValues.get(subValueKey);
                    case "question":
                        setupQuestion = subValues.get(subValueKey);
                    case "answer1":
                        setupQuestionAnswer1 = subValues.get(subValueKey);
                    case "answer2":
                        setupQuestionAnswer2 = subValues.get(subValueKey);
                    case "answer3":
                        setupQuestionAnswer3 = subValues.get(subValueKey);
                    case "answer4":
                        setupQuestionAnswer4 = subValues.get(subValueKey);
                    default:
                        System.out.println(subValues.get(subValueKey));
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

        System.out.println(yaml.dump(yaml.load(new FileInputStream(new File(
                fileName)))));

        Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml
                .load(new FileInputStream(new File(fileName)));

        for (String key : values.keySet()) {
            Map<String, String> subValues = values.get(key);
            System.out.println(key);

            for (String subValueKey : subValues.keySet()) {
                //System.out.println(String.format("\t%s === %s", subValueKey, subValues.get(subValueKey)));
                switch (subValueKey) {
                    case "username":
                        setupUser = subValues.get(subValueKey);
                    case "password":
                        setupPass = subValues.get(subValueKey);
                    default:
                        System.out.println(subValues.get(subValueKey));
                }
            }
            database.newUser(setupUser, setupPass);
        }

    }

}
