import java.util.ArrayList;
import java.util.List;

/**
 * The UiController class implements the iUiController interface,
 * managing the UI's interactions with the pizza menu and the list of selected pizzas.
 * This class is responsible for handling actions such as adding or removing pizzas from the menu,
 * and managing the list of pizzas selected by the user.
 */
public class UiController implements iUiController{
    private List<Pizza> selectedPizzaList;

    /**
     * Constructs a new UiController instance, initializing the list of selected pizzas.
     */
    public UiController(){
        selectedPizzaList = new ArrayList<>();
    }

    /**
     * Adds a pizza to the menu.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @param name The name of the pizza to add.
     * @param ingredient The ingredients of the pizza.
     * @param price The price of the pizza.
     */
    @Override
    public void addPizzaToMenu(String name, String ingredient, double price) 
    {
      menu.addPizza(new pizza(name, ingredient, price));       
    }

    /**
     * Removes a pizza from the menu by its ID.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @param pizzaID The ID of the pizza to remove.
     */
    @Override
    public void removePizzaFromMenu(int pizzaID) 
    {
      menu.removePizza(menu.getPizzaByID(pizzaID);
    }

    /**
     * Adds a pizza to the list of selected pizzas by its ID.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @param pizzaID The ID of the pizza to add to the list.
     */
    @Override
    public void addPizzaToList(int pizzaID) {
        //TODO when menu class is made
    }

    /**
     * Removes a pizza from the list of selected pizzas by its ID.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @param pizzaID The ID of the pizza to remove from the list.
     */
    @Override
    public void removePizzaFromList(int pizzaID) {
        //TODO when menu class is made
    }

    /**
     * Clears the list of selected pizzas.
     */
    @Override
    public void clearPizzaList() {
        selectedPizzaList.clear();
    }

    /**
     * Retrieves a pizza from the menu by its ID.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @param PizzaID The ID of the pizza to retrieve.
     * @return The Pizza object if found, null otherwise.
     */
    @Override
    public Pizza getPizzaFromMenu(int PizzaID) {
        //TODO when menu class is made
        return null;
    }

    /**
     * Retrieves the current pizza menu.
     * Note: This method is a placeholder and needs to be implemented when the Menu class is created.
     *
     * @return A list of Pizza objects representing the current menu, null if not available.
     */
    @Override
    public List<Pizza> getPizzaMenu() {
        //TODO when menu class is made
        return null;
    }

    /**
     * Adds an order for the selected pizzas.
     * Note: This method is a placeholder and needs to be implemented when the Order class is created.
     */
    @Override
    public void addPizzaOrder() 
    {
        //TODO when Order class is made
    }

    /**
     * Removes an order by its ID.
     * Note: This method is a placeholder and needs to be implemented when the Order class is created.
     *
     * @param OrderID The ID of the order to remove.
     */
    @Override
    public void removePizzaOrder(int OrderID) {
        //TODO when Order class is made
    }
}