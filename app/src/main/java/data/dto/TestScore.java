package data.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class TestScore {

    List<String> scoreList;

    public TestScore() {
    }

    public TestScore(List<String> scoreList) {
        this.scoreList = scoreList;
    }

    public List<String> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<String> scoreList) {
        this.scoreList = scoreList;
    }
}
