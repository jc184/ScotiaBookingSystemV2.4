
package booking8;

import java.io.IOException;
import java.util.Vector;

/**
 * <p>The {@code PassengerManager} class creates a manager which adds and removes
 * ordinary Passengers, Western Isles Passengers and Business Passengers by using
 * Inheritance. It also generates a unique ID for each passenger, and holds the data
 * in a persistent state. See folder Teststore/data/PassengerManager</p>
 * <p>Possible example of adding Passengers:</p>
 *
 * <blockquote><pre>
 * PassengerManager pmanager = new PassengerManager(rootStorePath);
 * //Adding an ordinary, a Business, and a Western Isles passenger:
 * pmanager.addPassenger("Angus MacNeill", PassengerTypeEnum.ORDINARY);
 * pmanager.addBusiness("Ian MacNicol", PassengerTypeEnum.BUSINESS, "Atlantic Fishfarms Ltd");
 * pmanager.addIslander("John Alexander", PassengerTypeEnum.ISLANDER, "North Uist");
 * </pre></blockquote>
 * <p>
 * Adds examples of the three categories above.
 * </p>
 * <p>Possible example of removing Passengers:</p>
 * <blockquote><pre>
 * pmanager.removePassenger(1);//Removes Passenger with PassengerID 1
 * </pre></blockquote>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @author James Chalmers 08003323
 * @version 2.4, 13-05-2010
 * @since JDK 1.5
 */
public class PassengerManager {

    ObjectPersistence<PassengerBean> passengerStore;

    /**
     * Creates an empty PassengerManager. Bookings can be added,
     * retrieved and removed.
     * @param rootStorePath
     */
    public PassengerManager(String rootStorePath) {
        passengerStore = new ObjectPersistence<PassengerBean>(rootStorePath, "PassengerManager");
    }

    /**
     * Adds an ordinary passenger to the PassengerManager.
     * @param passengerName
     * @param passengerType
     * @return 
     * @exception IOException on input error
     */
    public int addPassenger(String passengerName, PassengerTypeEnum passengerType) throws IOException {
        int passengerID = getPID();
        PassengerBean passenger = new PassengerBean(passengerID, passengerName, passengerType);
        passengerStore.saveObject(passenger, passenger.getPassengerID());
        return (passengerID);
    }
    
    /**
     * Adds a Business passenger to the PassengerManager.
     * @param passengerName
     * @param passengerType
     * @param company
     * @exception IOException on input error
     */
    public int addBusiness(String passengerName, PassengerTypeEnum passengerType, String company) throws IOException {
        int passengerID = getPID();
        PassengerBean business = new BusinessBean(passengerID, passengerName, passengerType, company);
        passengerStore.saveObject(business, business.getPassengerID());
        return (passengerID);
    }

    /**
     * Adds a Western Isles passenger to the PassengerManager.
     * @param passengerName
     * @param passengerType
     * @param island
     * @return 
     * @exception IOException on input error
     */
    public int addIslander(String passengerName, PassengerTypeEnum passengerType, String island) throws IOException {
        int passengerID = getPID();
        PassengerBean islander = new IslanderBean(passengerID, passengerName, passengerType, island);
        passengerStore.saveObject(islander, islander.getPassengerID());
        return (passengerID);
    }

    /**
     * Returns all the passengers
     * @exception IOException on input error
     * @return a vector of PassengerBeans
     */
    public Vector<PassengerBean> getAllPassengers() throws IOException {
        return passengerStore.getAllObjects();
    }

    /**
     * Removes an passenger from the passengerStore with the given passengerID
     * @param passengerID
     * @exception IOException on input error
     */
    public void removePassenger(int passengerID) throws IOException {
        passengerStore.deleteObject(passengerID);
    }

    /**
     * Gets a passenger from the passengerStore by using the passenger ID
     * @param PassengerID
     * @exception IOException on input error
     * @return getPassenger(PassengerID)
     */
    public PassengerBean getPassenger(int PassengerID) throws IOException {
        for (PassengerBean p : passengerStore.getAllObjects()) {
            if (p.getPassengerID() == PassengerID) {
                return (p);
            }
        }
        return getPassenger(PassengerID);
    }

    /**
     * Creates and returns a unique passengerID
     * @exception IOException on input error
     * @exception IndexOutOfBoundsException
     * @return id
     */
    protected int getPID() throws IOException {
        Vector<Integer> passengerIDs = new Vector<Integer>();
        for (PassengerBean p : passengerStore.getAllObjects()) {
            passengerIDs.add(p.getPassengerID());
        }
        for (int id = 1; id < Integer.MAX_VALUE; id++) {
            if (!passengerIDs.contains(id)) {
                return id;
            }
        }
        throw new IndexOutOfBoundsException("All the passengerIDs have been taken");
    }
}
