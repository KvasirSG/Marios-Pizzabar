package PizzaUI;
import PizzaApp.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CompletedOrdersCard extends JPanel {
    private JTable orderTable;
    private OrderTableModel orderTableModel;

    public CompletedOrdersCard(List<Order> completedOrders) {
        setLayout(new BorderLayout());
        /// Populate the list with active orders
        orderTableModel = new OrderTableModel(completedOrders,ButtonType.CPORDER);
        orderTable = new JTable(orderTableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);
    }

    // Method to refresh the list of completed orders
    public void updateCompletedOrdersList(List<Order> completedOrders) {
        orderTableModel = new OrderTableModel(completedOrders,ButtonType.CPORDER);
        orderTable.setModel(orderTableModel);
    }
}
