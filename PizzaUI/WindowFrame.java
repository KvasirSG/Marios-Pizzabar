package PizzaUI;

import javax.swing.*;
import PizzaApp.UiController;

import java.awt.*;

public class WindowFrame extends JFrame {
    private LeftPanel leftPanel;
    private MiddlePanel middlePanel;
    private RightPanel rightPanel;
    private UiController uiController; // controller that manages the application logic

    public WindowFrame(UiController uiController){
        this.uiController = uiController;
        setTitle("Mario's PizzaBar");
        initializeComponents();
        layoutComponents();
        attachEventHandlers();
    }
    private void initializeComponents() {
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel(uiController);
        middlePanel = new MiddlePanel(uiController, rightPanel);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        setSize(800, 600); // Set the initial size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
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
    private void showManageMenuDialog() {
        ManageMenuDialog dialog = new ManageMenuDialog(this,uiController, middlePanel);
        // Add event listeners to dialog buttons for adding/removing pizzas
        dialog.setVisible(true);
    }
    private void showManageOrderDialog(){
        ManageOrderDialog dialog = new ManageOrderDialog(this,uiController,rightPanel);
        dialog.setVisible(true);
    }
}
