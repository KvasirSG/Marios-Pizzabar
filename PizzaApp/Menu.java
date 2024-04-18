package PizzaApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu in the Pizza application, managing a list of pizzas and providing functionality
 * to add, remove, and retrieve pizzas.
 * @author Nikolaj Pirum
 */
public class Menu
{
   private List<Pizza> pizzaList;
   private String pizzaMenuFile = "PizzaApp/Menu.pizza";

   /**
    * Constructs a new Menu instance, initializing the pizza list and loading existing pizzas from a file.
    */
   public Menu()
   {
      this.pizzaList = new ArrayList<Pizza>();
      List<Pizza> tempPizzaList = DataManager.readPizzasFromFile(pizzaMenuFile);
      if (tempPizzaList!=null){
         pizzaList.addAll(tempPizzaList);
      }
   }

   /**
    * Retrieves a Pizza object by its ID.
    *
    * @param pizzaID The ID of the pizza to retrieve.
    * @return The Pizza object with the specified ID, or null if no such pizza exists.
    */
   public Pizza getPizzaByID(int pizzaID) 
   { 
      for (Pizza pizza : pizzaList) 
      {
         if (pizza.getPizzaID() == pizzaID) 
         {
            return pizza;        
         }
      }
      return null;
      }

   /**
    * Adds a pizza to the menu and updates the storage file.
    *
    * @param pizza The Pizza object to be added to the menu.
    */
   public void addPizza(Pizza pizza)
   {
      pizzaList.add(pizza);
      DataManager.writePizzasToFile(pizzaList,pizzaMenuFile);
   }

   /**
    * Removes a pizza from the menu and updates the storage file.
    *
    * @param pizza The Pizza object to be removed from the menu.
    */
   public void removePizza(Pizza pizza)
   {
      pizzaList.remove(pizza);
      DataManager.writePizzasToFile(pizzaList,pizzaMenuFile);
   }

   /**
    * Retrieves the list of all pizzas in the menu.
    *
    * @return The list of Pizza objects.
    */
  public List<Pizza> getPizzaList()
   {  
      return pizzaList;
   }

   /**
    * Computes and returns the next highest pizza ID based on the pizzas currently in the menu.
    * This can be used to assign a unique ID to a new pizza.
    *
    * @return The next highest pizza ID.
    */
   public int getNextHighestID(){
      int highestID = 0;
      for (Pizza pizza:pizzaList){
         if (pizza.getPizzaID() > highestID){
            highestID = pizza.getPizzaID();
         }
      }
      return highestID+1;
   }

}