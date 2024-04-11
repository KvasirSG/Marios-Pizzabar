import java.util.List;

public interface iUiController {
    public void addPizzaToMenu(String name, String ingredient, double price); 
    public void removePizzaFromMenu(int pizzaID);
    public void addPizzaToList(int pizzaID);
    public void removePizzaFromList(int pizzaID);
    public void clearPizzaList();
    public Pizza getPizzaFromMenu(int PizzaID);
    public List<Pizza> getPizzaMenu();
    public void addPizzaOrder();
    public void removePizzaOrder(int OrderID);
    //TODO public List<Order> getAllOrders();
}
