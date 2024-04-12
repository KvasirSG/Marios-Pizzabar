/*OrderManager class intended to handle and manage the orders prior to use in the controller class
Made by Duofour*/

import java.util.*;

public class OrderManager {
    private List<Order> orders;
    private List<Order> completedOrders;
    private int nextPriority;

    // Constructor to initialize the OrderManager
    public OrderManager() {
        this.orders = new ArrayList<>();
        this.completedOrders = new ArrayList<>();
        this.nextPriority = 1;
    }

    // Method to add an order to the list of active orders
    public boolean addOrder(Order order) {
        order.setPriority(nextPriority++);
        return orders.add(order);
    }

    // Method to remove an order from the list of active orders based on its priority
    public boolean removeOrder(int priority) {
        for (Order order : orders) {
            if (order.getPriority() == priority) {
                boolean removed = orders.remove(order);
                if (removed) {
                    updatePriorities();
                }
                return removed; 
            }
        }
        return false; 
    }

    // Method to complete an order based on its priority
    public boolean completeOrder(int priority) {
        for (Order order : orders) {
            if (order.getPriority() == priority) {
                boolean removed = orders.remove(order);
                if (removed) {
                    completedOrders.add(order);
                    updatePriorities();
                }
                return removed; 
            }
        }
        return false;
    }

    // Method to get an order by its ID from the list of active orders
    public Order getOrder(long orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    // Method to get all active orders
    public List<Order> getOrders() {
        return orders;
    }
    // Method to update priorities after an order is removed
    private void updatePriorities() {
        int priority = 1;
        for (Order order : orders) {
            order.setPriority(priority++);
        }
        nextPriority = priority;
    }

    // Method to get all completed orders
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }
}
