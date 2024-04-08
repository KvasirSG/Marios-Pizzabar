// Class for creating orders, apply timestamp and ID
// Accesses List<Pizza> for menu items on the order
// by Duofour
import java.time.*; 
import java.util.*;

public class Order{
   
   private static int nextID = 1; 
   private int orderID; 
   private List<Pizza> pizzas; 
   private LocalDateTime orderTime; 
   
   // Constructor for the Order object, provides ID for the order as well as initializing the ArrayList "Pizzas"
   // Furthermore applies orderTime to the order for documentation
   
   public Order() {
      this.orderID = nextID++;
      this.pizzas = ArrayList<>();
      this.orderTime = LocalDateTime.now(); 
   }
   //End of Order Constructor
   
   //Getter method for OrderTime, returns orderTime value
   LocalDateTime getOrderTime(){
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
   
}