package model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class Airport {

    private static final int AIRLINE_DATA_SIZE = 10;
    private static final int CITY_DATA_SIZE = 30;

    private static final String AIRPORT_AIRLINE_DATA = "data/AirLine.txt";
    private static final String AIRPORT_CITY_DATA = "data/City.txt";

    private List<Flight> flightList;

    public Airport(){
        flightList = new ArrayList<Flight>();
    }

    public List<Flight> getFlightList(){
        return flightList;
    }

    public void createData(int i) throws IOException, NegativeArraySizeException {
        if (i <= 0){
            throw new NegativeArraySizeException();
        }else {
            if (!flightList.isEmpty()){
                flightList.clear();
            }
            int[] flightNumbers = createRandomNumbers(i);
            ArrayList<String> airline = readData(AIRPORT_AIRLINE_DATA);
            ArrayList<String> city = readData(AIRPORT_CITY_DATA);

            for (int j = 0; j < i; j++) {
                int airlineN = (int) Math.floor(Math.random()*AIRLINE_DATA_SIZE);
                int cityN = (int) Math.floor(Math.random()*CITY_DATA_SIZE);
                String day = "" +  (int) Math.floor(Math.random()*(2-(30+1))+(30));
                String month = "" + (int) Math.floor(Math.random()*(5-(12+1))+(12));
                int hour = (int) Math.floor(Math.random()*(2-(12+1))+(12));
                int minute =(int) Math.floor(Math.random()*(1-(59+1))+(59));
                int dayMoment = (int) Math.floor(Math.random()*(2-(3+1))+(3));
                int gate = (int) Math.floor(Math.random()*(2-(15+1))+(15));
                int flightN = flightNumbers[j];

                addFlight(airline.get(airlineN), city.get(cityN), day, month, hour, minute, dayMoment, gate, flightN);
            }
            sortByDate();
        }
    }

    private void addFlight(String airlineN, String cityN, String day, String month, int hour, int minute, int dayMoment, int gate, int flightN){
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
        flightList.add(flight);
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

    private int[] createRandomNumbers(int i){
        int min = 1000;
        int max = 9999;

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


    public void sortByDate(){
        for(int i =0; i< flightList.size(); i++) {
            for (int j = 0; j < flightList.size() - i - 1; j++) {
                if (flightList.get(j).getDate().compareTo(flightList.get(j + 1).getDate()) > 0) {
                    Flight temp = flightList.get(j);
                    flightList.set(j, flightList.get(j + 1));
                    flightList.set(j + 1, temp);
                }
            }
        }
    }


    public void sortByTime(){
        for (int i = 0; i < flightList.size(); i++) {
            Flight current = flightList.get(i);
            int j = i;
            while(j > 0 && flightList.get(j-1).getTime().compareTo(current.getTime()) > 0){
                flightList.set(j, flightList.get(j-1));
                j--;
            }
            flightList.set(j, current);
        }
    }


    public void sortByAirline(){
        for (int i = 0; i < flightList.size() - 1; i++) {
            Flight min = flightList.get(i);
            int c = i;

            for (int j = i + 1; j < flightList.size(); j++) {
                if (flightList.get(j).getAirline().compareToIgnoreCase(min.getAirline()) < 0){
                    min = flightList.get(j);
                    c = j;
                }
            }
            Flight aux = flightList.get(i);
            flightList.set(i, min);
            flightList.set(c, aux);
        }
    }


    public void sortByFlightNumber(){
        Collections.sort(flightList);
    }


    public void sortByDestination(){
        Collections.sort(flightList, new CityDestinationComparator());
    }


    public void sortByGate(){
        for (int i = 0; i < flightList.size() - 1; i++) {
            Flight min = flightList.get(i);
            int c = i;

            for (int j = i + 1; j < flightList.size(); j++) {
                if (flightList.get(j).getGate() < min.getGate()){
                    min = flightList.get(j);
                    c = j;
                }
            }
            Flight aux = flightList.get(i);
            flightList.set(i, min);
            flightList.set(c, aux);
        }
    }
}
