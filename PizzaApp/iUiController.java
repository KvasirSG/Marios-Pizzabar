package PizzaApp;

import java.util.List;

/**
 * The iUiController interface defines the operations that can be performed by the UI controller
 * in the Pizza application. It includes methods for managing pizzas and orders in the menu and list.
 * @author Kenneth Heimann
 */
public interface iUiController {

    /**
     * Adds a new pizza to the menu.
     *
     * @param name The name of the pizza.
     * @param ingredient The main ingredient of the pizza.
     * @param price The price of the pizza.
     */
    public void addPizzaToMenu(String name, String ingredient, double price);

    /**
     * Removes a pizza from the menu by its ID.
     *
     * @param pizzaID The ID of the pizza to remove.
     */
    public void removePizzaFromMenu(int pizzaID);

    /**
     * Adds a pizza to the current list by its ID.
     *
     * @param pizzaID The ID of the pizza to add.
     */
    public void addPizzaToList(int pizzaID);

    /**
     * Removes a pizza from the current list by its ID.
     *
     * @param pizzaID The ID of the pizza to remove.
     */
    public void removePizzaFromList(int pizzaID);

    /**
     * Clears the current list of pizzas.
     */
    public void clearPizzaList();

    /**
     * Retrieves the current list of pizzas.
     *
     * @return The list of pizzas.
     */
    List<Pizza> getPizzaList();

    /**
     * Retrieves a pizza from the menu by its ID.
     *
     * @param pizzaID The ID of the pizza to retrieve.
     * @return The requested pizza.
     */
    public Pizza getPizzaFromMenu(int pizzaID);

    /**
     * Retrieves the entire menu of pizzas.
     *
     * @return The list of all pizzas in the menu.
     */
    public List<Pizza> getPizzaMenu();

    /**
     * Adds a pizza order with a specified completion time.
     *
     * @param completionTime The time it takes to complete the order.
     */
    public void addPizzaOrder(int completionTime);

    /**
     * Removes a pizza order by its ID.
     *
     * @param orderID The ID of the order to remove.
     */
    public void removePizzaOrder(long orderID);

    /**
     * Marks an order as completed by its ID.
     *
     * @param orderID The ID of the order to complete.
     */
    public void completeOrder(long orderID);

    /**
     * Retrieves a list of all orders.
     *
     * @return The list of all orders.
     */
    public List<Order> getAllOrders();

    /**
     * Retrieves a list of all completed orders.
     *
     * @return The list of completed orders.
     */
    public List<Order> getAllCompletedOrders();

    /**
     * Retrieves the sums of all orders.
     *
     * @return A list of the sums of each order.
     */
    public List<Double> getOrderSums();
}
