package PizzaUI;

import javax.swing.*;
import PizzaApp.UiController;

import java.awt.*;

/**
 * The main window frame of the Pizza application, organizing the UI into left, middle, and right panels,
 * and handling interactions between these components and the application logic.
 * @author Kenneth Heimann
 */
public class WindowFrame extends JFrame {
    private LeftPanel leftPanel;
    private MiddlePanel middlePanel;
    private RightPanel rightPanel;
    private UiController uiController; // controller that manages the application logic

    /**
     * Constructs the WindowFrame with a reference to the UiController.
     *
     * @param uiController The controller managing the application logic.
     */
    public WindowFrame(UiController uiController){
        this.uiController = uiController;
        setTitle("Mario's PizzaBar");
        initializeComponents();
        layoutComponents();
        attachEventHandlers();
    }

    /**
     * Initializes the components of the window frame, including the left, middle,
     * and right panels.
     */
    private void initializeComponents() {
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel(uiController);
        middlePanel = new MiddlePanel(uiController, rightPanel);
    }

    /**
     * Sets up the layout of the window frame, arranging the left, middle, and right panels.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        setSize(1500, 600); // Set the initial size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Attaches event handlers to the components for handling user actions, such as button clicks.
     */
    private void attachEventHandlers() {
        // Example: Switch to the menu view when the "View Menu" button is clicked
        leftPanel.getViewMenuButton().addActionListener(e -> {
            middlePanel.updateMenu();
            middlePanel.showCard("Menu");
        });
        leftPanel.getViewActiveOrdersButton().addActionListener(e -> {
            middlePanel.updateAOrders();
            middlePanel.showCard("Active Orders");
        });
        leftPanel.getViewCompletedOrdersButton().addActionListener(e -> {
            middlePanel.updateCOrders();
            middlePanel.showCard("Completed Orders");
        });
        leftPanel.getManageMenuButton().addActionListener(e -> showManageMenuDialog());

        // Example handler for adding an order
        rightPanel.getAddOrderButton().addActionListener(e -> showManageOrderDialog());

        // Other event handlers as needed
    }

    /**
     * Displays the dialog for managing the menu, allowing the addition or removal of pizzas.
     */
    private void showManageMenuDialog() {
        ManageMenuDialog dialog = new ManageMenuDialog(this,uiController, middlePanel);
        // Add event listeners to dialog buttons for adding/removing pizzas
        dialog.setVisible(true);
    }

    /**
     * Displays the dialog for managing orders, allowing the addition of new orders.
     */
    private void showManageOrderDialog(){
        ManageOrderDialog dialog = new ManageOrderDialog(this,uiController,rightPanel);
        dialog.setVisible(true);
    }
}
