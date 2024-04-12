import java.util.*;

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
        options.add("View current orders");
        options.add("Add Order");
        options.add("Exit");
        int index = 1;
        for (String option:options){
            System.out.println(index+". "+option);
            index++;
        }
        System.out.print("Enter your choice: ");
    }

    private void displayAddPizzaToMenu(){
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

    private void displayRemovePizzaFromMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id of the pizza you want to remove: ");
        int pizzaID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        uiController.removePizzaFromMenu(pizzaID);
        displayMenu();
    }

    private void displayOrders(){
        List<Order> orders = uiController.getAllOrders();
        orders.sort((Comparator.comparingInt(Order::getPriority)));
        for (Order order:orders){
            System.out.println(order.getPriority()+". "+order.getOrderID()+" - "+order.getPizzas()+" - "+order.getOrderTime());
        }
    }

    private void displaySelectedPizzaList(){
        List<Pizza> pizzaList = uiController.getPizzaList();
        System.out.println(" ");
        System.out.println("Selected Pizzas:");
        for (Pizza pizza : pizzaList) {
            System.out.println(pizza.getPizzaID()+". "+pizza.getName()+": "+pizza.getIngredient()+"..."+ pizza.getPrice()+",-");
        }
    }

    private void displayAddOrder(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Making new order:");
        while (true){
            System.out.print("Add Pizza to order (type 0 to finish): ");
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (input == 0){
                break;
            } else {
                uiController.addPizzaToList(input);
                displaySelectedPizzaList();
            }
        }
        if (!uiController.getPizzaList().isEmpty()) {
            uiController.addPizzaOrder();
            displayOrders();
        }

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
                    displayAddPizzaToMenu();
                    break;
                case 3:
                    displayRemovePizzaFromMenu();
                    break;
                case 4:
                    displayOrders();
                    break;
                case 5:
                    displayAddOrder();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }


}
