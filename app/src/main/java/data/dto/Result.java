package data.dto;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Result {

    private List<Answer> answerList;
    private long testStartTime;
    private int testId;


    public Result() {
        this.answerList = new ArrayList();
    }

    public Result(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addStimulusResult(Answer answer){
        this.answerList.add(answer);
    }

    public void removeStimulusResult(Answer answer){
        this.answerList.remove(answer);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public long getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
