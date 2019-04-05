package model;

public class Time {

    private int hour;
    private int minute;
    private String format;

    public Time(int hour, int minute, String format) {
        this.hour = hour;
        this.minute = minute;
        this.format = format;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getFormat() {
        return format;
    }
}
