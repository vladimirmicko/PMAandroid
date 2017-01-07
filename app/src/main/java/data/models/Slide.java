package data.models;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Slide
{
    private Long id;
    private String slideName;
    private int delay;
    private byte[] primingImage;
    private byte[] testImage;
    private Long testId;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPrimingImage() {
        return primingImage;
    }

    public void setPrimingImage(byte[] primingImage) {
        this.primingImage = primingImage;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }

    public byte[] getTestImage() {
        return testImage;
    }

    public void setTestImage(byte[] testImage) {
        this.testImage = testImage;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
