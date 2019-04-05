package model;

import customExceptions.NegativeNumberException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Airport {

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
            ArrayList<String> airline = readData(AIRPORT_AIRLINE_DATA);
            ArrayList<String> city = readData(AIRPORT_CITY_DATA);

            for (int j = 0; j < i; j++) {
                int airlineN = 0;
                airlineN = (int) Math.random()*30;
                System.out.println(j + ": " + airlineN);
            }

        }
    }

    private ArrayList<String> readData(String path)throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> words = new ArrayList<String>();

        String line = br.readLine();
        while(line != null){
            if (line.charAt(0) != '#'){
                words.add(line);
            }
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
