package model;

import customExceptions.NegativeNumberException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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


    public void createData(int i) throws IOException, NegativeNumberException {
        if (i <= 0){
            throw new NegativeNumberException();
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
                String day = "" +  (int) Math.floor(Math.random()*(1-(30+1))+(30));
                String month = "" + (int) Math.floor(Math.random()*(5-(12+1))+(12));
                String hour = "" + (int) Math.floor(Math.random()*(2-(12+1))+(12));
                String minute = "" + (int) Math.floor(Math.random()*(0-(59+1))+(59));
                int gate = (int) Math.floor(Math.random()*(2-(15+1))+(15));
                int flightN = flightNumbers[j];

                addFlight(airline.get(airlineN), city.get(cityN), day, month, hour, minute, gate, flightN);
            }

            for (int j = 0; j < flightList.size(); j++) {
                System.out.println("Airline: " + flightList.get(j).getAirline() + "\n" + "FlightNumber: " + flightList.get(j).getFlightNumber() + "\n" + "Date: " + flightList.get(j).getDate() + "\n" + "Time: " + flightList.get(j).getTime() + "\n" + "TO: " + flightList.get(j).getDestinationCity() + "\n" + "Gate: " + flightList.get(j).getGate() + "\n" + "\n");
            }
        }
    }

    private void addFlight(String airlineN, String cityN, String day, String month, String hour, String minute, int gate, int flightN){
        String flightNumber = "";
        String date = "";
        String time = "";

        if (Integer.parseInt(day) < 10){
            day = "0" + day;
        }
        if (Integer.parseInt(month) < 10){
            month = "0" + month;
        }
        if (Integer.parseInt(hour) < 10){
            hour = "0" + hour;
        }
        if (Integer.parseInt(minute) < 10){
            minute = "0" + minute;
        }
        time = hour + ":" + minute;
        date = "2019/" + month + "/" + day;

        switch (airlineN){
            case "Avianca":
                flightNumber = "AVA " + flightN;
                break;
            case "Satena":
                flightNumber = "SA " + flightN;
                break;
            case "Latam":
                flightNumber = "LTM " + flightN;
                break;
            case "Copa Airlines":
                flightNumber = "CAL " + flightN;
                break;
            case "Wingo":
                flightNumber = "WNO " + flightN;
                break;
            case "EasyFly":
                flightNumber = "ESF " + flightN;
                break;
            case "Viva Colombia":
                flightNumber = "VVA " + flightN;
                break;
            case "Lanco":
                flightNumber = "LNO " + flightN;
                break;
            case "Tampa Cargo":
                flightNumber = "TMC " + flightN;
                break;
            case "Sadelca":
                flightNumber = "SEA " + flightN;
                break;
        }
        Flight flight = new Flight(date, time, airlineN, flightNumber, cityN, gate);
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

}
