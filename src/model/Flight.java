package model;

public class Flight {

    private String date;
    private String time;
    private String flightNumber;
    private String destinationCity;
    private int gate;

    public Flight(String date, String time, String flightNumber, String destinationCity, int gate) {
        this.date = date;
        this.flightNumber = flightNumber;
        this.destinationCity = destinationCity;
        this.gate = gate;
    }

}
