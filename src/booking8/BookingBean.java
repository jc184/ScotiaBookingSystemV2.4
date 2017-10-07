
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Booking Bean for the Scotia Airways Airline Reservation System</p>
 * @author James Chalmers 08003323
 * @version 2.4, 06-05-2010
 * @since JDK 1.5
 */

public class BookingBean implements Serializable {
    private static final long serialVersionUID = 1020664249889380497L;

    private int bookingID;
    private int passengerID;
    private DestinationEnum destination;

    public BookingBean(int bookingID, int passengerID, DestinationEnum destination) {
        this.bookingID = bookingID;
        this.passengerID = passengerID;
        this.destination = destination;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public DestinationEnum getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookingBean other = (BookingBean) obj;
        if (this.bookingID != other.bookingID) {
            return false;
        }
        if (this.passengerID != other.passengerID) {
            return false;
        }
        if (this.destination != other.destination) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.bookingID;
        hash = 37 * hash + this.passengerID;
        hash = 37 * hash + (this.destination != null ? this.destination.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("Booking details:\n");
        toStringBuilder.append("Booking ID: ");
        toStringBuilder.append(bookingID);
        toStringBuilder.append("\nPassenger ID: ");
        toStringBuilder.append(passengerID);
        toStringBuilder.append("\nDestination: ");
        toStringBuilder.append(destination);
        return toStringBuilder.toString();
    }

}
