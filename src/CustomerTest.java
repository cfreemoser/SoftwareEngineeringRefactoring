import java.lang.reflect.Field;
import java.util.Random;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Created by freim on 10.05.2017.
 */

// Im sorry for that...
public class CustomerTest {

    private Rental r1, r2;
    private Customer c1;

    @org.junit.Before
    public void setUp() throws Exception {
        Movie m1 = new Movie("movie1", 1);
        Movie m2 = new Movie("movie2", 2);
         r1 = new Rental(m1, 10);
         r2 = new Rental(m2, 5);
         c1 = new Customer("Franziska Thompson");

    }

    @org.junit.Test
    public void addRental() throws Exception {
        Field f = c1.getClass().getDeclaredField("rentals");
        f.setAccessible(true);
        Vector rentals = (Vector) f.get(c1);

        c1.addRental(r1);
        c1.addRental(r2);

        assertTrue(rentals.size() == 2);
    }


    @org.junit.Test
    public void statement() throws Exception {

        c1.addRental(r1);
        c1.addRental(r2);

        String test =
                "Rental Record for Franziska Thompson\n" +
                "\tTitle\t\tDays\tAmount\n" +
                "\tmovie1\t\t10\t30.0\n" +
                "\tmovie2\t\t5\t4.5\n" +
                "Amount owed is 34.5\n" +
                "You earned 3 frequent renter points";

        assertTrue(c1.statement().equals(test));


    }

}