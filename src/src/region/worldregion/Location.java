package region.worldregion;

import java.awt.*;

/**
 *
 * Location object. Main attributes are {@link Point} and a population density value.
 * */
public class   Location {

    private final Point point;
    private final int density;

    /**
     * Constructor.
     * @param point
     * @param densitiy
     */
    public Location(Point point, int densitiy) {
        this.point = point;
        this.density = densitiy;
    }

    /**
     *
     * @return Point.
     */
    public Point getPoint() {
        return point;
    }

    /**
     *
     * @return Density.
     */
    public int getDensity() {
        return density;
    }

    /**
     * Overriding of metod equals(). Locations are the same if they share the same point on a board.
     * @param o
     * @return
     */
    public boolean equals(Object o) {

        Point p = ((Location) o).getPoint();
        return this.getPoint().getX() == p.getX()
                && this.getPoint().getY() == p.getY();
    }

    /**
     *
     * @return Column of {@link Point}.
     */
    public double x() {
        return getPoint().getX();
    }

    /**
     *
     * @return Line of {@link Point}
     */
    public double y() {
        return getPoint().getY();
    }

    /**
     *
     * @param location
     * @return If this location is a border of another location.
     */
    public boolean isBorder(Location location) {

        if(this.equals(location)) return false;

        double verticalDiff = Math.abs(y() - location.y());
        double horizontalDiff = Math.abs(x() - location.x());

        if(horizontalDiff <= 1 && verticalDiff <= 1) {
            return true;
        }

        return false;
    }
}
