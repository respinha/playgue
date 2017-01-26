package common;

import entities.Bacteria;
import java.util.*;

/**
 * Created by espinha on 1/24/17.
 */
public class Report {

    private List<Infection> infections;
    public Report (Set<Infection> infections) {

        this.infections = new ArrayList<>();
        this.infections.addAll(infections);
    }

    public List<Infection> reportedInfections() {
        return infections;
    }
}
