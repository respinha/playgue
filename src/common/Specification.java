package common;

/**
 * Created by espinha on 1/23/17.
 */
public class Specification {

    protected final int medicalRating;
    protected final double temperature;
    protected final int welfareRating;

    public Specification(int welfareRating, int medicalRating, double temperature) {
        this.temperature = temperature;

        this.welfareRating = welfareRating;
        this.medicalRating = medicalRating;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWelfareRating() {
        return welfareRating;
    }

    public int getMedicalRating() {
        return medicalRating;
    }
}
