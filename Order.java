// Class for creating orders, apply timestamp and ID
// Accesses List<Pizza> for menu items on the order
// by Duofour
import java.io.Serializable;
import java.time.*;
import java.util.*;

public class Order implements Serializable {
   private static final long serialVersionUID = 2L;
   private static int nextID; 
   private long orderID;
   private List<Pizza> pizzas; 
   private LocalDateTime orderTime; 
   private int priority;
   private LocalDateTime completionTime;
   
   // Constructor for the Order object, provides ID for the order as well as initializing the ArrayList "Pizzas"
   // Furthermore applies orderTime to the order for documentation
   
   public Order() {
      this.orderID = generateIDFromTimestamp();
      this.pizzas = new ArrayList<>();
      this.orderTime = LocalDateTime.now();
   }
   //End of Order Constructor
   //Overloaded constructor, intended for DataManager class to add
   public Order(Long orderID, List<Pizza> pizzaList, LocalDateTime orderTime){
       this.orderID = orderID;
        this.pizzas = pizzaList;
        this.orderTime = orderTime;
   }
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
    
    // Setter method allowing priority to be set in the OrderManager class
    public void setPriority(int priority) {
        this.priority = priority;
   }
   
   // Getter method for the OrderManager class
   public int getPriority() {
        return priority;
   }
   public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

   public LocalDateTime getCompletionTime() {
        return completionTime;
    }
   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderID: ").append(orderID).append(", ");
        sb.append("OrderTime: ").append(orderTime).append(", ");
        sb.append("Priority: ").append(priority).append(", ");
        sb.append("Pizzas: [");

        for (int i = 0; i < pizzas.size(); i++) {
            sb.append(pizzas.get(i).toString());
            if (i < pizzas.size() - 1) {
                sb.append(", "); // Add comma except after the last pizza
            }
        }

        sb.append("]");
        return sb.toString();
    }
    // Method to get the sum of price for pizzas on the order
    public double getSumPrice() {
      double sum = 0.0;
        for (Pizza pizza : pizzas) {
            sum += pizza.getPrice();
        }
        return sum;
    }
    
}
   
