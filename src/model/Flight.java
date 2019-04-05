package model;

public class Flight {

    private String date;
    private Time time;
    private String flightNumber;
    private String destinationCity;
    private int gate;

    public Flight(String date, String flightNumber, String destinationCity, int gate) {
        this.date = date;
        this.flightNumber = flightNumber;
        this.destinationCity = destinationCity;
        this.gate = gate;
    }

    public void addTime(int hour, int minute, String format){
        Time t = new Time(hour, minute, format);
        this.time = t;
    }
}
