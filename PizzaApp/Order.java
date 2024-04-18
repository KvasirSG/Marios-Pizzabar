package PizzaApp;

import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 * Represents an order in the Pizza application. Each order has a unique ID,
 * a list of pizzas, an order time, a priority, and a completion time.
 * @author Sebastian Dufour
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 2L;
    private static int nextID;
    private long orderID;
    private List<Pizza> pizzas;
    private LocalDateTime orderTime;
    private int priority;
    private LocalDateTime completionTime;

    /**
     * Constructs a new Order with a unique ID, initializes the pizza list, and sets
     * the order time to the current moment.
     */
    public Order() {
        this.orderID = generateIDFromTimestamp();
        this.pizzas = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    /**
     * Constructs a new Order with a specified ID, pizza list, and order time.
     * This constructor is intended for use when reconstructing orders from storage.
     *
     * @param orderID The unique ID of the order.
     * @param pizzaList The list of pizzas in the order.
     * @param orderTime The time the order was placed.
     */
    public Order(Long orderID, List<Pizza> pizzaList, LocalDateTime orderTime) {
        this.orderID = orderID;
        this.pizzas = pizzaList;
        this.orderTime = orderTime;
    }

    /**
     * Generates a unique ID for the order based on the current timestamp.
     *
     * @return A unique long representing the ID.
     */
    private long generateIDFromTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() % 100; // Get last two digits of the year
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        // Concatenates and converts the first 6 digits to an integer to be used for ID
        // of the individual orderss
        String idString = String.format("%02d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);
        return Long.parseLong(idString);
    }

    /**
     * Returns the time the order was placed.
     *
     * @return The order time.
     */
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    // End of getOrderTime

    /**
     * Returns the unique ID of the order.
     *
     * @return The order ID.
     */
    public long getOrderID() {
        return orderID;
    }
    // End of Getter method for OrderID

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The Pizza to add to the order.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Returns the list of pizzas in the order.
     *
     * @return The list of Pizza objects.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Sets the priority of the order.
     *
     * @param priority The priority level of the order.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority of the order.
     *
     * @return The order's priority level.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the completion time of the order.
     *
     * @param completionTime The time when the order was completed.
     */
    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    /**
     * Returns the completion time of the order.
     *
     * @return The completion time.
     */
    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    /**
     * Provides a string representation of the order, including the ID, order time,
     * priority, and pizzas.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderID: ").append(orderID).append(", ");
        sb.append("OrderTime: ").append(orderTime).append(", ");
        sb.append("Priority: ").append(priority).append(", ");
        sb.append("Pizzas: [");

        for (int i = 0; i < pizzas.size(); i++) {
            sb.append(pizzas.get(i).toString());
            if (i < pizzas.size() - 1) {
                sb.append(", "); // Add comma except after the last pizza
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Calculates the total price of all pizzas in the order.
     *
     * @return The sum of prices of all pizzas in the order.
     */
    public double getSumPrice() {
        double sum = 0.0;
        for (Pizza pizza : pizzas) {
            sum += pizza.getPrice();
        }
        return sum;
    }

}
