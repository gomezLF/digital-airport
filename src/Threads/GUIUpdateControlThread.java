package Threads;

import javafx.application.Platform;
import userInterface.AirportScreenController;

public class GUIUpdateControlThread extends Thread {

    private final static long UPDATE_SLEEP_TIME = 1000;
    private AirportScreenController asc;

    public GUIUpdateControlThread(AirportScreenController asc) {
        this.asc = asc;
    }

    @Override
    public void run(){
        while (true){
            TimeThread tt = new TimeThread(asc);
            Platform.runLater(tt);

            try {
                sleep(UPDATE_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
