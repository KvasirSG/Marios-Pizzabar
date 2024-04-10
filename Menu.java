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
   }

   public Pizza getPizzaByID(int pizzaID) 
   { 
      for (Pizza pizza : pizzaList) 
      {
         if (pizza.getPizzaID() == pizzaID) 
         {
            System.out.println(pizza);
            return pizza;
            
         }
      }
      return null;
      }
   // method for adding pizza 
   public void addPizza(Pizza pizza)
   {
      pizzaList.add(pizza);
   }
   // method for removing pizza
   public void removePizza(Pizza pizza)
   {
         pizzaList.remove(pizza);  
   }
   
  public List<Pizza> getPizzaList()
   {
      System.out.println(pizzaList);  
      return pizzaList;
   }

}