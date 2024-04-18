package PizzaApp;

import java.io.*;
import java.util.List;

/**
 * The DataManager class provides static methods for writing to and reading from files
 * the lists of Pizza and Order objects. It uses serialization to store objects.
 * @author Kenneth Heimann
 */
public class DataManager{

    /**
     * Writes a list of Pizza objects to a file using serialization.
     *
     * @param pizzaList the list of Pizza objects to be written to the file.
     * @param filename the name of the file to which the pizza list will be written.
     */
    public static void writePizzasToFile(List<Pizza> pizzaList, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pizzaList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the pizza list to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads a list of Pizza objects from a file using serialization.
     *
     * @param filename the name of the file from which to read the pizza list.
     * @return the list of Pizza objects read from the file, or null if an error occurs.
     */
    public static List<Pizza> readPizzasFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // The cast is unchecked, ensure your file contains the correct data
            @SuppressWarnings("unchecked")
            List<Pizza> pizzaList = (List<Pizza>) ois.readObject();
            return pizzaList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading the pizza list from the file.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes a list of Order objects to a file using serialization.
     *
     * @param orderList the list of Order objects to be written to the file.
     * @param filename the name of the file to which the order list will be written.
     */
    public static void writeOrderToFile(List<Order> orderList, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(orderList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the pizza list to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads a list of Order objects from a file using serialization.
     *
     * @param filename the name of the file from which to read the order list.
     * @return the list of Order objects read from the file, or null if an error occurs.
     */
    public static List<Order> readOrdersFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // The cast is unchecked, ensure your file contains the correct data
            @SuppressWarnings("unchecked")
            List<Order> orderList = (List<Order>) ois.readObject();
            return orderList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading the pizza list from the file.");
            e.printStackTrace();
            return null;
        }
    }
}