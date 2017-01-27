package region.worldregion;

import java.awt.*;

/**
 * Created by espinha on 1/26/17.
 */
public class Location {

    private final Point point;
    private final int density;

    public Location(Point point, int densitiy) {
        this.point = point;
        this.density = densitiy;
    }

    public Point getPoint() {
        return point;
    }

    public int getDensity() {
        return density;
    }

    public boolean equals(Object o) {

        Point p = ((Location) o).getPoint();
        return this.getPoint().getX() == p.getX()
                && this.getPoint().getY() == p.getY();
    }
}
