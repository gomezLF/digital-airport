package model;

public class Flight implements Comparable<Flight>{

    private String date;
    private Time time;
    private String airline;
    private String flightNumber;
    private String destinationCity;
    private int gate;

    public Flight(String date, Time time, String airline, String flightNumber, String destinationCity, int gate) {
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
        return time.toString();
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

    @Override
    public int compareTo(Flight o) {
        int comparation;

        if (flightNumber.compareTo(o.flightNumber) > 0){
            comparation = 1;
        }else if (flightNumber.compareTo(o.flightNumber) < 0){
            comparation = -1;
        }else{
            comparation = 0;
        }
        return comparation;
    }
}
