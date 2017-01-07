package data.models;

import java.util.Date;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Test {

    private long id;
    private String testName;
    private int numberOfSlides;
    private String creationDate;

    // constructors
    public Test() {
    }

    public Test(String note) {
        this.testName = testName;
        this.creationDate = (new Date()).toString();
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    public void setNumberOfSlides(int numberOfSlides) {
        this.numberOfSlides = numberOfSlides;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
