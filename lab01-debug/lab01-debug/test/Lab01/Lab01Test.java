package Lab01;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Lab01Test {

    private Lab01Debug lab01;

    @Before
    public void before() {
        lab01 = new Lab01Debug();
    }
    @Test
    public void testActor() {
        // set a breakpoint on the line below to debug
        assertEquals("Chadwick Boseman", lab01.selectActor(2));
    }

    @Test
    public void testMovie() {
        // set a breakpoint on the line below to debug
        assertEquals("Captain Marvel (2019)", lab01.selectMovie(3));
    }

    @Test
    public void testMovie2() {
        // set a breakpoint on the line below to debug
        assertNotNull(lab01.selectMovie(8));
    }

    @Test
    public void testAvengerMovie() {
        // set a breakpoint on the lines below to debug
        assertTrue(lab01.isAvengerMovie("Avengers: Endgame"));
        assertFalse(lab01.isAvengerMovie("Avengers: Thanos Won, End of Story"));
    }

    @Test
    public void testCircularLeftShift() {
        String arr[] = {"Ant-Man", "Black Panther", "Captain America", "Doctor Strange", "Falcon", "Groot"}; // note: alphabetical
        // set a breakpoint on the line below to debug        
        lab01.circular_left_shift(arr);
        assertArrayEquals(new String[]{"Black Panther", "Captain America", "Doctor Strange", "Falcon", "Groot", "Ant-Man"}, arr);
    }
}
