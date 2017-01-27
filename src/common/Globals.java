package common;

import pt.ua.concurrent.Metronome;

import java.util.Random;

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
