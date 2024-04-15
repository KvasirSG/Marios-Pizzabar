package PizzaUI;
import PizzaApp.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActiveOrdersCard extends JPanel {
    private JList<Order> activeOrdersList = new JList<>(); // Again, assuming a custom list model or override toString in Order class

    public ActiveOrdersCard(List<Order> activeOrders) {
        setLayout(new BorderLayout());
        // Populate the list with active orders
        activeOrdersList.setListData(activeOrders.toArray(new Order[0]));
        add(new JScrollPane(activeOrdersList), BorderLayout.CENTER);
    }

    // Method to update the active orders list
    public void updateActiveOrdersList(List<Order> activeOrders) {
        activeOrdersList.setListData(activeOrders.toArray(new Order[0]));
    }
}

