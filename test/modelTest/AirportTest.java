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

    }

    @Test
    void sortByDateTest(){

    }

    @Test
    void sortByTimeTest(){

    }

    @Test
    void sortByAirlineTest(){

    }

    @Test
    void sortByFlightNumberTest(){

    }

    @Test
    void sortByDestinationTest(){

    }

    @Test
    void sortByGateTest(){

    }

    @Test
    void searchByDateTest(){

    }

    @Test
    void searchByTimeTest(){

    }

    @Test
    void searchByAirline(){

    }

    @Test
    void searchByFlightNumberTest(){

    }

    @Test
    void searchByCityTest(){

    }

    @Test
    void searchByGateTest(){

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