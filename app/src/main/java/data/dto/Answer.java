package data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Answer {

    private int answerNumber;
    private long primeStimShowTime;
    private long testStimShowTime;
    private long answerTime;
    private int answerValue;


    public Answer() {
    }


    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

    public long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(long answerTime) {
        this.answerTime = answerTime;
    }

    public long getPrimeStimShowTime() {
        return primeStimShowTime;
    }

    public void setPrimeStimShowTime(long primeStimShowTime) {
        this.primeStimShowTime = primeStimShowTime;
    }

    public long getTestStimShowTime() {
        return testStimShowTime;
    }

    public void setTestStimShowTime(long testStimShowTime) {
        this.testStimShowTime = testStimShowTime;
    }

    @JsonIgnore
    public long getAnswerDuration() {
        return answerTime-testStimShowTime;
    }

}
