package PizzaApp;

import java.util.ArrayList;
import java.util.List;

/**
 * The UiController class implements the iUiController interface,
 * managing the UI's interactions with the pizza menu and the list of selected pizzas.
 * This class is responsible for handling actions such as adding or removing pizzas from the menu,
 * and managing the list of pizzas selected by the user.
 *
 * @author Kenneh Heimann, Sebastian Dufour
 */
public class UiController implements iUiController{
    private List<Pizza> selectedPizzaList;
    private Menu menu;
    private OrderManager orderManager;

    /**
     * Constructs a new UiController instance, initializing the list of selected pizzas, menu and orderManager
     */
    public UiController(){
        selectedPizzaList = new ArrayList<>();
        menu = new Menu();
        orderManager = new OrderManager();

    }

    /**
     * Adds a pizza to the menu.
     *
     * @param name The name of the pizza to add.
     * @param ingredient The ingredients of the pizza.
     * @param price The price of the pizza.
     */
    @Override
    public void addPizzaToMenu(String name, String ingredient, double price) 
    {
      menu.addPizza(new Pizza(menu.getNextHighestID(), name, ingredient, price));
    }

    /**
     * Removes a pizza from the menu by its ID.
     *
     * @param pizzaID The ID of the pizza to remove.
     */
    @Override
    public void removePizzaFromMenu(int pizzaID) 
    {
      menu.removePizza(menu.getPizzaByID(pizzaID));
    }

    /**
     * Adds a pizza to the list of selected pizzas by its ID.
     *
     * @param pizzaID The ID of the pizza to add to the list.
     */
    @Override
    public void addPizzaToList(int pizzaID) {
        selectedPizzaList.add(menu.getPizzaByID(pizzaID));
    }

    /**
     * Removes a pizza from the list of selected pizzas by its ID.
     *
     * @param pizzaID The ID of the pizza to remove from the list.
     */
    @Override
    public void removePizzaFromList(int pizzaID) {
        selectedPizzaList.remove(menu.getPizzaByID(pizzaID));
    }

    /**
     * Clears the list of selected pizzas.
     */
    @Override
    public void clearPizzaList() {
        selectedPizzaList.clear();
    }

    @Override
    public List<Pizza> getPizzaList() {
        return selectedPizzaList;
    }

    /**
     * Retrieves a pizza from the menu by its ID.
     *
     * @param pizzaID The ID of the pizza to retrieve.
     * @return The Pizza object if found, null otherwise.
     */
    @Override
    public Pizza getPizzaFromMenu(int pizzaID) {
        return menu.getPizzaByID(pizzaID);
    }

    /**
     * Retrieves the current pizza menu.
     *
     * @return A list of Pizza objects representing the current menu, null if not available.
     */
    @Override
    public List<Pizza> getPizzaMenu() {
        return menu.getPizzaList();
    }

    /**
     * Adds an order for the selected pizzas.
     */
    @Override
    public void addPizzaOrder(int completionTime) 
    {
        Order order = new Order();
        for (Pizza pizza: selectedPizzaList){
            order.addPizza(pizza);
        }
        orderManager.addOrder(order, completionTime);
        clearPizzaList();
    }
    
        
    /**
     * Retrieves the sum of prices of all pizzas in each active order.
     *
     * @return A list containing the total price of each active order.
     */
    @Override
    public List<Double> getOrderSums() {
        List<Double> orderSums = new ArrayList<>();
        List<Order> activeOrders = orderManager.getOrders();

        for (Order order : activeOrders) {
            orderSums.add(order.getSumPrice());
        }

        return orderSums;
    }

    /**
     * Removes an order by its ID.
     *
     * @param orderID The ID of the order to remove.
     */
    @Override
    public void removePizzaOrder(long orderID) {
        orderManager.removeOrder(orderID);
    }

    /**
     * method to complete an order.
     * @param orderID
     */
    @Override
    public void completeOrder(long orderID) {
        orderManager.completeOrder(orderID);
    }

    /**
     * retrieves all active orders
     * @return orders
     */
    @Override
    public List<Order> getAllOrders() {
        return orderManager.getOrders();
    }

    /**
     * retrieves all completed orders.
     * @return completedOrders
     */
    @Override
    public List<Order> getAllCompletedOrders() {
        return orderManager.getCompletedOrders();
    }
}