package modelTest;

import model.Flight;
import model.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;
    private Time time;

    private void setupScenary1(){

    }

    private void setupScenary2(){
        String date = "2019-05-05";
        time = new Time(10,00, "AM");
        String airline = "Avianca";
        String flighNumber = "Esy 21500";
        String destination = "Medellin";
        int gate = 10;

        flight = new Flight(date, time, airline, flighNumber, destination, gate);
    }


    @Test
    void FlightTest(){
        setupScenary1();

        String date = "2019-04-05";
        time = new Time(05, 30, "PM");
        String airline = "EasyFly";
        String flighNumber = "Esy 2019";
        String destination = "Cali";
        int gate = 5;


        flight = new Flight(date, time, airline, flighNumber, destination, gate);

        //Se prueba que cada atributo haya quedado diferente de null
        assertNotNull(flight.getDate(), "The date on the flight is null");
        assertNotNull(flight.getTime(), "The time on the flight is null");
        assertNotNull(flight.getAirline(), "The airline on the flight is null");
        assertNotNull(flight.getFlightNumber(), "The flight number on the flight is null");
        assertNotNull(flight.getDestinationCity(), "The destination city on flight is null");
        assertNotNull(flight.getGate(), "The gate on the flight is null");

        //Se prueba que a cada atributo se le haya asignado su correspondiente valor
        assertTrue(flight.getDate().equals(date), "The dates are not equal");
        assertTrue(flight.getTime().equals("05:30 PM"), "The times are not equal");
        assertTrue(flight.getAirline().equals(airline), "The airlines are not equal");
        assertTrue(flight.getFlightNumber().equals(flighNumber), "The flight numbers are not equal");
        assertTrue(flight.getDestinationCity().equals(destination), "The destination cities are not equal");
        assertTrue(flight.getGate() == gate, "The gates are not equal");
    }

    @Test
    void compareToTest(){
        //Se prueba que el metodo compareTo retorne el valor -1 correctamente

        setupScenary2();

        String date = "2019-04-05";
        time = new Time(05, 30, "PM");
        String airline = "EasyFly";
        String flighNumber = "Esy 25000";
        String destination = "Cali";
        int gate = 5;

        Flight flight2 = new Flight(date, time, airline, flighNumber, destination, gate);

        assertTrue(flight.compareTo(flight2) == -1, "The expected value is different to -1");

        //Se prueba que el metodo compareTo retorne el valor 1 correctamente

        setupScenary2();

        String date2 = "2019-04-05";
        time = new Time(05, 30, "PM");
        String airline2 = "EasyFly";
        String flighNumber2 = "Esy 20000";
        String destination2 = "Cali";
        int gate2 = 5;

        Flight flight3 = new Flight(date2, time, airline2, flighNumber2, destination2, gate2);

        assertTrue(flight.compareTo(flight3) == 1, "The expected value is different to 1");

        //Se prueba que el metodo compareTo retorne el valor 0 correctamente

        setupScenary2();

        String date3 = "2019-04-05";
        time = new Time(05, 30, "PM");
        String airline3 = "EasyFly";
        String flighNumber3= "Esy 21500";
        String destination3 = "Cali";
        int gate3 = 5;

        Flight flight4 = new Flight(date3, time, airline3, flighNumber3, destination3, gate3);

        assertTrue(flight.compareTo(flight4) == 0, "The expected value is different to 0");
    }

}