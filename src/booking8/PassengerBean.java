
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Passenger Bean for the Scotia Airways Airline Reservation System</p>
 * @author James Chalmers 08003323
 * @version 2.4, 09-05-2010
 * @since JDK 1.5
 */

public class PassengerBean implements Serializable {
    private static final long serialVersionUID = 7110257553910990723L;

    private int passengerID;
    private String passengerName;
    private PassengerTypeEnum passengerType;

    public PassengerBean(int passengerID, String passengerName, PassengerTypeEnum passengerType) {
        this.passengerID = passengerID;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public PassengerTypeEnum getPassengerType() {
        return passengerType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PassengerBean other = (PassengerBean) obj;
        if (this.passengerID != other.passengerID) {
            return false;
        }
        if ((this.passengerName == null) ? (other.passengerName != null) : !this.passengerName.equals(other.passengerName)) {
            return false;
        }
        if (this.passengerType != other.passengerType) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.passengerID;
        hash = 59 * hash + (this.passengerName != null ? this.passengerName.hashCode() : 0);
        hash = 59 * hash + (this.passengerType != null ? this.passengerType.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("Ordinary Passenger:\n");
        toStringBuilder.append("Passenger ID: ");
        toStringBuilder.append(passengerID);
        toStringBuilder.append("\nPassenger Name: ");
        toStringBuilder.append(passengerName);
        toStringBuilder.append("\nPassenger Type: ");
        toStringBuilder.append(passengerType);
        return toStringBuilder.toString();
    }
    
}
