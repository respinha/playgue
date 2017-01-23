package region;

import common.Specification;

/**
 * Created by espinha on 1/15/17.
 */
public class RegionSpecification extends Specification {

    private final String name;

    public String getName() {
        return name;
    }

    public RegionSpecification(String name, int welfareRating, int medicalRating, double temperature) {

        super(welfareRating, medicalRating, temperature);
        this.name = name;

    }
}
