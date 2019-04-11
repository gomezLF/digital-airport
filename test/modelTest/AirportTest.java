package modelTest;

import model.Airport;
import model.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    private Airport airport;

    private static final String AIRPORT_AIRLINE_DATA_TEST = "dataTest/AirlineTest.txt";
    private static final String AIRPORT_CITY_DATA_TEST = "dataTest/CityTest.txt";

    private void setupScenary1(){

    }

    private void setupScenary2(){
        airport = new Airport();
    }

    private void setupScenary3(){
        airport = new Airport();

        int size = 1000;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }
    }

    private void setupScenary4(){
        airport = new Airport();
        int size = 50;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }

        airport.sortByDate();
    }

    private void setupScenary5(){
        airport = new Airport();
        int size = 100;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }

        airport.sortByTime();
    }

    private void setupScenary6(){
        airport = new Airport();
        int size = 150;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }
        airport.sortByAirline();
    }

    private void setupScenary7(){
        airport = new Airport();
        int size = 50;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }
        airport.sortByFlightNumber();
    }

    private void setupScenary8(){
        
    }

    @Test
    public void AirportTest(){
        setupScenary1();

        airport = new Airport();

        assertNotNull(airport.getFlightList(), "The flight list is null");
    }

    @Test
    public void createDataTest_1(){
        //Prueba donde se verifica el correcto lanzamiento de la exceptcion NegativeArraySizeException
        setupScenary2();

        int size = -20;

        try {
            airport.createData(size,AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
            fail("The size of the new list is a negative number");
        } catch (NegativeArraySizeException e) {
            assertTrue(true);
        }catch (IOException e) {
            e.printStackTrace();
        }

        //Prueba donde se verifica el No correcto lanzamiento de la excepcion NegativeArraySizeException
        setupScenary2();

        size = 50;

        try {
            airport.createData(size,AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDataTest_2(){
        setupScenary2();

        int size = 20;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }

        //Se prueba de que el tamano de la lista sea igual al numero indicado de vuelos, en este caso 20.
        assertTrue(airport.getFlightList().size() == size, "The size of the flight list is not" + size);

        //Se prueba de que cada parametro de los vuelos de la lista, sean diferentes de null.
        for (int i = 0; i < airport.getFlightList().size(); i++) {
            assertNotNull(airport.getFlightList().get(i).getDate(), "The date, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getTime(), "The time, on flight " + i  + " is null");
            assertNotNull(airport.getFlightList().get(i).getAirline(), "The airline, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getFlightNumber(), "The flight number, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getDestinationCity(), "The destination city, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getGate(), "The gate, on flight " + i + " is null");
        }

        //Se prueba de que no hallan numeros repetidos de vuelos, en la lista de vuelos, ya que estos(los numeros) deben ser unicos.
        for (int i = 0; i < airport.getFlightList().size(); i++) {
            for (int j = 0; j < airport.getFlightList().size(); j++) {
                assertFalse(airport.getFlightList().get(i).getFlightNumber().compareToIgnoreCase(airport.getFlightList().get(j).getFlightNumber()) == 0 && i != j, "The flight numbers, on flights " + i + ": " + airport.getFlightList().get(i).getFlightNumber() + " and " + j + ": " + airport.getFlightList().get(j).getFlightNumber() + " are the same");
            }
        }
    }

    @Test
    public void sortByDateTest(){
        //Se prueba el metodo de ordenamiento por fecha con una pequeña cantidad de datos
        setupScenary4();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getDate().compareTo(flightList.get(i+1).getDate()) < 0 || flightList.get(i).getDate().compareTo(flightList.get(i+1).getDate())  == 0, "The date on flight " + i + " is greater than on flight" + (i+1));
        }

        //Se prueba el metodo de ordenamiento por fecha con una cantidad grande de datos
        setupScenary3();
        airport.sortByDate();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getDate().compareTo(flightList2.get(i+1).getDate()) < 0 || flightList2.get(i).getDate().compareTo(flightList2.get(i+1).getDate())  == 0, "The date on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    public void sortByTimeTest(){
        //Se prueba el metodo de ordenamiento por hora con una pequeña cantidad de datos
        setupScenary5();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getTime().compareTo(flightList.get(i+1).getTime()) < 0 || flightList.get(i).getTime().compareTo(flightList.get(i+1).getTime())  == 0, "The time on flight " + i + " is greater than on flight " + (i+1));
        }

        //Se prueba el metodo de ordenamiento por hora con una cantidad grande de datos
        setupScenary3();
        airport.sortByTime();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getTime().compareTo(flightList2.get(i+1).getTime()) < 0 || flightList2.get(i).getTime().compareTo(flightList2.get(i+1).getTime())  == 0, "The time on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    public void sortByAirlineTest(){
        //Se prueba el metodo de ordenamiento por aerolinea con una pequeña cantidad de datos
        setupScenary6();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getAirline().compareTo(flightList.get(i+1).getAirline()) < 0 || flightList.get(i).getAirline().compareTo(flightList.get(i+1).getAirline())  == 0, "The airline on flight " + i + " is greater than on flight " + (i+1));
        }

        //Se prueba el metodo de ordenamiento por aerolinia con una cantidad de datos grande
        setupScenary3();
        airport.sortByAirline();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getAirline().compareTo(flightList2.get(i+1).getAirline()) < 0 || flightList2.get(i).getAirline().compareTo(flightList2.get(i+1).getAirline())  == 0, "The airline on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    public void sortByFlightNumberTest(){
        //Se prueba el metodo de ordenamiento por numero de vuelo, con una pequeña cantidad de datos
        setupScenary7();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getFlightNumber().compareTo(flightList.get(i+1).getFlightNumber()) < 0, "The flight number on flight " + i + " is greater than on flight " + (i+1));
        }

        //Se prueba el metodo de ordenamiento por numero de vuelo, con una cantidad de datos grande
        setupScenary3();
        airport.sortByFlightNumber();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getFlightNumber().compareTo(flightList2.get(i+1).getFlightNumber()) < 0, "The flight number on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    public void sortByDestinationTest(){

    }

}