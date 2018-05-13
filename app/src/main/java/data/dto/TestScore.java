package data.dto;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class TestScore {

    private List<StimulusResult> scoreList;
    private int testStartTime;


    public TestScore() {
        this.scoreList = new ArrayList();
    }

    public TestScore(List<StimulusResult> scoreList) {
        this.scoreList = scoreList;
    }

    public void addStimulusResult(StimulusResult stimulusResult){
        this.scoreList.add(stimulusResult);
    }

    public void removeStimulusResult(StimulusResult stimulusResult){
        this.scoreList.remove(stimulusResult);
    }

    public List<StimulusResult> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<StimulusResult> scoreList) {
        this.scoreList = scoreList;
    }

    public int getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(int testStartTime) {
        this.testStartTime = testStartTime;
    }


}
