import java.util.List;

public interface iUiController {
    public void addPizzaToMenu(String name, String ingredient, double price); 
    public void removePizzaFromMenu(int pizzaID);
    public void addPizzaToList(int pizzaID);
    public void removePizzaFromList(int pizzaID);
    public void clearPizzaList();
    List<Pizza> getPizzaList();
    public Pizza getPizzaFromMenu(int PizzaID);
    public List<Pizza> getPizzaMenu();
    public void addPizzaOrder();
    public void removePizzaOrder(int priority);
    public void completeOrder(int priority);
    public List<Order> getAllOrders();
    public List<Order> getAllCompletedOrders();
}
