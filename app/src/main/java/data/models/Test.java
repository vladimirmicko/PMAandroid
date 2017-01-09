package data.models;

import java.util.Date;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Test {

    private Long id;
    private String testName;
    private String description;
    private String creationDate;
    private byte[] testPromoImage;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public byte[] getTestPromoImage() {
        return testPromoImage;
    }

    public void setTestPromoImage(byte[] testPromoImage) {
        this.testPromoImage = testPromoImage;
    }
}
