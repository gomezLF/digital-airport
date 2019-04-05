package model;

import customExceptions.NegativeNumberException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Airport {

    public enum CREATOR {NEW_LIST, RANDOM_NUMBERS}

    private static final String AIRPORT_AIRLINE_DATA = "data/AirLine.txt";
    private static final String AIRPORT_CITY_DATA = "data/City.txt";
    private static final String AIRPORT_DATE_DATA = "data/Date.txt";
    private static final String AIRPORT_GATE_DATA = "data/Gate.txt";
    private static final String AIRPORT_TIME_DATA = "data/Time.txt";

    private List<AirLine> airLineList;

    public Airport(){
        airLineList = new ArrayList<>();
    }


    public void createData(int i) throws IOException, NegativeNumberException {
        if (i <= 0){
            throw new NegativeNumberException();
        }else {
            if (!airLineList.isEmpty()){
                airLineList.clear();
            }
            int[] flightNumbers = createRandomNumbers(i, CREATOR.NEW_LIST);
            int[] airlineNumbers = createRandomNumbers(i, CREATOR.RANDOM_NUMBERS);
            int[] cityNumbers = createRandomNumbers(i,CREATOR.RANDOM_NUMBERS);
            int[] dateNumbers = createRandomNumbers(i,CREATOR.RANDOM_NUMBERS);
            int[] gateNumbers = createRandomNumbers(i,CREATOR.RANDOM_NUMBERS);
            int[] timeNumbers = createRandomNumbers(i,CREATOR.RANDOM_NUMBERS);

            for (int j = 0; j < flightNumbers.length; j++) {
                String airline = readData(airlineNumbers[j], AIRPORT_AIRLINE_DATA);
                String city = readData(cityNumbers[j], AIRPORT_CITY_DATA);
                String date = readData(dateNumbers[j], AIRPORT_DATE_DATA);
                int gate = Integer.parseInt(readData(gateNumbers[j], AIRPORT_GATE_DATA));
                String time = readData(timeNumbers[j], AIRPORT_TIME_DATA);
                String flightNumber = "";

                switch (airline){
                    case "Avianca":
                        flightNumber = "AVA " + flightNumbers[j];
                        break;
                    case "Satena":
                        flightNumber = "NSE " + flightNumbers[j];
                        break;
                    case "Latam":
                        flightNumber = "ARE " + flightNumbers[j];
                        break;
                    case "Copa Airlines":
                        flightNumber = "CMP " + flightNumbers[j];
                        break;
                    case "Wingo":
                        flightNumber = "RPB " + flightNumbers[j];
                        break;
                    case "EasyFly":
                        flightNumber = "EFY " + flightNumbers[j];
                        break;
                    case "Viva Colombia":
                        flightNumber = "VVC " + flightNumbers[j];
                        break;
                    case "Lanco":
                        flightNumber = "LAE " + flightNumbers[j];
                        break;
                    case "Tampa Cargo":
                        flightNumber = "TPA " + flightNumbers[j];
                        break;
                    case "Sadelca":
                        flightNumber = "SDK " + flightNumbers[j];
                        break;
                }
                AirLine a = new AirLine(airline);
                a.addFlight(date, time, flightNumber, city, gate);
                airLineList.add(a);
            }
        }
    }

    private String readData(int i, String path)throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int counter = 0;
        boolean stoped = false;
        String line = br.readLine();
        while(line != null && !stoped){
            if (line.charAt(0) != '#'){
                if (counter == i){
                    stoped = true;
                }
            }
            counter ++;
            line = br.readLine();
        }
        return line;
    }

    private int[] createRandomNumbers(int i, CREATOR creator ){
        int min = 0;
        int max = 0;
        int[] randomNumbers = new int[i];

        switch (creator){
            case NEW_LIST:
                min = 1000;
                max = 9999;

                break;
            case RANDOM_NUMBERS:
                min = 1;
                max = 1000;
                break;
        }

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
