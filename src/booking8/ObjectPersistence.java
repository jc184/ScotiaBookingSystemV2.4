package booking8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * <p>This class allows the whole Airline Reservation System to save input data
 * to persistent storage</p>
 * Iteration 2.4: Add Booking, Add Passenger, Business, Islander (Inheritance),
 * Add Flight
 * @param <T>
 * @author James Chalmers 08003323
 * @version 2.4, 06-05-2010
 * @since JDK 1.5
 */
public class ObjectPersistence<T> {


    File objectDirectory;

    /**
     * Creates File, file path and directory structure for objects to be saved
     * @param rootStorePath 
     * @param dirName
     */
    public ObjectPersistence(String rootStorePath, String dirName) {
        String childPath = File.separator + "data" + File.separator + dirName + File.separator;
        objectDirectory = new File(rootStorePath, childPath);
        //Create a directory structure:
        objectDirectory.mkdirs();
    }

    /**
     * Allows Bookings, Passengers and Flights to be saved
     * @param o 
     * @param ID
     * @throws IOException on input error
     */
    public void saveObject(T o, int ID) throws IOException {
        File objectFile = new File(objectDirectory, ID + ".dat");
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(new FileOutputStream(objectFile));
        oos.writeObject(o);
        oos.close();
    }

    /**
     * Allows Bookings, Passengers and Flights to be deleted
     * @param ID
     * @throws IOException on input error
     */
    public void deleteObject(int ID) throws IOException {
        File objectFile = new File(objectDirectory, ID + ".dat");
        objectFile.delete();
    }

    /**
     * Returns a vector of all the objects
     * @return vector of all objects
     * @throws IOException on input error
     */
    public Vector<T> getAllObjects() throws IOException {
        File fileName;
        T readObject;
        Vector<T> allObjects = new Vector<T>();

        String[] dirList = objectDirectory.list();
        for (int i = 0; i < dirList.length; i++) {
            if (dirList[i].endsWith(".dat")) {
                fileName = new File(objectDirectory, dirList[i]);
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(new FileInputStream(fileName));
                try {
                    readObject = (T) ois.readObject();
                } catch (ClassNotFoundException e) {
                    throw new IOException("Problem reading " + dirList[i] + " from directory: " + e.getMessage());
                }
                ois.close();
                allObjects.add(readObject);
            }
        }
        //Return the vector
        return (allObjects);
    }
}
