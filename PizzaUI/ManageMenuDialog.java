package PizzaUI;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;

public class ManageMenuDialog extends JDialog {
    private JTextField nameField = new JTextField(10);
    private JTextField ingredientField = new JTextField(10);
    private JSpinner priceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.5));
    private JTextField removeIdField = new JTextField(10);
    private JButton addButton = new JButton("Add Pizza");
    private JButton removeButton = new JButton("Remove Pizza");
    private UiController uiController; // Reference to the controller
    private MiddlePanel middlePanel;

    public ManageMenuDialog(JFrame parent, UiController controller, MiddlePanel middlePanel) {
        super(parent, "Manage Menu", true);
        this.middlePanel = middlePanel;
        this.uiController = controller;
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        // Adding components for adding a pizza
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Ingredients:"));
        add(ingredientField);
        add(new JLabel("Price:"));
        add(priceSpinner);
        add(addButton);

        // Component for removing a pizza by ID
        add(new JLabel("Pizza ID to Remove:"));
        add(removeIdField);
        add(removeButton);

        attachEventHandlers();
    }

    private void attachEventHandlers() {
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String ingredients = ingredientField.getText();
            double price = (double) priceSpinner.getValue();
            uiController.addPizzaToMenu(name, ingredients, price);
            JOptionPane.showMessageDialog(this, "Pizza added successfully!");
            middlePanel.updateMenu();
        });

        removeButton.addActionListener(e -> {
            try {
                int pizzaID = Integer.parseInt(removeIdField.getText());
                uiController.removePizzaFromMenu(pizzaID);
                JOptionPane.showMessageDialog(this, "Pizza removed successfully!");
                middlePanel.updateMenu();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Pizza ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
