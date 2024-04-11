import java.io.Serializable;

public class Pizza implements Serializable
{
   private static final long serialVersionUID = 1L;
   private static int lastPizzaID = 0;
   private int pizzaID;
   private String name;
   private String ingredient;
   private double price;

   // Constructor for automatic ID generation
   public Pizza(String name, String ingredient, double price) {
      this.pizzaID = ++lastPizzaID;
      this.name = name;
      this.ingredient = ingredient;
      this.price = price;
   }

   // Overloaded constructor for manual ID setting
   public Pizza(int pizzaID, String name, String ingredient, double price) {
      if (pizzaID > lastPizzaID) {
         lastPizzaID = pizzaID; // Update the lastPizzaID if the manual ID is greater
      }
      this.pizzaID = pizzaID;
      this.name = name;
      this.ingredient = ingredient;
      this.price = price;
   }

   // method for printing pizza number
   public int getPizzaID() 
   {
      return pizzaID;
   }
   // method for printing pizza name
   public String getName() 
   {
      return name;
   }
   //method for setting pizza name 
   public void setName(String name) 
   {
      this.name = name;
   }
   // method for printing pizza ingrediens 
   public String getIngredient() 
   {
      return ingredient; 
   }
   // method setting pizza ingrediens
   public void setIngredient(String ingredient) 
   {
      this.ingredient = ingredient;
   }
   // method for printing pizza price
   public double getPrice() 
   {
      return price;
   }
   // method for setting pizza price
   public void setPrice(double price) 
   {
      this.price = price;
   }

   public String toString() 
   {
      // Mimics an array format: [pizzaID, name, ingredient, price]
      return String.format("[%d, %s, %s, %.2f]", pizzaID, name, ingredient, price);
   }
}