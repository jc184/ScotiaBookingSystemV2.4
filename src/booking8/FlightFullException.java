
package booking8;

/**
 * <p>This class is a Exception for the Scotia Airways Airline Reservation System</p>
 * @author James Chalmers 08003323
 * @version 2.4, 06-05-2010
 * @since JDK 1.5
 */
public class FlightFullException extends Exception {
    private static final long serialVersionUID = 9059845667732509785L;

    /**
     * Constructs an instance of <code>FlightFullException</code> with a detail message.
     * @param msg the detail message "The Flight is Full" in AddBooking() in BookingManager.
     */
    public FlightFullException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>FlightFullException</code> without a detail message.
     */
    public FlightFullException() {
    }
}
