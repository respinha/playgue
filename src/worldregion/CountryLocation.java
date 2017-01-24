package worldregion;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Created by espinha on 12/5/16.
 */
@Immutable
public class CountryLocation {

    private double latitude;
    private double longitude;

    private double range;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public CountryLocation(String location) {

        String[] data = location.split(",");

        latitude = Double.parseDouble(data[0]);
        longitude = Double.parseDouble(data[1]);

        range = Double.parseDouble(data[2]);
    }
}
