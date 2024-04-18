package PizzaUI;
import PizzaApp.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents a panel for displaying a list of completed orders in the UI.
 * This component uses a JTable to present the completed orders to the user.
 * @author Kenneth Heimann
 */
public class CompletedOrdersCard extends JPanel {
    private JTable orderTable;
    private OrderTableModel orderTableModel;

    /**
     * Constructs a CompletedOrdersCard panel.
     * Initializes the panel with a list of completed orders and displays them in a table.
     *
     * @param completedOrders The initial list of completed orders to display.
     */
    public CompletedOrdersCard(List<Order> completedOrders) {
        setLayout(new BorderLayout());
        // Initialize the table model with the completed orders and set it to the table
        orderTableModel = new OrderTableModel(completedOrders,ButtonType.NONE);
        // Add the table to the panel inside a scroll pane for scrolling capability
        orderTable = new JTable(orderTableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);
    }

    /**
     * Updates the list of completed orders displayed by the panel.
     * This method can be called to refresh the table contents when the list of completed orders changes.
     *
     * @param completedOrders The updated list of completed orders to display.
     */
    public void updateCompletedOrdersList(List<Order> completedOrders) {
        // Update the table model with the new list of completed orders and refresh the table view
        orderTableModel = new OrderTableModel(completedOrders,ButtonType.NONE);
        orderTable.setModel(orderTableModel);
    }
}
