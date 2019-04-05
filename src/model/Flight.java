package model;

public class Flight {

    private String date;
    private String time;
    private String airline;
    private String flightNumber;
    private String destinationCity;
    private String gate;

    public Flight(String date, String time, String airline, String flightNumber, String destinationCity, String gate) {
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAirline() {
        return airline;
    };

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
}
