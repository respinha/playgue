package Tests;

import common.Globals;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rui on 1/25/17.
 */
public class TestTimerTask {

    public static void main(String[] args) throws InterruptedException {
        GlobalTimer gTimer = new GlobalTimer();
        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(gTimer, 0, 3*1000 );

        while(true);
    }
}

class GlobalTimer extends TimerTask {



    @Override
    public void run() {
        System.out.println("Start time:" + new Date());
        completeTask();
        System.out.println("End time:" + new Date());

    }

    private void completeTask() {
        //assuming it takes 20 secs to complete the task
        System.out.println("tick");
    }


}
