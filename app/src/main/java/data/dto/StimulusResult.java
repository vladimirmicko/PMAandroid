package data.dto;

import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class StimulusResult {

    private int testId;
    private int stimulusNo;
    private int primeStimShowTime;
    private int testStimShowTime;
    private int answer;
    private int answerDuration;
    private int answerTime;


    public StimulusResult() {
    }


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getStimulusNo() {
        return stimulusNo;
    }

    public void setStimulusNo(int stimulusNo) {
        this.stimulusNo = stimulusNo;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }

    public int getPrimeStimShowTime() {
        return primeStimShowTime;
    }

    public void setPrimeStimShowTime(int primeStimShowTime) {
        this.primeStimShowTime = primeStimShowTime;
    }

    public int getTestStimShowTime() {
        return testStimShowTime;
    }

    public void setTestStimShowTime(int testStimShowTime) {
        this.testStimShowTime = testStimShowTime;
    }

    public int getAnswerDuration() {
        return answerDuration;
    }

    public void setAnswerDuration(int answerDuration) {
        this.answerDuration = answerDuration;
    }
}
