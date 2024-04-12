import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI for the pizza ordering system
 */
public class UiTerminal {
    UiController uiController;
    public UiTerminal(UiController uiController){
        this.uiController = uiController;
        uInputHandler();
    }
    private void displayMenu() {
        List<Pizza> pizzaList = uiController.getPizzaMenu();
        System.out.println(" ");
        System.out.println("Menu:");
        for (Pizza pizza : pizzaList) {
            System.out.println(pizza.getPizzaID()+". "+pizza.getName()+": "+pizza.getIngredient()+"..."+ pizza.getPrice()+",-");
        }
    }

    // Method to display options in the main menu
    private void displayOptions() {
        List<String> options = new ArrayList<>();
        System.out.println("\nOptions:");
        options.add("Display Menu");
        options.add("Add Pizza to Menu");
        options.add("Remove Pizza from Menu");
        options.add("Exit");
        int index = 1;
        for (String option:options){
            System.out.println(index+". "+option);
            index++;
        }
        System.out.print("Enter your choice: ");
    }

    private void displayAddPizzToMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a pizza to menu by name, ingredients, price");
        System.out.print("Enter name of the Pizza: ");
        String pizzaName = scanner.nextLine();
        System.out.print("Enter the ingredients of the Pizza: ");
        String pizzaIngredients = scanner.nextLine();
        System.out.print("Enter the price: ");
        double pizzaPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        uiController.addPizzaToMenu(pizzaName,pizzaIngredients,pizzaPrice);
        displayMenu();

    }

    private void uInputHandler(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayOptions();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    displayAddPizzToMenu();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }


}
