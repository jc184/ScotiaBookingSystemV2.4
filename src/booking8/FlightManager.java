
package booking8;

import java.io.IOException;
import java.util.Vector;

/**
 * <p>The {@code FlightManager} class creates a manager which sets
 * Flight's statuses to AVAILABLE, CHECKINGIN, BOARDING and CLOSED.
 * The details are stored in a persistent state in Teststore/data/FlightManager
 * by using Object Persistence</p>
 * <p>Possible example of setting a Flight:</p>
 *
 * <blockquote><pre>
 *      FlightManager fmanager = new FlightManager(rootStorePath);
 *      //Set a Flights Status:
 *      fmanager.setFlightStatus(FlightStatusEnum.CHECKINGIN, DestinationEnum.GLASGOW);
 * </pre></blockquote>
 * <p>
 * Sets a Flight status to CHECKINGIN and destination to GLASGOW.
 * Using this method means the Flights always exist, so there is no need to
 * add or remove them.
 * </p>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight.
 * @author James Chalmers 08003323
 * @version 2.4, 15-05-2010
 * @since JDK 1.5
 */

public class FlightManager {

    ObjectPersistence<FlightBean> flightStore;

    /**
     * Creates an empty FlightManager
     * @param rootStorePath
     */
    public FlightManager(String rootStorePath) {
        flightStore = new ObjectPersistence<>(rootStorePath, "FlightManager");
    }

    /**
     * Initialises 2 flights to either destination
     * If system crashes, it initialises the flights automatically at startup.
     * This means that two flights always exist, only the status and destination changes.
     * @exception IOException
     */
    public void init() throws IOException {
        Vector<FlightBean> flightVec = flightStore.getAllObjects();
        if (flightVec.size() != 2) {
            flightStore.saveObject(new FlightBean(FlightStatusEnum.AVAILABLE, DestinationEnum.GLASGOW), 0);
            flightStore.saveObject(new FlightBean(FlightStatusEnum.AVAILABLE, DestinationEnum.WESTERNISLES), 1);
        }
    }

    /**
     * Returns all the flights held
     * @return A vector of all the FlightBeans
     * @exception IOException
     */
    public Vector<FlightBean> getAllFlights() throws IOException {
        return flightStore.getAllObjects();
    }

    /**
     * Gets the destination of a Flight
     * @param destination
     * @param status
     * @exception IOException
     * @return getStatus() (the status of the flight)
     */
    public FlightStatusEnum getFlightStatus(DestinationEnum destination) throws IOException {
        for (FlightBean fb : flightStore.getAllObjects()) {
            if (fb.getDestination().equals(destination)) {
                return fb.getStatus();
            }
        }
        throw new IOException("Flight unknown");
    }

    /**
     * Sets the status and destination of flights to AVAILABLE, CHECKINGIN,
     * BOARDING or CLOSED, and WESTERNISLES or GLASGOW.
     * @param status
     * @param destination
     * @exception IOException
     */
    public void setFlightStatus(FlightStatusEnum status, DestinationEnum destination) throws IOException {
        int id = destination.equals(DestinationEnum.GLASGOW) ? 0 : 1;
        for (FlightBean fb : flightStore.getAllObjects()) {
            if (fb.getDestination().equals(destination)) {
                fb.setStatus(status);
                flightStore.deleteObject(id);
                flightStore.saveObject(fb, id);
            }
        }
    }
}
