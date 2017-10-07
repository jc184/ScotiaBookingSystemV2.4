package booking8;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>The {@code PassengerManagerTest} class uses JUnit to create a manager
 * which tests adding, removing, and retrieving Passenger details.</p>
 * <p>It also uses System testing to demonstrate that Inheritance works as intended</p>
 * <p>System testing: Testing aspects of the whole system against requirements
 * from the brief. eg A flight is full when all 24 seats are taken.</p>
 * <p>Unit testing: testing small units of code, eg individual methods, from
 * use cases such as Add Passenger.</p>
 * <p>Scotia Airways Airline Reservation System</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @author James Chalmers 08003323
 * @version 2.4, 13-05-2010
 * @since JDK 1.5
 */
public class PassengerManagerTest {

    PassengerManager pmanager;

    @Before
    public void setUp() {
        String sep = File.separator;
        String rootStorePath = "." + sep + "TestStore";
        pmanager = new PassengerManager(rootStorePath);
    }

    /**
     * The contents of the PassengerManager folder containing the
     * all the *.dat files must be erased after each test run.
     * This will prevent them from building up continuously.
     * @throws IOException
     */
    @After
    public void tearDown() throws IOException {
        Vector<Integer> deleteIds = new Vector<Integer>();
        for (PassengerBean p : pmanager.getAllPassengers()) {
            deleteIds.add(p.getPassengerID());
        }
        //Iterate through and delete Bookings:
        for (int i : deleteIds) {
            pmanager.removePassenger(i);
        }
    }

    /**
     * System tests basic functions of PassengerManager
     * Unit tests individual aspects of methods
     * @throws java.io.IOException
     */
    @Test
    public void testCore() throws IOException {
        int id1 = pmanager.addPassenger("Angus MacNeill", PassengerTypeEnum.ORDINARY);
        PassengerBean pb1 = new PassengerBean(id1, "Angus MacNeill", PassengerTypeEnum.ORDINARY);
        assertEquals(pb1, pmanager.getPassenger(id1));

        int id2 = pmanager.addIslander("John Alexander", PassengerTypeEnum.ISLANDER, "North Uist");
        PassengerBean pb2 = new IslanderBean(id2, "John Alexander", PassengerTypeEnum.ISLANDER, "North Uist");
        assertEquals(pb2, pmanager.getPassenger(id2));

        int id3 = pmanager.addBusiness("Ian MacNicol", PassengerTypeEnum.BUSINESS, "Atlantic Fishfarms Ltd");
        PassengerBean pb3 = new BusinessBean(id3, "Ian MacNicol", PassengerTypeEnum.BUSINESS, "Atlantic Fishfarms Ltd");
        assertEquals(pb3, pmanager.getPassenger(id3));

        pmanager.removePassenger(id1);
        pmanager.removePassenger(id2);
        pmanager.removePassenger(id3);
        assertEquals(0, pmanager.getAllPassengers().size());
        pmanager.addPassenger("Angus MacNeill", PassengerTypeEnum.ORDINARY);
        assertEquals(1, pmanager.getAllPassengers().size());
        pmanager.addIslander("John Alexander", PassengerTypeEnum.ISLANDER, "North Uist");
        assertEquals(2, pmanager.getAllPassengers().size());
        pmanager.addBusiness("Ian MacNicol", PassengerTypeEnum.BUSINESS, "Atlantic Fishfarms Ltd");
        assertEquals(3, pmanager.getAllPassengers().size());
    }

    /**
     * Test of addPassenger method, of class PassengerManager.
     * User enters Name and Passenger Type (Passenger, Islander or Business) 
     * and the data is added.
     * @throws Exception 
     */
    @Test
    public void testAddPassenger() throws Exception {
        pmanager.addPassenger("Angus MacNeill", PassengerTypeEnum.ORDINARY);
        pmanager.addBusiness("Ian MacNicol", PassengerTypeEnum.BUSINESS, "Atlantic Fishfarms Ltd");
        pmanager.addIslander("John Alexander", PassengerTypeEnum.ISLANDER, "North Uist");
        System.out.println("Test of Add Passenger:");
        for (PassengerBean p : pmanager.getAllPassengers()) {
            System.out.println("\n" + p.toString());
        }
    }

