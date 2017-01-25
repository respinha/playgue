package region;

import common.Report;
import entities.Reporter;
import pt.ua.gboard.GBoard;
import region.worldregion.MutableCountry;

import java.util.Map;

/**
 * Created by espinha on 11/21/16.
 */

// Shared
public class MedicalInformationCenter {

    // organization

    private static GBoard board;
    private int time;

    public MedicalInformationCenter(GBoard board) {
        this.board = board;
        time++;
    }

    public synchronized void update(Reporter reporter, Report report) {

        assert report != null && reporter != null;

        while(time % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Map<MutableCountry, Double> infectedCountries = report.getData();

        System.out.println("Reporter: " + reporter.getName());
        for (Map.Entry<MutableCountry, Double> entry : infectedCountries.entrySet())
        {
            System.out.print(entry.getKey() + " ; " + entry.getValue());
        }
        System.out.println("#############");

        // todo: connect to gboard
    }

    public synchronized void newDay() {
        time++;
        if(time % 2 == 0)
            notifyAll();
    }
}
