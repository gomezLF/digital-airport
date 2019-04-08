package model;

public class Time {

    private int  hour;
    private int minute;
    private String dayMoment;

    public Time(int hour, int minute, String dayMoment) {
        this.hour = hour;
        this.minute = minute;
        this.dayMoment = dayMoment;
    }

    @Override
    public String toString() {
        String h;
        String m;

        if (hour < 10){
            h = "0" + hour;
        }else {
            h = "" +  hour;
        }
        if (minute < 10){
            m = "0" + minute;
        }else {
            m = "" + minute;
        }
        String message = h + ":" + m + " " + dayMoment;

        return message;
    }
}
