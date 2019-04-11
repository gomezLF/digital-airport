package modelTest;

import model.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    private Time time;

    private void setupScenary1(){

    }

    private void setupScenary2(){
        int hour = 9;
        int minute = 0;
        String dayMoment = "PM";

        time = new Time(hour, minute, dayMoment);
    }

    private void setupScenary3(){
        int hour = 10;
        int minute = 50;
        String dayMoment = "AM";

        time = new Time(hour, minute, dayMoment);
    }

    private void setupScenary4(){
        int hour = 11;
        int minute = 5;
        String dayMoment = "AM";

        time = new Time(hour, minute, dayMoment);
    }

    private void setupScenary5(){
        int hour = 1;
        int minute = 35;
        String dayMoment = "PM";

        time = new Time(hour, minute, dayMoment);
    }

    @Test
    void TimeTest(){
        int hour = 6;
        int minute = 30;
        String dayMoment = "PM";

        time = new Time(hour, minute, dayMoment);

        //Se prueba que cada atributo haya quedado diferente de null
        assertNotNull(time.getHour(), "The hour is null");
        assertNotNull(time.getMinute(), "The minute is null");
        assertNotNull(time.getDayMoment(), "The day moment is null");

        //Se prueba que a cada atributo se le haya asignado su correspondiente valor
        assertTrue(time.getHour() == hour, "The hours are not equal");
        assertTrue(time.getMinute() == minute, "The minutes are not equal");
        assertTrue(time.getDayMoment().equals(dayMoment), "The day moments are not equal");
    }

    @Test
    void toStringTest(){
        setupScenary2();

        String returned = "09:00 PM";
        assertTrue(time.toString().equals(returned), "The returned values (" + returned + ") are not equal");

        setupScenary3();
        String returned2 = "10:50 AM";
        assertTrue(time.toString().equals(returned2), "The returned values (" + returned2 + ") are not equal");

        setupScenary4();
        String returned3 = "11:05 AM";
        assertTrue(time.toString().equals(returned3), "The returned values (" + returned3 + ") are not equal");

        setupScenary5();
        String returned4 = "01:35 PM";
        assertTrue(time.toString().equals(returned4), "The returned values (" + returned4 + ") are not equal");
    }

}