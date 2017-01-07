package data.models;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class Slide
{
    private long id;
    private String slideName;
    private int delay;
    private byte[] primingImage;
    private byte[] testImage;
    private long testId;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }
}
