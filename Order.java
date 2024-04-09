// Class for creating orders, apply timestamp and ID
// Accesses List<Pizza> for menu items on the order
// by Duofour
import java.time.*; 
import java.util.*;

public class Order{
   
   private static int nextID; 
   private long orderID;
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
   private long generateIDFromTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() % 100; // Get last two digits of the year
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        // Concatenates and converts the first 6 digits to an integer to be used for ID of the individual orderss
        String idString = String.format("%02d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);
        return Long.parseLong(idString);
   }
   //Getter method for OrderTime, returns orderTime value
   public LocalDateTime getOrderTime() {
      return orderTime;
   }
   // End of getOrderTime 
   
   // Getter method for OrderID
   public long getOrderID(){
      return orderID; 
   }
   // End of Getter method for OrderID
   
   // Pizza adding method, allows the addition of pizzas to orders. 
   public void  addPizza(Pizza pizza){
      pizzas.add(pizza); 
   }
   // Method for returning the pizzas on the order
   public List<Pizza> getPizzas() {
        return pizzas;
    }
   
}