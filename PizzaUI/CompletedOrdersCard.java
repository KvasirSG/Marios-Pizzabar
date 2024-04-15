package PizzaUI;
import PizzaApp.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CompletedOrdersCard extends JPanel {
    private JList<Order> completedOrdersList = new JList<>();

    public CompletedOrdersCard(List<Order> completedOrders) {
        setLayout(new BorderLayout());
        // Initialize the list with completed orders
        completedOrdersList.setListData(completedOrders.toArray(new Order[0]));
        add(new JScrollPane(completedOrdersList), BorderLayout.CENTER);
    }

    // Method to refresh the list of completed orders
    public void updateCompletedOrdersList(List<Order> completedOrders) {
        completedOrdersList.setListData(completedOrders.toArray(new Order[0]));
    }
}
