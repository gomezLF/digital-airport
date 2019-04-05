package model;

public class Flight {

    private String date;
    private String time;
    private String airline;
    private String flightNumber;
    private String destinationCity;
    private int gate;

    public Flight(String date, String time, String airline, String flightNumber, String destinationCity, int gate) {
        this.date = date;
        this.time = time;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.destinationCity = destinationCity;
        this.gate = gate;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public int getGate() {
        return gate;
    }
}
