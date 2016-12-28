package region;

/**
 * Created by espinha on 12/5/16.
 */
public class CountryLocation {

    private double latitude;
    private double longitude;

    private double range;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public CountryLocation(String location) {

        String[] data = location.split(";");

        latitude = Double.parseDouble(data[0]);
        longitude = Double.parseDouble(data[1]);

        range = Double.parseDouble(data[2]);
    }
}
