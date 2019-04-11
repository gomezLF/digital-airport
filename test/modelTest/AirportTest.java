package modelTest;

import model.Airport;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    private Airport airport;

    private static final String AIRPORT_AIRLINE_DATA_TEST = "";
    private static final String AIRPORT_CITY_DATA_TEST = "";

    private void setupScenary1(){

    }

    private void setupScenary2(){
        airport = new Airport();
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

        assertTrue(airport.getFlightList().size() == size, "The size of the flight list is not" + size);

        for (int i = 0; i < airport.getFlightList().size(); i++) {
            assertNotNull(airport.getFlightList().get(i).getDate(), "The date, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getTime(), "The time, on flight " + i  + " is null");
            assertNotNull(airport.getFlightList().get(i).getAirline(), "The airline, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getFlightNumber(), "The flight number, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getDestinationCity(), "The destination city, on flight " + i + " is null");
            assertNotNull(airport.getFlightList().get(i).getGate(), "The gate, on flight " + i + " is null");
        }

        for (int i = 0; i < airport.getFlightList().size(); i++) {
            for (int j = 0; j < airport.getFlightList().size(); j++) {
                assertFalse(airport.getFlightList().get(i).getFlightNumber().compareToIgnoreCase(airport.getFlightList().get(j).getFlightNumber()) == 0, "The flight numbers, on flights " + i + " and " + j + "are the same");
            }
        }
    }

    @Test
    public void

}