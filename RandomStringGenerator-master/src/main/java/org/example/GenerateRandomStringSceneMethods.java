package org.example;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomStringSceneMethods {

    private String alphabeticalCharacters = "abcdefghijklmnopqrstuvwxyz";
    private String numbericalCharacters = "1234567890";
    private String specialCharacters = "!@#$%^&*()[]{}:;,<>.?/|\\~`";
    private List<String> generatorRules;
    public GenerateRandomStringSceneMethods(){
        generatorRules = new ArrayList<String>();
    }

    public String GenerateRandomString(String stringLength, boolean includesAlpaChars, boolean includesNumChars, boolean includesSpecialChars){
        int stringLengthInt;
        if (stringLength.isBlank() || stringLength.equals("0")) throw new RuntimeException("Invalid input. Input should not be empty and should be greater than 0.");
        else if (!includesAlpaChars && !includesNumChars && !includesSpecialChars) throw new RuntimeException("No options were selected. A minimum of one option should be selected in order to generate a random string.");
        else {
            stringLengthInt = Integer.parseInt(stringLength);
            if (includesAlpaChars) generatorRules.add(alphabeticalCharacters);
            if (includesNumChars) generatorRules.add(numbericalCharacters);
            if (includesSpecialChars) generatorRules.add(specialCharacters);
        }

            StringBuilder randomString = new StringBuilder();
            int alphabetsLength = alphabeticalCharacters.length();
            Random randomNumber = new Random();

        System.out.println(stringLength);
            for (int i = 0; i < stringLengthInt; i++){
                int number = randomNumber.nextInt(alphabetsLength);
                randomString.append(alphabeticalCharacters.charAt(number));
            }

            return randomString.toString();
    }

}
