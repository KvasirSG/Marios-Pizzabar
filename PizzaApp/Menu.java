package PizzaApp;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
   private List<Pizza> pizzaList;
   String pizzaMenuFile = "PizzaApp/Menu.pizza";

   // Menu contructor 
   public Menu()
   {
      this.pizzaList = new ArrayList<Pizza>();
      List<Pizza> tempPizzaList = DataManager.readPizzasFromFile(pizzaMenuFile);
      if (tempPizzaList!=null){
         pizzaList.addAll(tempPizzaList);
      }
   }

   //method to call pizza with pizzaID
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
      
   // method for adding pizza 
   public void addPizza(Pizza pizza)
   {
      pizzaList.add(pizza);
      DataManager.writePizzasToFile(pizzaList,pizzaMenuFile);
   }
   // method for removing pizza
   public void removePizza(Pizza pizza)
   {
      pizzaList.remove(pizza);
      DataManager.writePizzasToFile(pizzaList,pizzaMenuFile);
   }
   
  //getter method for pizzaList
  public List<Pizza> getPizzaList()
   {  
      return pizzaList;
   }

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