package model;

import java.util.Comparator;

public class CityDestinationComparator implements Comparator<Flight> {


    @Override
    public int compare(Flight one, Flight two) {
        int comparation;

        String destinationOne = one.getDestinationCity();
        String destinationTwo = two.getDestinationCity();

        if (destinationOne.compareToIgnoreCase(destinationTwo) > 0){
            comparation = 1;
        }else if (destinationOne.compareToIgnoreCase(destinationTwo) < 0){
            comparation = -1;
        }else {
            comparation = 0;
        }
        return comparation;
    }
}
