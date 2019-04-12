package modelTest;

import customExceptions.EmptyDataException;
import model.Airport;
import model.Flight;
import model.Time;
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
        airport = new Airport();
        int size = 100;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }
        airport.sortByDestination();
    }

    private void setupScenary9(){
        airport = new Airport();
        int size = 150;

        try {
            airport.createData(size, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            fail("The files where the data is, was not found");
        } catch (NegativeArraySizeException e) {
            fail("The size of the new list is a negative number");
        }
        airport.sortByGate();
    }

    private void setupScenary10(){
        airport = new Airport();

        try {
            airport.createData(50, AIRPORT_AIRLINE_DATA_TEST, AIRPORT_CITY_DATA_TEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AirportTest(){
        setupScenary1();

        airport = new Airport();

        assertNotNull(airport.getFlightList(), "The flight list is null");
    }

    @Test
    void createDataTest_1(){
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

        //Prueba donde se verifica el funcionamiento a la hora de NO lanzar la excepcion NegativeArraySizeException
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
    void createDataTest_2(){
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
       assertEquals(airport.getFlightList().size(), size, "The size of the flight list is not" + size);

        //Se prueba de que cada parametro de los vuelos de la lista, sean diferentes de null.
        for (int i = 0; i < airport.getFlightList().size(); i++) {
            assertNotNull(airport.getFlightList().get(i).getDate(), "The date, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getTime(), "The time, on flight " + i  + " is null");
            assertNotNull(airport.getFlightList().get(i).getAirline(), "The airline, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getFlightNumber(), "The flight number, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getDestinationCity(), "The destination city, on flight " + i + " is null");
        }

        //Se prueba de que no hallan numeros repetidos de vuelos, en la lista de vuelos, ya que estos(los numeros) deben ser unicos.
        for (int i = 0; i < airport.getFlightList().size(); i++) {
            for (int j = 0; j < airport.getFlightList().size(); j++) {
                assertFalse(airport.getFlightList().get(i).getFlightNumber().compareToIgnoreCase(airport.getFlightList().get(j).getFlightNumber()) == 0 && i != j, "The flight numbers, on flights " + i + ": " + airport.getFlightList().get(i).getFlightNumber() + " and " + j + ": " + airport.getFlightList().get(j).getFlightNumber() + " are the same");
            }
        }
    }

    @Test
    void sortByDateTest(){
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
    void sortByTimeTest(){
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
    void sortByAirlineTest(){
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
    void sortByFlightNumberTest(){
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
    void sortByDestinationTest(){
        //Se prueba el metodo de ordenamiento por ciudad de destino con una pequeña cantidad de datos
        setupScenary8();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getDestinationCity().compareTo(flightList.get(i+1).getDestinationCity()) < 0 || flightList.get(i).getDestinationCity().compareTo(flightList.get(i+1).getDestinationCity())  == 0, "The destination city on flight " + i + " is greater than on flight " + (i+1));
        }

        //Se prueba el metodo de ordenamiento por ciudad de destino con una cantidad de datos grande
        setupScenary3();
        airport.sortByDestination();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getDestinationCity().compareTo(flightList2.get(i+1).getDestinationCity()) < 0 || flightList2.get(i).getDestinationCity().compareTo(flightList2.get(i+1).getDestinationCity())  == 0, "The destination city on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    void sortByGateTest(){
        //Se prueba el metodo de ordenamiento por ciudad de destino con una pequeña cantidad de datos
        setupScenary9();

        List<Flight> flightList = airport.getFlightList();

        for (int i = 0; i < flightList.size() - 1; i++) {
            assertTrue(flightList.get(i).getGate() < flightList.get(i+1).getGate() || flightList.get(i).getGate() == flightList.get(i+1).getGate(), "The gate on flight " + i + " is greater than on flight " + (i+1));
        }

        //Se prueba el metodo de ordenamiento por ciudad de destino con una cantidad de datos grande
        setupScenary3();
        airport.sortByGate();

        List<Flight> flightList2 = airport.getFlightList();

        for (int i = 0; i < flightList2.size() - 1; i++) {
            assertTrue(flightList2.get(i).getGate() < flightList2.get(i+1).getGate() || flightList2.get(i).getGate() == flightList2.get(i+1).getGate(), "The gate on flight " + i + " is greater than on flight" + (i+1));
        }
    }

    @Test
    void searchByDateTest(){
        //Aqui se prueba de que el metodo searchByDate lanze la excepcion EmptyDataExcepcion correctamente.
        setupScenary10();

        try {
            airport.searchByDate("");
            fail("The value of the date on flight is empty");
        } catch (EmptyDataException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByDate funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-05-15";
        Time t = new Time(6, 30, "PM");
        String airline = "Avianca";
        String flightNumber = "Aia 20000";
        String destination = "Bogota";
        int gate = 5;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
           f2 = airport.searchByDate(date);
        } catch (EmptyDataException e) {
            fail("The date on flight is empty");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getDate(), date, "The dates on flight are not equal");
    }

    @Test
    void searchByTimeTest(){
        //Aqui se prueba de que el metodo searchByTime lanze la excepcion EmptyDataExcepcion correctamente.
        setupScenary10();

        try {
            airport.searchByTime("");
            fail("The value of the date on flight is empty");
        } catch (EmptyDataException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByTime funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-08-20";
        Time t = new Time(8, 45, "PM");
        String airline = "EasyFly";
        String flightNumber = "Esy 35000";
        String destination = "Cali";
        int gate = 9;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
            f2 = airport.searchByTime(t.toString());
        } catch (EmptyDataException e) {
            fail("The date on flight is empty");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getTime(), t.toString(), "The times on flight are not equal");
    }

    @Test
    void searchByAirline(){
        //Aqui se prueba de que el metodo searchByAirline lanze la excepcion EmptyDataExcepcion correctamente.
        setupScenary10();

        try {
            airport.searchByAirline("");
            fail("The airline on flight is empty");
        } catch (EmptyDataException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByAirline funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-04-30";
        Time t = new Time(1, 0, "AM");
        String airline = "Satena";
        String flightNumber = "Sta 15000";
        String destination = "Brasilia";
        int gate = 1;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
            f2 = airport.searchByAirline(airline);
        } catch (EmptyDataException e) {
            fail("The airline on flight is empty");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getAirline(), airline, "The airlines on flight are not equal");
    }

    @Test
    void searchByFlightNumberTest(){
        //Aqui se prueba de que el metodo searchByFlightNumber lanze la excepcion EmptyDataExcepcion correctamente.
        setupScenary10();

        try {
            airport.searchByFlightNumber("");
            fail("The value of the flightNumber on flight is empty");
        } catch (EmptyDataException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByFlightNumber funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-10-01";
        Time t = new Time(5, 25, "AM");
        String airline = "Lanco";
        String flightNumber = "Lno 50000";
        String destination = "Cartagena";
        int gate = 15;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
            f2 = airport.searchByFlightNumber(flightNumber);
        } catch (EmptyDataException e) {
            fail("The value of the flightNumber on flight is empty");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getFlightNumber(), flightNumber, "The flight numbers on flight are not equal");
    }

    @Test
    void searchByCityTest(){
        //Aqui se prueba de que el metodo searchByCity lanze la excepcion EmptyDataExcepcion correctamente.
        setupScenary10();

        try {
            airport.searchByCity("");
            fail("The value of the city on flight is empty");
        } catch (EmptyDataException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByCity funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-11-10";
        Time t = new Time(10, 15, "PM");
        String airline = "Lanco";
        String flightNumber = "Lno 55300";
        String destination = "Quito";
        int gate = 15;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
            f2 = airport.searchByCity(destination);
        } catch (EmptyDataException e) {
            fail("The value of the city on flight is empty");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getDestinationCity(), destination, "The destination city on flight are not equal");
    }

    @Test
    void searchByGateTest(){
        //Aqui se prueba de que el metodo searchByGate lanze la excepcion NumberFormatException correctamente.
        setupScenary10();

        String parameter = "5J";

        try {
            airport.searchByGate(Integer.parseInt(parameter));
            fail("The value of the city on flight is empty");
        } catch (NumberFormatException e) {
            assertTrue(true);
        }


        //Aqui se prueba que el metodo searchByGate funcione correctamente retornando el vuelo que se busco.
        setupScenary10();

        String date = "2019-06-13";
        Time t = new Time(8, 20, "PM");
        String airline = "Avianca";
        String flightNumber = "Aia 20000";
        String destination = "Caracas";
        int gate = 20;

        Flight f = new Flight(date, t, airline, flightNumber, destination, gate);
        airport.getFlightList().add(f);

        Flight f2 = null;
        try {
            f2 = airport.searchByGate(gate);
        } catch (NumberFormatException e) {
            fail("The value of the gate is not a number");
        }

        assertNotNull(f2, "The flight is null");
        assertEquals(f2.getGate(), gate, "The value of the gate is not a number");
    }

    @Test
    void createPageTest(){
        //Se verifica que retorne los valores apropiados cuando esta en la pagina 0, con una lista de vuelos de tamaño 50
        setupScenary10();

        int to1 = 0;
        int from1 = 20;

        int[] array1 = airport.createPage(0);
        assertEquals(array1[0], to1, "The value in array1[0] is different to " + to1);
        assertEquals(array1[1], from1, "The value in array1[1] is different to " + from1);

        //Se verifica que retorne los valores apropiados cuando esta en la pagina 1, con una lista de vuelos de tamaño 50
        setupScenary10();

        int to2 = 20;
        int from2 = 40;

        int[] array2 = airport.createPage(1);
        assertEquals(array2[0], to2, "The value in array2[0] is different to " + to2);
        assertEquals(array2[1], from2, "The value in array2[1] is different to " + from2);

        //Se verifica que retorne los valores apropiados cuando esta en la pagina 2, con una lista de vuelos de tamaño 50
        int to3 = 40;
        int from3 = 50;

        int[] array3 = airport.createPage(2);
        assertEquals(array3[0], to3, "The value in array3[0] is different to " + to3);
        assertEquals(array3[1], from3, "The value in array3[1] is different to " + from3);
    }

}