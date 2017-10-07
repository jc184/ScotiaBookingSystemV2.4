
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Business Bean for the Scotia Airways Airline Reservation System.
 * It uses Inheritance by extending PassengerBean</p>
 * @author James Chalmers 08003323
 * @version 2.4, 06-05-2010
 * @since JDK 1.5
 */

public class BusinessBean extends PassengerBean implements Serializable {
    private static final long serialVersionUID = -1329105547744520548L;

    private String company;

    public BusinessBean(int passengerID, String passengerName, PassengerTypeEnum passengerType, String business) {
        super(passengerID, passengerName, passengerType);
        this.company = business;
    }

    public String getBusiness() {
        return company;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BusinessBean other = (BusinessBean) obj;
        if ((this.company == null) ? (other.company != null) : !this.company.equals(other.company)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.company != null ? this.company.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("Business Passenger:\n");
        toStringBuilder.append("Passenger ID: ");
        toStringBuilder.append(super.getPassengerID());
        toStringBuilder.append("\nPassenger Name: ");
        toStringBuilder.append(super.getPassengerName());
        toStringBuilder.append("\nPassenger Type: ");
        toStringBuilder.append(super.getPassengerType());
        toStringBuilder.append("\nBusiness Name: ");
        toStringBuilder.append(company);
        return toStringBuilder.toString();
    }
}
