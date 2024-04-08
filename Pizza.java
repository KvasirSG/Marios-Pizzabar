public class Pizza 
{
   private int pizzaID;
   private String name;
   private String ingredient;
   private double price; 
   
   public Pizza(int pizzaID, String name, String ingredient, double price)
   {
      this.pizzaID = pizzaID;
      this.name = name;
      this.ingredient = ingredient; 
      this.price = price;
   }
  
   public int getPizzaID()
   {
      return pizzaID;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public String getIngredient()
   {
      return ingredient; 
   }
   
   public void setIngredient(String ingredient)
   {
      this.ingredient = ingredient;
   }
   
   public double getPrice()
   {
      return price;
   }
   
   public void setPrice(double price)
   {
      this.price = price;
   }
}