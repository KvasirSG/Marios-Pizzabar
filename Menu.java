import java.util.ArrayList;
import java.util.List;

public class Menu
{
   private List<Pizza> pizzaList;
   String pizzaMenuFile = "pizzaMenu.txt";

   // Menu contructor 
   public Menu() 
   {
      this.pizzaList = new ArrayList<Pizza>();
      this.pizzaList = DataManager.readPizzasFromFile(pizzaMenuFile);
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

}