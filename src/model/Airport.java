package model;


import customExceptions.EmptyDataException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Airport {

    private static final int ROWS_PER_PAGE = 20;

    public static final String AIRPORT_AIRLINE_DATA = "data/AirLine.txt";
    public static final String AIRPORT_CITY_DATA = "data/City.txt";

    private Flight first;

    public Airport(){

    }

    public Flight getFlightList(){
        return first;
    }

    public void createData(int i, String airlineData, String cityData) throws IOException, NegativeArraySizeException {
        if (i <= 0){
            throw new NegativeArraySizeException();
        }else {
            if (first != null){
                first.setNext(null);
                first = null;
            }
            int[] flightNumbers = createFlightNumbers(i);
            ArrayList<String> airline = readData(airlineData);
            ArrayList<String> city = readData(cityData);

            for (int j = 0; j < i; j++) {
                int airlineN = (int) Math.floor(Math.random()*(airline.size() - 1));
                int cityN = (int) Math.floor(Math.random()*(city.size() - 1));
                String day = "" +  (int) Math.floor(Math.random()*(2-(30+1))+(30));
                String month = "" + (int) Math.floor(Math.random()*(5-(12+1))+(12));
                int hour = (int) Math.floor(Math.random()*(2-(12+1))+(12));
                int minute =(int) Math.floor(Math.random()*(1-(59+1))+(59));
                int dayMoment = (int) Math.floor(Math.random()*(2-(3+1))+(3));
                int gate = (int) Math.floor(Math.random()*(2-(15+1))+(15));
                int flightN = flightNumbers[j];

                createFlight(airline.get(airlineN), city.get(cityN), day, month, hour, minute, dayMoment, gate, flightN);
            }
            sortByDate();
        }
    }

    private void createFlight(String airlineN, String cityN, String day, String month, int hour, int minute, int dayMoment, int gate, int flightN){
        String flightNumber = "";
        String date = "";
        String dayM = "";

        if (Integer.parseInt(day) < 10){
            day = "0" + day;
        }
        if (Integer.parseInt(month) < 10){
            month = "0" + month;
        }

        switch (dayMoment){
            case 1:
                dayM = "AM";
                break;
            case 2:
                dayM = "PM";
                break;
        }
        Time t = new Time(hour, minute, dayM);
        date = "2019-" + month + "-" + day;
        flightNumber = "" + airlineN.charAt(0) + airlineN.charAt(2) + airlineN.charAt(airlineN.length() - 1) + " " + flightN;

        Flight flight = new Flight(date, t, airlineN, flightNumber, cityN,gate);
        addFlight(flight);
    }

    private void addFlight(Flight flight){
        if (first == null){
            first = flight;

        }else {
            Flight aux = first;

            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(flight);

        }
    }

    private ArrayList<String> readData(String path)throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> words = new ArrayList<String>();

        String line = br.readLine();
        while(line != null){
            words.add(line);
            line = br.readLine();
        }
        br.close();
        fr.close();

        return words;
    }

    private int[] createFlightNumbers(int i){
        int min = 10000;
        int max = 99999;

        int[] randomNumbers = new int[i];

        for (int j = 0; j < randomNumbers.length; j++) {
            int rd = (int)Math.floor(Math.random()*(min-(max+1))+(max));
            randomNumbers[j] = rd;
        }

        for (int j = 0; j < randomNumbers.length; j++) {
            for (int k = 0; k < randomNumbers.length; k++) {
                if (randomNumbers[j] == randomNumbers[k] && j != k){
                    int rd = (int)Math.floor(Math.random()*(min-(max+1))+(max));
                    randomNumbers[j] = rd;
                    j = 0;
                }
            }
        }
        return randomNumbers;
    }


    public void sortByDate() {
        if (first.getNext() != null){

            for (int i = 0; i < listSize(); i++){
                Flight current = first;
                Flight previous = null;
                Flight next = first.getNext();

                while(next != null){
                    if (current.compareTo(next) == 1){
                        if (previous != null){
                            Flight sig = next.getNext();

                            previous.setNext(next);
                            next.setNext(current);
                            current.setNext(sig);
                        }else {
                            Flight sig = next.getNext();

                            first = next;
                            next.setNext(current);
                            current.setNext(sig);
                        }

                        previous = next;
                        next = current.getNext();

                    }else {
                        previous = current;
                        current = next;
                        next = next.getNext();
                    }
                }
            }
        }
    }

    public void sortByTime(){
        if (first.getNext() != null){

            for (int i = 0; i < listSize(); i++){
                Flight current = first;
                Flight previous = null;
                Flight next = first.getNext();

                while(next != null){
                    if (current.getTime().compareTo(next.getTime()) > 0){

                        if (previous != null){
                            Flight sig = next.getNext();

                            previous.setNext(next);
                            next.setNext(current);
                            current.setNext(sig);
                        }else {
                            Flight sig = next.getNext();

                            first = next;
                            next.setNext(current);
                            current.setNext(sig);
                        }

                        previous = next;
                        next = current.getNext();

                    }else {
                        previous = current;
                        current = next;
                        next = next.getNext();
                    }
                }
            }
        }
    }


    public void sortByAirline(){
        if (first.getNext() != null){

            Flight p = first;
            Flight current = first.getNext();
            Flight previous = first;

            while(current != null){
                if (previous.getAirline().compareTo(current.getAirline()) <= 0){
                    current = current.getNext();
                    previous = previous.getNext();
                }else {

                    if (first.getAirline().compareTo(current.getAirline()) > 0){
                        previous.setNext(current.getNext());
                        current.setNext(first);
                        first = current;
                    }else {
                        p = first;
                        while(p.getNext() != null && p.getNext().getAirline().compareTo(current.getAirline()) < 0){
                            p = p.getNext();
                        }
                        previous.setNext(current.getNext());
                        current.setNext(p.getNext());
                        p.setNext(current);
                    }
                }
                current = previous.getNext();
            }
        }
    }


    public void sortByFlightNumber(){
        if (first.getNext() != null){

            Flight p = first;
            Flight current = first.getNext();
            Flight previous = first;

            while(current != null){
                if (previous.getFlightNumber().compareTo(current.getFlightNumber()) <= 0){
                    current = current.getNext();
                    previous = previous.getNext();
                }else {

                    if (first.getFlightNumber().compareTo(current.getFlightNumber()) > 0){
                        previous.setNext(current.getNext());
                        current.setNext(first);
                        first = current;
                    }else {
                        p = first;

                        FlightNumberComparator fnc = new FlightNumberComparator();
                        while(p.getNext() != null && fnc.compare(p.getNext(), current) == -1){
                            p = p.getNext();
                        }
                        previous.setNext(current.getNext());
                        current.setNext(p.getNext());
                        p.setNext(current);
                    }
                }
                current = previous.getNext();
            }
        }
    }


    public void sortByDestination(){
        if (first.getNext() != null){
            
        }
    }


    public void sortByGate(){
        if (first.getNext() != null){

        }
    }
    
    public Flight searchByDate(String date) throws EmptyDataException {
        Flight searched = null;

        if (date.equals("")){
            throw new EmptyDataException();
        }else {
            sortByDate();

            Flight current = first;
            boolean found = false;

            while(current != null && !found){

                if (current.getDate().compareTo(date) == 0){
                    found = true;
                    searched = current;
                }
                current = current.getNext();
            }
        }
        return searched;
    }

    public Flight searchByTime(String time) throws EmptyDataException {
        Flight searched = null;

        if (time.equals("")){
            throw new EmptyDataException();
        }else {
            sortByTime();

            Flight current = first;
            boolean found = false;

            while(current != null && !found){
                if (current.getTime().compareTo(time) == 0){
                    found = true;
                    searched = current;
                }

                current = current.getNext();
            }
        }
        return searched;
    }

    public Flight searchByAirline(String airline) throws EmptyDataException {
        Flight searched = null;

        if (airline.equals("")){
            throw new EmptyDataException();
        }else {
            sortByAirline();

            Flight current = first;
            boolean found = false;

            while(current != null && !found){
                if (current.getAirline().compareTo(airline) == 0){
                    found = true;
                    searched = current;
                }

                current = current.getNext();
            }
        }
        return searched;
    }

    public Flight searchByFlightNumber(String flightNumber) throws EmptyDataException {
        Flight searched = null;

        if (flightNumber.equals("")){
            throw new EmptyDataException();
        }else {
            sortByFlightNumber();
            boolean found = false;

            for (Flight i = first; i != null && !found; i = i.getNext()) {
                if (i.getFlightNumber().compareTo(flightNumber) == 0){
                    found = true;
                    searched = i;
                }
            }
        }
        return searched;
    }

    public Flight searchByCity(String city) throws EmptyDataException {
        Flight searched = null;

        if (city.equals("")){
            throw new EmptyDataException();
        }else {
            sortByDestination();
            boolean found = false;

            for (Flight i = first; i != null && !found; i = i.getNext()) {
                if (i.getDestinationCity().compareTo(city) == 0){
                    found = true;
                    searched = i;
                }
            }
        }
        return searched;
    }

    public Flight searchByGate(int gate){
        sortByGate();

        Flight searched = null;
        boolean found = false;

        for (Flight i = first; i != null && !found; i = i.getNext()){
            if (i.getGate() == gate){
                found = true;
                searched = i;
            }
        }
        return searched;
    }

    public int listSize(){
        int size = 0;
        Flight aux = first;

        while(aux.getNext() != null){
            size ++;
            aux = aux.getNext();
        }
        return size;
    }

    public int[] createPage(int pageIndex){
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, listSize());

        int[] page = new int[] {fromIndex, toIndex};

        return page;
    }

    public ObservableList<Flight> dataToScreen(int pageIndex){
        int[] array = createPage(pageIndex);

        ObservableList<Flight> data = FXCollections.observableArrayList();

        Flight aux = first;
        int counter = 0;
        while(counter <= array[1]){
            if (counter >= array[0] && counter <= array[1]){
                data.add(aux);
            }
            counter ++;
            aux = aux.getNext();
        }
        return data;
    }
}
