/**
 * Main class representing the pizza ordering system
 * Acting as interface between the logic and the UI
 */
public class PizzaOrderSystem {
    public static void main(String[] args) {
        // making an instance of the UiController
        UiController uiController = new UiController();

        // making an instance of the UiTerminal
        UiTerminal uiTerminal = new UiTerminal(uiController);
    }
}
