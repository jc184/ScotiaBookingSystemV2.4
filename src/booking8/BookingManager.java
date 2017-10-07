
package booking8;

import java.io.IOException;
import java.util.Vector;

/**
 * <p>The {@code BookingManager} class creates a manager which adds and removes 
 * Bookings, and saves the details in a persistent stored state by using Object
 * Persistence. See folder Teststore/data/BookingManager</p>
 * <p>Adding a Booking might look like this:</p>
 *
 * <blockquote><pre>
 *      BookingManager bmanager = new BookingManager(rootStorePath);
 *      //Add a Booking:
 *      bmanager.addBooking(203, DestinationEnum.GLASGOW);
 * </pre></blockquote>
 * <p>
 * This example adds a booking with a Booking ID of 203, and destination of Glasgow
 * </p>
 * <p>Removing a Booking might look like this:</p>
 * <blockquote><pre>
 * bmanager.removeBooking(2);//Removes Booking with BookingID 2
 * </pre></blockquote>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @author James Chalmers 08003323
 * @version 2.4, 12-05-2010
 * @since JDK 1.5
 */
public class BookingManager {

    ObjectPersistence<BookingBean> bookingStore;
    private int maxGSeats;
    private int maxWSeats;

    /**
     * Creates an empty BookingManager. Bookings can be added,
     * retrieved and removed.
     * @param rootStorePath
     */
    public BookingManager(String rootStorePath) {
        bookingStore = new ObjectPersistence<BookingBean>(rootStorePath, "BookingManager");
        maxGSeats = 0;
        maxWSeats = 0;
    }

    /**
     * Allows an item to be added to the BookingManager, but throws an exception
     * if too many Bookings are added.
     * @param bookingID
     * @param passengerID
     * @param destination
     * @exception IOException on input error
     * @exception FlightFullException on adding beyond 24
     */
    public int addBooking(int passengerID, DestinationEnum destination) throws IOException, FlightFullException {
        int maxSeats = destination.equals(DestinationEnum.GLASGOW) ? maxGSeats : maxWSeats;
        if (maxSeats < 24) {
            int bookingID = getBID();
            BookingBean booking = new BookingBean(bookingID, passengerID, destination);
            bookingStore.saveObject(booking, booking.getBookingID());
            if (destination.equals(DestinationEnum.GLASGOW)) {
                maxGSeats++;
            } else {
                maxWSeats++;
            }
            return (bookingID);
        } else {
            throw new FlightFullException("The Flight is Full");
        }
    }

    /**
     * Returns all the bookings held
     * @exception IOException on input error
     * @return a vector of BookingBeans
     */
    public Vector<BookingBean> getAllBookings() throws IOException {
        return bookingStore.getAllObjects();
    }

    /**
     * Removes an booking from the bookingStore with a specified bookingID.
     * Decrements the Seat counter on the flight concerned
     * @exception IOException on input error
     * @param bookingID
     */
    public void removeBooking(int bookingID) throws IOException {
        if (getBooking(bookingID).getDestination().equals(DestinationEnum.GLASGOW)) {
            maxGSeats--;
        } else {
            maxWSeats--;
        }
        bookingStore.deleteObject(bookingID);
    }

    /**
     * Gets a booking from the bookingStore referenced by the booking ID
     * @param bookingID
     * @exception IOException on input error
     * @return getBooking(bookingID)
     */
    public BookingBean getBooking(
            int bookingID) throws IOException {
        for (BookingBean b : bookingStore.getAllObjects()) {
            if (b.getBookingID() == bookingID) {
                return (b);
            }

        }
        return getBooking(bookingID);
    }

    /**
     * Returns a unique bookingID
     * @exception IOException on input error
     * @exception IndexOutOfBoundsException on no more IDs available
     * @return id
     */
    protected int getBID() throws IOException {
        Vector<Integer> bookingIDs = new Vector<Integer>();
        for (BookingBean b : bookingStore.getAllObjects()) {
            bookingIDs.add(b.getBookingID());
        }
        for (int id = 1; id <
                Integer.MAX_VALUE; id++) {
            if (!bookingIDs.contains(id)) {
                return id;
            }
        }
        throw new IndexOutOfBoundsException("All the IDs have been used up");
    }
}
