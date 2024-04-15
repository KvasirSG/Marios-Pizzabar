/*OrderManager class intended to handle and manage the orders prior to use in the controller class
Made by Duofour*/

import java.time.*;
import java.util.*;

public class OrderManager {
    private List<Order> orders;
    String orderFile = "Orders.pizza";
    private List<Order> completedOrders;
    String completeOrderFile = "CompleteOrders.pizza"; 
    private int nextPriority;

    // Constructor to initialize the OrderManager
    public OrderManager() {
        this.orders = new ArrayList<>();
        List<Order> tempOrderList = DataManager.readOrdersFromFile(orderFile);
         if (tempOrderList != null){
            orders.addAll(tempOrderList);
        }
        this.completedOrders = new ArrayList<>();
        List<Order> tempCompleteOrderList = DataManager.readOrdersFromFile(completeOrderFile);
         if (tempCompleteOrderList != null){
            orders.addAll(tempCompleteOrderList);
        }
        this.nextPriority = 1;
    }

    // Method to add an order to the list of active orders
public boolean addOrder(Order order, int tempOrderCompletionTime) {    
    order.setPriority(nextPriority++);
    order.setCompletionTime(LocalDateTime.now().plusMinutes(tempOrderCompletionTime));
    boolean tempAddOrder = orders.add(order);
    if (orders.size() % 5 == 0) { // Check if 5 orders have been placed at the same time
       for (Order o : orders) {
         o.setCompletionTime(o.getCompletionTime().plusMinutes(20)); // Increase completion time by 10 minutes
        }
    }
    DataManager.writeOrderToFile(orders, orderFile);
    return tempAddOrder;
}

    // Method to remove an order from the list of active orders based on its priority
    public boolean removeOrder(int priority) {
        for (Order order : orders) {
            if (order.getPriority() == priority) {
                boolean removed = orders.remove(order);
                DataManager.writeOrderToFile(orders, orderFile);
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
                DataManager.writeOrderToFile(orders, completeOrderFile);
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
        DataManager.writeOrderToFile(orders, orderFile);
        nextPriority = priority;
    }

    // Method to get all completed orders
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }
}
