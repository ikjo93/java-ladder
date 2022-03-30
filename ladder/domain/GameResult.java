package ladder.domain;

import ladder.view.GameDisplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private static final String TOTAL_RESULT_WORD = "all";
    private static final String NOT_FOUND_WORD = "해당되는 사람이 없습니다.";

    private final Map<String, String> gameResult;
    private final List<String> players;
    private final List<Line> ladderInfo;
    private final List<String> results;

    private final int lengthX;
    private final int lengthY;
    private int posX; // posX : 사다리 가로축
    private int posY; // posY : 사다리 세로축

    GameResult(List<String> players, List<Line> ladderInfo, List<String> results) {
        gameResult = new HashMap<>();
        this.players = players;
        this.ladderInfo = ladderInfo;
        this.results = results;

        lengthX = ladderInfo.get(0).getLengthX();
        lengthY = ladderInfo.size();

        createResult();
    }

    private void createResult() {
        for (int i = 0; i < players.size(); i++) {
            processResult(i);
        }
    }

    private void processResult(int playerIndex) {
        posX = playerIndex;
        posY = 0;
        while (posY < lengthY) {
            movePoint();
        }
        gameResult.put(players.get(playerIndex), results.get(posX));
    }

    private void movePoint() {
        if (posX != 0 && ladderInfo.get(posY).elementIsTrue(posX - 1)) posX -= 1;
        else if (posX != lengthX && ladderInfo.get(posY).elementIsTrue(posX)) posX += 1;
        posY++;
    }

    public void getResult(String playerName) {
        if (playerName.equals(TOTAL_RESULT_WORD)) {
            GameDisplay.showTotalResult(gameResult);
            return;
        }
        GameDisplay.showResult(gameResult.getOrDefault(playerName, NOT_FOUND_WORD));
    }
}
