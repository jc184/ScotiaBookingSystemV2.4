
package booking8;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>The {@code FlightManagerTest} class uses JUnit to create a manager
 * which tests setting Flight statuses to AVAILABLE, CHECKINGIN, BOARDING 
 * and CLOSED and destinations to GLASGOW or WESTERNISLES.</p>
 * <p>System testing: Testing aspects of the whole system against requirements
 * from the brief. eg A flight is full when all 24 seats are taken.</p>
 * <p>Unit testing: testing small units of code, eg individual methods, from
 * use cases such as Set Flight Status.</p>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @author James Chalmers 08003323
 * @version 2.4, 15-05-2010
 * @since JDK 1.5
 */

public class FlightManagerTest {

    /**
     * Create an instance of FlightManager:
     */
    FlightManager fmanager;

    /**
     * Test runs upon System start up and creates 2 separate flights
     * @throws java.io.IOException
     */
    @Before
    public void setUp() throws IOException {
        String sep = File.separator;
        String rootStorePath = "." + sep + "TestStore";
        //Create an instance:
        fmanager = new FlightManager(rootStorePath);
        fmanager.init();
        fmanager.setFlightStatus(FlightStatusEnum.AVAILABLE, DestinationEnum.GLASGOW);
        fmanager.setFlightStatus(FlightStatusEnum.AVAILABLE, DestinationEnum.WESTERNISLES);
    }

    /**
     * Test of init method, of class FlightManager.
     * This method unit tests the setting of Flight statuses and destinations.
     * @throws Exception
     */
    @Test
    public void testInit() throws Exception {
        fmanager.setFlightStatus(FlightStatusEnum.CHECKINGIN, DestinationEnum.GLASGOW);
        assertEquals(FlightStatusEnum.CHECKINGIN, fmanager.getFlightStatus(DestinationEnum.GLASGOW));
        fmanager.setFlightStatus(FlightStatusEnum.BOARDING, DestinationEnum.GLASGOW);
        assertEquals(FlightStatusEnum.BOARDING, fmanager.getFlightStatus(DestinationEnum.GLASGOW));
        fmanager.setFlightStatus(FlightStatusEnum.CLOSED, DestinationEnum.GLASGOW);
        assertEquals(FlightStatusEnum.CLOSED, fmanager.getFlightStatus(DestinationEnum.GLASGOW));
        assertEquals(FlightStatusEnum.AVAILABLE, fmanager.getFlightStatus(DestinationEnum.WESTERNISLES));
        fmanager.setFlightStatus(FlightStatusEnum.CHECKINGIN, DestinationEnum.WESTERNISLES);
        assertEquals(FlightStatusEnum.CHECKINGIN, fmanager.getFlightStatus(DestinationEnum.WESTERNISLES));
        fmanager.setFlightStatus(FlightStatusEnum.BOARDING, DestinationEnum.WESTERNISLES);
        assertEquals(FlightStatusEnum.BOARDING, fmanager.getFlightStatus(DestinationEnum.WESTERNISLES));
        fmanager.setFlightStatus(FlightStatusEnum.CLOSED, DestinationEnum.WESTERNISLES);
        assertEquals(FlightStatusEnum.CLOSED, fmanager.getFlightStatus(DestinationEnum.WESTERNISLES));
    }

}
