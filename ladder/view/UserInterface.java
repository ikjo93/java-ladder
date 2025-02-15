package ladder.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInterface {

    private static final String SEPARATOR = ",";
    private final Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    public List<String> inputPlayerList() {
        String userInput = "";
        boolean inputFlag = true;
        while (inputFlag) {
            GameDisplay.guidePlayerNameInput();
            userInput = sc.nextLine();
            inputFlag = checkInput(userInput);
        }

        return getElementList(userInput);
    }

    public List<String> inputExecutionResult() {
        String userInput = "";
        boolean inputFlag = true;
        while (inputFlag) {
            GameDisplay.guideExecutionResultInput();
            userInput = sc.nextLine();
            inputFlag = checkInput(userInput);
        }

        return getElementList(userInput);
    }

    public int inputLadderHeight() {
        GameDisplay.guideLadderHeightInput();

        return Integer.parseInt(sc.nextLine());
    }

    public String inputPlayerNameForResult() {
        GameDisplay.guideResultOfPlayer();

        return sc.nextLine();
    }

    private boolean checkInput(String userInput) {
        String inputPattern = "^([^,\s]{1,5})+(,([^,\s]{1,5}))*$";
        if (!Pattern.matches(inputPattern, userInput)) {
            GameDisplay.showError();
            return true;
        }

        return false;
    }

    private List<String> getElementList(String input) {
        List<String> arrayList = new ArrayList<>();
        String[] players = input.split(SEPARATOR);
        Collections.addAll(arrayList, players);

        return arrayList;
    }
}
