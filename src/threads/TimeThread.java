package threads;

import userInterface.AirportScreenController;

public class TimeThread extends Thread{

    private AirportScreenController asc;

    public TimeThread(AirportScreenController asc) {
        this.asc = asc;
    }

    @Override
    public void run(){
        asc.updateTime();
    }
}
