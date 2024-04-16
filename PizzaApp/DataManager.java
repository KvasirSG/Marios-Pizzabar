package PizzaApp;

import java.io.*;
import java.util.List;

public class DataManager{
    public static void writePizzasToFile(List<Pizza> pizzaList, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pizzaList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the pizza list to the file.");
            e.printStackTrace();
        }
    }

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

    public static void writeOrderToFile(List<Order> orderList, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(orderList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the pizza list to the file.");
            e.printStackTrace();
        }
    }

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