
package booking8;

import java.io.Serializable;

/**
 * <p>This class is a Enum of flight statuses for the Scotia Airways Airline
 * Reservation System</p>
 * @author: James Chalmers 08003323
 * @version: 2.4, 08-05-2010
 * @since JDK 1.5
 */
public enum FlightStatusEnum  implements Serializable {

    AVAILABLE, //returns 1 when FlightStatus is set to AVAILABLE
    CHECKINGIN,//returns 2 when FlightStatus is set to CHECKING IN
    BOARDING,  //returns 3 when FlightStatus is set to BOARDING
    CLOSED;    //returns 4 when FlightStatus is set to CLOSED

}
