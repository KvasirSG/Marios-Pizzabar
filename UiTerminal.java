import java.util.List;

/**
 * Console UI for the pizza ordering system
 */
public class UiTerminal {
    UiController uiController;
    public UiTerminal(UiController uiController){
        this.uiController = uiController;
    }
    public void displayMenu() {
        List<Pizza> pizzaList = uiController.getPizzaMenu();
        System.out.println(" ");
        System.out.println("Menu:");
        for (Pizza pizza : pizzaList) {
            System.out.println(pizza);
        }
    }
}
