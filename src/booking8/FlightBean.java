
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Flight Bean for the Scotia Airways Airline Reservation System</p>
 * @author James Chalmers 08003323
 * @version 2.4, 08-05-2010
 * @since JDK 1.5
 */

public class FlightBean implements Serializable {
    private static final long serialVersionUID = 7468484161948522989L;


    private FlightStatusEnum status;
    private DestinationEnum destination;

    public FlightBean(FlightStatusEnum status, DestinationEnum destination) {
        this.status = status;
        this.destination = destination;
    }

    public DestinationEnum getDestination() {
        return destination;
    }

    public FlightStatusEnum getStatus() {
        return status;
    }

    public void setDestination(DestinationEnum destination) {
        this.destination = destination;
    }

    public void setStatus(FlightStatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlightBean other = (FlightBean) obj;
        if (this.status != other.status) {
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
        hash = 53 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 53 * hash + (this.destination != null ? this.destination.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("Flight details:\n");
        toStringBuilder.append("\nFlight Status: ");
        toStringBuilder.append(status);
        toStringBuilder.append("\nFlight Destination: ");
        toStringBuilder.append(destination);
        return toStringBuilder.toString();
    }

}
