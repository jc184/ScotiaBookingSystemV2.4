package booking8;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * <p>The {@code BookingManagerTest} class uses JUnit to create a manager
 * which tests adding, removing, and retrieving Passenger details.</p>
 * <p>System testing: Testing aspects of the whole system against requirements
 * from the brief. eg A flight is full when all 24 seats are taken.</p>
 * <p>Unit testing: testing small units of code, eg individual methods, from
 * use cases such as Add Booking.</p>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @author James Chalmers 08003323
 * @version 2.4, 12-05-2010
 * @since JDK 1.5
 */
public class BookingManagerTest {

    BookingManager bmanager;

    @Before
    public void setUp() {
        String sep = File.separator;
        String rootStorePath = "." + sep + "TestStore";
        bmanager = new BookingManager(rootStorePath);
    }

    /**
     * The contents of the BookingManager folder containing the *.dat files
     * must be erased after each test run.
     * This will prevent them from building up continuously.
     * @throws IOException
     */
    @After
    public void tearDown() throws IOException {
        for (BookingBean booking : bmanager.getAllBookings()) {
            bmanager.removeBooking(booking.getBookingID());
        }
    }

    /**
     * System tests basic functions of BookingManager
     * Unit tests individual aspects of methods
     * @throws java.io.IOException
     * @throws booking8.FlightFullException
     */
    @Test
    public void testCore() throws IOException, FlightFullException {
        int id = bmanager.addBooking(11, DestinationEnum.GLASGOW);
        BookingBean b = new BookingBean(id, 11, DestinationEnum.GLASGOW);
        assertEquals(b, bmanager.getBooking(id));
        bmanager.removeBooking(id);
        assertEquals(0, bmanager.getAllBookings().size());
        for (int i = 0; i < 24; i++) {
            bmanager.addBooking(i, DestinationEnum.GLASGOW);
        }
        try {
            bmanager.addBooking(57, DestinationEnum.GLASGOW);
            fail("this booking should not have been added");
        } catch (FlightFullException e) {
        }
        assertEquals(24, bmanager.getAllBookings().size());
        bmanager.addBooking(11, DestinationEnum.WESTERNISLES);
        assertEquals(25, bmanager.getAllBookings().size());
    }

    /**
     * Test of getBID() method of class BookingManager
     * Tests that generated Booking IDs match order in which Bookings are added
     * and that IDs removed are taken by the next Booking to be added
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBID() throws Exception {
        int id1 = bmanager.addBooking(11, DestinationEnum.GLASGOW);
        int id2 = bmanager.addBooking(12, DestinationEnum.WESTERNISLES);
        int id3 = bmanager.addBooking(13, DestinationEnum.GLASGOW);
        int id4 = bmanager.addBooking(14, DestinationEnum.WESTERNISLES);
        assertEquals(1, id1);
        assertEquals(2, id2);
        assertEquals(3, id3);
        assertEquals(4, id4);
        bmanager.removeBooking(id2);
        int id5 = bmanager.addBooking(15, DestinationEnum.GLASGOW);
        assertEquals(2, id5);
        int id6 = bmanager.addBooking(16, DestinationEnum.WESTERNISLES);
        assertEquals(5, id6);
        int id7 = bmanager.addBooking(17, DestinationEnum.WESTERNISLES);
        assertEquals(6, id7);
    }

    /**
     * Test of addBooking method, of class BookingManager.
     * User adds a booking by entering passengerID and Destination
     * and System generates a unique BookingID and records the details.
     * Passes if number of bookings is 24 or less - using System testing
     * Uncomment the last two lines to test more than 24 bookings (Fail).
     * @throws Exception
     */
    @Test
    public void testAddBooking() throws Exception {
        bmanager.addBooking(203, DestinationEnum.GLASGOW);
        bmanager.addBooking(217, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(232, DestinationEnum.GLASGOW);
        bmanager.addBooking(266, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(287, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(289, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(303, DestinationEnum.GLASGOW);
        bmanager.addBooking(317, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(332, DestinationEnum.GLASGOW);
        bmanager.addBooking(366, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(387, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(389, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(403, DestinationEnum.GLASGOW);
        bmanager.addBooking(417, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(432, DestinationEnum.GLASGOW);
        bmanager.addBooking(466, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(487, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(489, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(503, DestinationEnum.GLASGOW);
        bmanager.addBooking(517, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(532, DestinationEnum.GLASGOW);
        bmanager.addBooking(566, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(587, DestinationEnum.WESTERNISLES);
//        bmanager.addBooking(589, DestinationEnum.WESTERNISLES);
//        bmanager.addBooking(999, DestinationEnum.WESTERNISLES);
        System.out.println("\nTest of Add Booking:");
        for (BookingBean b : bmanager.getAllBookings()) {
            System.out.println("\n" + b);
        }
    }

    /**
     * Test of getBooking method, of class BookingManager.
     * Retrieves passenger details by entering the BookingID.
     * @throws Exception
     */
    @Test
    public void testGetBooking() throws Exception {
        bmanager.addBooking(312, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(334, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(332, DestinationEnum.GLASGOW);
        bmanager.addBooking(366, DestinationEnum.GLASGOW);
        bmanager.addBooking(387, DestinationEnum.WESTERNISLES);
        System.out.println("\nTest of a single booking, by ID:");
        System.out.println(bmanager.getBooking(3));//Shows the single booking of a given ID.
    }

    /**
     * Test of getAllBookings method, of class BookingManager.
     * @throws Exception
     */
    @Test
    public void testGetAllBookings() throws Exception {
    }

    /**
     * Test of removeBooking method, of class BookingManager.
     * Deletes a Booking once its ID has been entered.
     * @throws Exception
     */
    @Test
    public void testRemoveBooking() throws Exception {
        bmanager.addBooking(406, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(423, DestinationEnum.GLASGOW);
        bmanager.addBooking(435, DestinationEnum.WESTERNISLES);
        bmanager.addBooking(477, DestinationEnum.GLASGOW);
        bmanager.addBooking(491, DestinationEnum.GLASGOW);
        bmanager.removeBooking(1);//Removes Booking with BookingID 1
        bmanager.removeBooking(2);//Removes Booking with BookingID 2
        bmanager.removeBooking(3);//Removes Booking with BookingID 3
        bmanager.removeBooking(4);//Removes Booking with BookingID 4
        System.out.println("\nTest of Remove Booking:");
        System.out.println("\nBooking(s) remaining:");
        for (BookingBean b : bmanager.getAllBookings()) {
            System.out.println("\n" + b);
        }
    }
    
}
