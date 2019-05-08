package model;

import java.util.Comparator;

public class FlightNumberComparator implements Comparator<Flight> {


    @Override
    public int compare(Flight one, Flight two) {
        int comparation;

        String oneFlightNumber = one.getFlightNumber();
        String twoFlightNumber = two.getFlightNumber();

        if (oneFlightNumber.compareToIgnoreCase(twoFlightNumber) > 0){
            comparation = 1;
        }else if (oneFlightNumber.compareToIgnoreCase(twoFlightNumber) < 0){
            comparation = -1;
        }else {
            comparation = 0;
        }
        return comparation;
    }
}
