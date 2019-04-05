package model;

import java.util.ArrayList;
import java.util.List;

public class AirLine {

    private List<Flight> flightList;

    private String name;

    public AirLine(String name){
        this.name = name;
        flightList = new ArrayList<Flight>();
    }

    public String getName() {
        return name;
    }

    public void addFlight(String date, String time, String flightNumber, String destination, int gate){
        String[] words = time.split(";");

        int hour = Integer.parseInt(words[0]);
        int minute = Integer.parseInt(words[1]);
        String format = words[2];

        Flight flight = new Flight(date,flightNumber,destination,gate);
        flight.addTime(hour, minute, format);

        flightList.add(flight);
    }
}
