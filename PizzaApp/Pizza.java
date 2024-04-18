package PizzaApp;

import java.io.Serializable;

/**
 * Represents a pizza in the Pizza application, including details such as ID, name, ingredient, and price.
 * @author Nikolaj Pirum
 */
public class Pizza implements Serializable
{
   private static final long serialVersionUID = 1L;
   private int pizzaID;
   private String name;
   private String ingredient;
   private double price;

   /**
    * Constructs a new Pizza object with the specified ID, name, ingredient, and price.
    *
    * @param pizzaID The unique identifier for the pizza.
    * @param name The name of the pizza.
    * @param ingredient The main ingredient of the pizza.
    * @param price The price of the pizza.
    */
   public Pizza(int pizzaID, String name, String ingredient, double price) {
      this.pizzaID = pizzaID;
      this.name = name;
      this.ingredient = ingredient;
      this.price = price;
   }

   /**
    * Gets the pizza's ID.
    *
    * @return The pizza's unique identifier.
    */
   public int getPizzaID() 
   {
      return pizzaID;
   }

   /**
    * Gets the name of the pizza.
    *
    * @return The name of the pizza.
    */
   public String getName() 
   {
      return name;
   }

   /**
    * Sets the name of the pizza.
    *
    * @param name The new name for the pizza.
    */
   public void setName(String name) 
   {
      this.name = name;
   }

   /**
    * Gets the main ingredient of the pizza.
    *
    * @return The pizza's main ingredient.
    */
   public String getIngredient() 
   {
      return ingredient; 
   }

   /**
    * Sets the main ingredient of the pizza.
    *
    * @param ingredient The new main ingredient for the pizza.
    */
   public void setIngredient(String ingredient) 
   {
      this.ingredient = ingredient;
   }

   /**
    * Gets the price of the pizza.
    *
    * @return The price of the pizza.
    */
   public double getPrice() 
   {
      return price;
   }

   /**
    * Sets the price of the pizza.
    *
    * @param price The new price for the pizza.
    */
   public void setPrice(double price) 
   {
      this.price = price;
   }

   /**
    * Returns a string representation of the pizza, formatted as an array containing the ID, name, ingredient, and price.
    *
    * @return A string representation of the pizza.
    */
   @Override
   public String toString() {
      // Mimics an array format: [pizzaID, name, ingredient, price]
      return String.format("[%d, %s, %s, %.2f]", pizzaID, name, ingredient, price);
   }
}