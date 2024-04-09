// Class for creating orders, apply timestamp and ID
// Accesses List<Pizza> for menu items on the order
// by Duofour
import java.time.*; 
import java.util.*;

public class Order{
   
   private static int nextID; 
   private int orderID; 
   private List<Pizza> pizzas; 
   private LocalDateTime orderTime; 
   
   // Constructor for the Order object, provides ID for the order as well as initializing the ArrayList "Pizzas"
   // Furthermore applies orderTime to the order for documentation
   
   public Order() {
      this.orderID = generateIDFromTimestamp();
      this.pizzas = new ArrayList<>();
      this.orderTime = LocalDateTime.now();
   }
   //End of Order Constructor
   
   // ID from timestamp method, to allow individual ID of the order instead of using Hashmap
   private int generateIDFromTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() % 100; // Get last two digits of the year
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        // Concatenate and convert the first 6 digits to an integer
        String idString = String.format("%02d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);
        return Integer.parseInt(idString);
   
   //Getter method for OrderTime, returns orderTime value
   public LocalDateTime getOrderTime() {
      return orderTime;
   }
   // End of getOrderTime 
   
   // Getter method for OrderID
   public int getOrderID(){
      return orderID; 
   }
   // End of Getter method for OrderID
   
   // Pizza adding method, allows the addition of pizzas to orders. 
   public addPizza(Pizza pizza){
      pizzas.add(Pizza); 
   }
   // Method for returning the pizzas on the order
   public List<Pizza> getPizzas() {
        return pizzas;
    }
   
}