    /**
     * Test of removePassenger method, of class PassengerManager.
     * User enters the Passenger ID, and the data is removed
     * @throws Exception
     */
    @Test
    public void testRemovePassenger() throws Exception {
        pmanager.addPassenger("Angus MacNeill", PassengerTypeEnum.ORDINARY);
        pmanager.addBusiness("Angus McDonald", PassengerTypeEnum.BUSINESS, "Dualchas Architects");
        pmanager.addIslander("Sean McGowan", PassengerTypeEnum.ISLANDER, "Skye");
        pmanager.addIslander("Iain MacNiceall", PassengerTypeEnum.ISLANDER, "Rua Fiola");
        pmanager.addBusiness("Fiona McPherson", PassengerTypeEnum.BUSINESS, "Scottish Environmental Protection Agency");
        pmanager.removePassenger(1);//Removes Passenger with PassengerID 1
        pmanager.removePassenger(2);//Removes Passenger with PassengerID 2
        pmanager.removePassenger(3);//Removes Passenger with PassengerID 3
        pmanager.removePassenger(4);//Removes Passenger with PassengerID 4
        //Will show only the remaining passenger(s)
        System.out.println("\nTest of Remove Passenger:");
        for (PassengerBean p : pmanager.getAllPassengers()) {
            System.out.println("\n" + "Passenger(s) remaining:");
            System.out.println(p);
        }
    }

    /**
     * Test of getPassenger method, of class PassengerManager.
     * User enters Passenger ID and the single Passengers details are shown.
     * @throws Exception
     */
    @Test
    public void testGetPassenger() throws Exception {
        pmanager.addPassenger("Seamus McLeod", PassengerTypeEnum.ORDINARY);
        pmanager.addPassenger("Aonghais Dòmhnallach", PassengerTypeEnum.ORDINARY);
        pmanager.addBusiness("Hamish McLennan", PassengerTypeEnum.BUSINESS, "Stornoway Ship Builders Ltd");
        pmanager.addBusiness("Sean Mac a' Ghobhainn", PassengerTypeEnum.BUSINESS, "Qinetic");
        pmanager.addIslander("Shona Nic a' Phearsain", PassengerTypeEnum.ISLANDER, "Benbecula");
        pmanager.addIslander("Stewart McLean", PassengerTypeEnum.ISLANDER, "Colonsay");
        System.out.println("\nTest of a single booking, by ID:");
        System.out.println("\n" + pmanager.getPassenger(3));
    }

    /**
     * Test of getPID() method of class PassengerManager
     * Tests that generated Passenger IDs match order in which Passengers are added
     * and that IDs removed are taken by the next Passenger to be added
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPID() throws Exception {
        int id1 = pmanager.addPassenger("Seamus McLeod", PassengerTypeEnum.ORDINARY);
        int id2 = pmanager.addBusiness("Hamish McLennan", PassengerTypeEnum.BUSINESS, "Stornoway Ship Builders Ltd");
        int id3 = pmanager.addIslander("Shona Nic a' Phearsain", PassengerTypeEnum.ISLANDER, "Benbecula");
        int id4 = pmanager.addIslander("Stewart McLean", PassengerTypeEnum.ISLANDER, "Colonsay");
        assertEquals(1, id1);
        assertEquals(2, id2);
        assertEquals(3, id3);
        assertEquals(4, id4);
        pmanager.removePassenger(id1);
        int id5 = pmanager.addPassenger("Aonghais Dòmhnallach", PassengerTypeEnum.ORDINARY);
        assertEquals(1, id5);
        int id6 = pmanager.addIslander("Shona Nic a' Phearsain", PassengerTypeEnum.ISLANDER, "Benbecula");
        assertEquals(5, id6);
    }
}
