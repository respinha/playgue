package common;

import entities.Bacteria;
import entities.Epidemy;
import pt.ua.concurrent.Metronome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rui on 1/24/17.
 */
public class Globals {

    public synchronized static Metronome metronome() {

        // metronome implemented by mos@ua.pt
        // src: http://sweet.ua.pt/mos/pt.ua.concurrent/index.xhtml

        if(metronome == null)
            return new Metronome(1500);
        return metronome;
    }

    private static volatile Metronome metronome;
    public static void randomPause(int min, int max) {

        int interval = new Random().nextInt(max - min) + min;
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
