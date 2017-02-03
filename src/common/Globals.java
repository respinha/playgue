package common;

import pt.ua.concurrent.Metronome;

import java.awt.*;
import java.util.Random;

/**
 * Created by rui on 1/24/17.
 *
 * Class with useful methods.
 */
public class Globals {

    private final static String[] symptomsPool = {"headache", "vomits", "nosebleed","heart failure",
            "headache2", "vomits2", "nosebleed2","heart failure2"};

    public synchronized static Metronome metronome() {

        // metronome implemented by mos@ua.pt
        // src: http://sweet.ua.pt/mos/pt.ua.concurrent/index.xhtml
        if(metronome == null)
            metronome = new Metronome(1500);

        return metronome;
    }
    
    public static String randomSymptom() {
        
        return symptomsPool[new Random().nextInt(symptomsPool.length)];
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

    public static Color chooseColor(int number) {

        assert number >= 0;

        switch (number){

            case 0: return Color.BLUE;
            case 1: return Color.orange;
                
            case 2: return Color.orange.darker();
                
            case 3:
            default: return Color.orange.darker().darker();
        }
    }


    public static boolean evenRandom() {

        return new Random().nextInt(2) == 0;
    }
}
 