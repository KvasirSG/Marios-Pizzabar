package PizzaApp;

import java.time.*;
import java.util.*;

/**
 * Manages the lifecycle of orders in the Pizza application. This includes
 * adding, removing, completing orders, and handling their priorities.
 * @author Sebastian Dufour
 */
public class OrderManager {
    private List<Order> orders;
    private String orderFile = "PizzaApp/Orders.pizza";
    private List<Order> completedOrders;
    private String completeOrderFile = "PizzaApp/CompleteOrders.pizza";
    private int nextPriority;

    /**
     * Initializes a new instance of OrderManager, loading existing orders from storage
     * and preparing the system to manage new and completed orders.
     */
    public OrderManager() {
        this.nextPriority = 1;
        this.orders = new ArrayList<>();
        List<Order> tempOrderList = DataManager.readOrdersFromFile(orderFile);
         if (tempOrderList != null){
            orders.addAll(tempOrderList);
            updatePriorities();
        }
        this.completedOrders = new ArrayList<>();
        List<Order> tempCompleteOrderList = DataManager.readOrdersFromFile(completeOrderFile);
         if (tempCompleteOrderList != null){
            completedOrders.addAll(tempCompleteOrderList);
        }
    }

    /**
     * Adds an order to the system with a specified estimated completion time.
     * The order's priority is automatically set based on its addition.
     *
     * @param order The order to add.
     * @param tempOrderCompletionTime The estimated completion time in minutes.
     * @return true if the order was successfully added.
     */
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

    /**
     * Removes an order from the system based on its unique ID.
     *
     * @param orderID The unique ID of the order to remove.
     * @return true if the order was found and successfully removed.
     */
    public boolean removeOrder(long orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
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

    /**
     * Marks an order as completed based on its unique ID. Completed orders are moved
     * from the active list to the completed list.
     *
     * @param orderID The unique ID of the order to complete.
     * @return true if the order was found and successfully marked as completed.
     */
    public boolean completeOrder(long orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                boolean removed = orders.remove(order);
                if (removed) {
                    completedOrders.add(order);
                    updatePriorities();
                }
                DataManager.writeOrderToFile(completedOrders, completeOrderFile);
                return removed; 
            }
        }
        return false;
    }

    /**
     * Retrieves an order by its unique ID from the list of active orders.
     *
     * @param orderID The unique ID of the order.
     * @return The order with the specified ID, or null if no such order exists.
     */
    public Order getOrder(long orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    /**
     * Returns a list of all active orders.
     *
     * @return The list of active orders.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Updates the priorities of all orders sequentially. This method is called
     * after any operation that modifies the list of active orders.
     */
    private void updatePriorities() {
        int priority = 1;
        for (Order order : orders) {
            order.setPriority(priority++);
        }
        DataManager.writeOrderToFile(orders, orderFile);
        nextPriority = priority;
    }

    /**
     * Returns a list of all completed orders.
     *
     * @return The list of completed orders.
     */
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }
}
