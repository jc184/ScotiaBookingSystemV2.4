
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Islander Bean for the Scotia Airways Airline Reservation System.
 * It uses Inheritance by extending PassengerBean</p>
 * @author James Chalmers 08003323
 * @version 2.4, 06-05-2010
 * @since JDK 1.5
 */

public class IslanderBean extends PassengerBean implements Serializable {
    private static final long serialVersionUID = -6683380139778275351L;
    
    private String island;

    public IslanderBean(int passengerID, String passengerName, PassengerTypeEnum passengerType, String island) {
        super(passengerID, passengerName, passengerType);
        this.island = island;
    }

    public String getIsland() {
        return island;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IslanderBean other = (IslanderBean) obj;
        if ((this.island == null) ? (other.island != null) : !this.island.equals(other.island)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.island != null ? this.island.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("Western Isles Passenger:\n");
        toStringBuilder.append("Passenger ID: ");
        toStringBuilder.append(super.getPassengerID());
        toStringBuilder.append("\nPassenger Name: ");
        toStringBuilder.append(super.getPassengerName());
        toStringBuilder.append("\nPassenger Type: ");
        toStringBuilder.append(super.getPassengerType());
        toStringBuilder.append("\nIsland Name: ");
        toStringBuilder.append(island);
        return toStringBuilder.toString();
    }
}
