package PizzaUI;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog window for managing the pizza menu. Allows the user to add new pizzas
 * to the menu and remove existing ones by providing pizza details and pizza ID respectively.
 */
public class ManageMenuDialog extends JDialog {
    private JTextField nameField = new JTextField(10);
    private JTextField ingredientField = new JTextField(10);
    private JSpinner priceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.5));
    private JTextField removeIdField = new JTextField(10);
    private JButton addButton = new JButton("Tilføj Pizza");
    private JButton removeButton = new JButton("Fjern Pizza");
    private UiController uiController; // Reference to the controller for performing menu operations.
    private MiddlePanel middlePanel; // Reference to the MiddlePanel for UI updates.

    /**
     * Constructs a ManageMenuDialog.
     *
     * @param parent The parent frame to which this dialog is attached.
     * @param controller The UiController for handling actions.
     * @param middlePanel The MiddlePanel for updating the UI based on actions performed.
     */
    public ManageMenuDialog(JFrame parent, UiController controller, MiddlePanel middlePanel) {
        super(parent, "Administrer menu", true);
        this.middlePanel = middlePanel;
        this.uiController = controller;
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        // Adding components for adding a pizza
        add(new JLabel("Navn:"));
        add(nameField);
        add(new JLabel("ingredienser:"));
        add(ingredientField);
        add(new JLabel("Pris:"));
        add(priceSpinner);
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(addButton);
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(new JPanel()); // Add an empty JPanel as a placeholder
        // Component for removing a pizza by ID
        add(new JLabel("Pizza ID til at Fjerne Pizza:"));
        add(removeIdField);
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(removeButton);

        attachEventHandlers();
    }

    /**
     * Attaches event handlers to the add and remove buttons.
     */
    private void attachEventHandlers() {
        // Handler for adding a pizza
        addButton.addActionListener(e -> {
            // Retrieve pizza details from fields and spinner
            String name = nameField.getText();
            String ingredients = ingredientField.getText();
            double price = (double) priceSpinner.getValue();
            // Invoke controller to add pizza and update the UI
            uiController.addPizzaToMenu(name, ingredients, price);
            middlePanel.updateMenu();
            JOptionPane.showMessageDialog(this, "Pizza tilføjet til menuen!");
            this.dispose(); // Close the dialog after action
        });

        // Handler for removing a pizza
        removeButton.addActionListener(e -> {
            try {
                int pizzaID = Integer.parseInt(removeIdField.getText());
                // Invoke controller to remove pizza and update the UI
                uiController.removePizzaFromMenu(pizzaID);
                middlePanel.updateMenu();
                JOptionPane.showMessageDialog(this, "Pizza Fjernet fra menuen!");
                this.dispose(); // Close the dialog after action
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Pizza ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

