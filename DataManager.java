import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles writing and reading files for our pizza system
 * @author Kenneth Heimann, Sebastian Duofour
 */
public class DataManager{

    /**
     * Writes a list of Pizza objects to a specified file in CSV format.
     * Each Pizza object's attributes are written to a single line in the file,
     * with attributes separated by commas.
     * <p>
     * If the specified file does not exist, it will be created.
     * If the file already exists, its contents will be overwritten.
     * </p>
     *
     * @param pizzaList A List of Pizza objects to be written to the file.
     * @param filename  The name of the file to which the pizza menu should be written.
     */
    public static void writeMenuToFile(List<Pizza> pizzaList, String filename) {
        // Create a File object for the specified filename.
        File file = new File(filename);
        try {
            // Check if the file exists, and create a new one if it does not.
            if (!file.exists()){
                file.createNewFile(); // create a new file if it does not exist.
            }
            // Use PrintWriter to write text to the file.
            try (PrintWriter printWriter= new PrintWriter(new FileWriter(file,false))){
                // Iterate through each Pizza object in the list.
                for (Pizza pizza:pizzaList){
                    // Write each Pizza object's attributes to the file in CSV format.
                    printWriter.println(pizza.getPizzaID()+";"+pizza.getName()+";"+pizza.getIngredient()+";"+pizza.getPrice());
                }
            }
        } catch (IOException e) {
            // Handle possible IOExceptions from file creation or writing.
            System.out.println("An error occurred while writing to or creating the file: " + e.getMessage());
        }
    }

    /**
     * Reads a list of Pizza objects from a specified file.
     * <p>
     * The method expects the file to be in CSV format, where each line contains a Pizza object's
     * attributes separated by commas, in the order of Pizza ID, name, ingredient, and price.
     * </p>
     * <p>
     * If the file does not exist or cannot be found, an error message is printed, and an empty list
     * is returned.
     * </p>
     *
     * @param filename The name of the file from which to read the pizza menu.
     * @return A List of Pizza objects read from the file. Returns an empty list if the file does not exist
     * or cannot be read.
     */
    public static List<Pizza> readMenuFromFile(String filename) {
        // Initialize an empty list to store Pizza objects.
        List<Pizza> pizzaList = new ArrayList<>();
        // Create a Scanner to read the file.
        try (Scanner scanner = new Scanner(new File(filename))){
            // Loop through each line in the file.
            while (scanner.hasNextLine()){
                // Split each line into parts using commas as the delimiter.
                String[] data = scanner.nextLine().split(";");
                // Create a new Pizza object from the split data.
                Pizza pizza = new Pizza(Integer.parseInt(data[0]),data[1],data[2],Double.parseDouble(data[3]));
                // Add the new Pizza object to the list.
                pizzaList.add(pizza);
            }
        } catch (FileNotFoundException e){
            // Print an error message if the file cannot be found.
            System.out.println("File not found: "+e.getMessage());
        }
        // Return the list of Pizza objects.
        return pizzaList;
    }

    public static void writeOrderToFile(List<Order> orderList, String filename) {
        // Create a File object for the specified filename.
        File file = new File(filename);
        try {
            // Check if the file exists, and create a new one if it does not.
            if (!file.exists()){
                file.createNewFile(); // create a new file if it does not exist.
            }
            // Use PrintWriter to write text to the file.
            try (PrintWriter printWriter= new PrintWriter(new FileWriter(file,false))){
                // Iterate through each Order object in the list.
                for (Order order:orderList){
                    // Write each Order object's attributes to the file in CSV format.
                    printWriter.println(order.getOrderID()+";"+order.getPizzas()+";"+order.getOrderTime());
                }
            }
        } catch (IOException e) {
            // Handle possible IOExceptions from file creation or writing.
            System.out.println("An error occurred while writing to or creating the file: " + e.getMessage());
        }
    }

    public static List<Order> readOrderFromFile() {
        return null;
    }

    /**
     * Checks if a file is empty. A file is considered empty if it does not exist,
     * cannot be read, or contains no content.
     *
     * @param filename the name of the file to check
     * @return true if the file is empty or does not exist, false otherwise
     */
    private boolean isFileEmpty(String filename){
        File file = new File(filename);
        // Check if the file exists and is not a directory
        if (!file.exists()|| file.isDirectory()){
            return true;
        }
        try (Scanner scanner = new Scanner(file)){
            // if the file has a next line, it has content
            return !scanner.hasNextLine();
        } catch (FileNotFoundException e){
            // file not found considering as empty
            System.out.println("File not found: "+ e.getMessage());
            return true;
        }
    }
}
