package PizzaUI;

import PizzaApp.Order;
import PizzaApp.Pizza;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * A custom cell editor for JTable cells that involve button interactions. This editor
 * handles button presses and performs corresponding actions based on the button type,
 * such as adding or removing pizzas from a list or completing and removing orders.
 * @author Kenneth Heimann
 */
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private String label;
    private Pizza pizza; // The Pizza object to act upon
    private Order order; // The Order object to act upon

    private RightPanel rightPanel;
    private MiddlePanel middlePanel;

    private UiController uiController;

    private ButtonType buttonType;

    /**
     * Constructs a ButtonEditor for a table cell, initializing it with a specific
     * button type and setting up action listeners for pizza-related actions.
     *
     * @param checkBox The check box component to be used as the editor.
     * @param uiController The UI controller for managing application logic.
     * @param rightPanel The RightPanel for UI interaction.
     * @param buttonType The type of button this editor will handle.
     */
    public ButtonEditor(JCheckBox checkBox, UiController uiController, RightPanel rightPanel, ButtonType buttonType) {
        super(checkBox);
        this.uiController = uiController;
        this.rightPanel = rightPanel;
        this.buttonType= buttonType;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                isPushed = true;
            }
        });
    }

    /**
     * Constructs a ButtonEditor for a table cell, initializing it with a specific
     * button type and setting up action listeners for order-related actions.
     *
     * @param checkBox The check box component to be used as the editor.
     * @param uiController The UI controller for managing application logic.
     * @param middlePanel The MiddlePanel for UI interaction.
     * @param buttonType The type of button this editor will handle.
     */
    public ButtonEditor(JCheckBox checkBox, UiController uiController,MiddlePanel middlePanel, ButtonType buttonType){
        super(checkBox);
        this.uiController = uiController;
        this.buttonType = buttonType;
        this.middlePanel = middlePanel;
        button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(true);
        if (buttonType == ButtonType.CORDER){
            button.setBackground(Color.green);
        } else if (buttonType == ButtonType.RORDER){
            button.setBackground(Color.red);
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                isPushed = true;
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // Convert the value to a string, or an empty string if the value is null
        label = (value == null) ? "" : value.toString();

        // If the button type is either PIZZA or RPIZZA, get the Pizza object from the model
        if (buttonType == ButtonType.PIZZA || buttonType == ButtonType.RPIZZA){
            // Cast the table model to PizzaTableModel to access specific methods
            PizzaTableModel model = (PizzaTableModel) table.getModel();
            // Retrieve the Pizza object for the given row
            this.pizza = model.getPizzaAt(row);

            // If the button type is either CORDER or RORDER, get the Order object from the model
        } else if (buttonType==ButtonType.CORDER||buttonType==ButtonType.RORDER){
            // Cast the table model to OrderTableModel to access specific methods
            OrderTableModel model = (OrderTableModel) table.getModel();
            // Retrieve the Order object for the given row
            this.order = model.getOrderAt(row);

        }
        // Set the button text to the label determined above
        button.setText(label);
        // Return the button component to be used as the editor
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        // Check if the button was clicked
        if (isPushed) {
            // Action to add a pizza to the list, if the button type is PIZZA
            if (buttonType == ButtonType.PIZZA){
                // Call to add the pizza to a list based on its ID
                uiController.addPizzaToList(pizza.getPizzaID());
                // Update the UI to reflect the added pizza
                rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
                // Show a confirmation message
                JOptionPane.showMessageDialog(button, pizza.getName()+" Tilføjet til order");
            }
            // Action to remove a pizza from the list, if the button type is RPIZZA
            if (buttonType==ButtonType.RPIZZA){
                // Call to remove the pizza from the list based on its ID
                uiController.removePizzaFromList(pizza.getPizzaID());
                // Update the UI to reflect the removed pizza
                rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
                // Show a confirmation message
                JOptionPane.showMessageDialog(button, pizza.getName()+" Fjernet fra order");
            }
            // Action to complete an order, if the button type is CORDER
            if (buttonType == ButtonType.CORDER){
                // Call to mark an order as completed based on its ID
                uiController.completeOrder(order.getOrderID());
                // Update the UI to reflect the change in order status
                middlePanel.updateAOrders();
                // Show a confirmation message
                JOptionPane.showMessageDialog(button, order.getOrderID()+"  Fuldført");
            }
            // Action to remove an order, if the button type is RORDER
            if (buttonType == ButtonType.RORDER){
                // Call to remove an order based on its ID
                uiController.removePizzaOrder(order.getOrderID());
                // Update the UI to reflect the removed order
                middlePanel.updateAOrders();
                // Show a confirmation message
                JOptionPane.showMessageDialog(button, order.getOrderID()+" Fjernet");
            }
        }
        // Reset the isPushed flag after the action is performed
        isPushed = false;
        // Return the label as the value of the cell editor
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        // Reset the isPushed flag to false when editing is stopped.
        // This is important to ensure that the next time the cell is edited,
        // it doesn't act as if the button was already pressed.
        isPushed = false;
        // Call the superclass method to handle the actual stopping of cell editing.
        // This will do any necessary cleanup and notify listeners that editing has stopped,
        // which is important for the table to update its state correctly.
        return super.stopCellEditing();
    }
}
