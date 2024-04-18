package PizzaUI;

import PizzaApp.Order;
import PizzaApp.Pizza;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom table model for displaying orders in the application. This model supports
 * displaying orders with or without action buttons based on the specified ButtonType.
 * @author Kenneth Heimann
 */
public class OrderTableModel extends AbstractTableModel {
    private final List<Order> orders; // List of orders to display
    private final String[] columnNames; // Names of the columns in the table
    private ButtonType buttonType; // Indicates whether the table should include action buttons

    /**
     * Constructs an OrderTableModel with a list of orders and a button type.
     *
     * @param orders The list of orders to be displayed in the table.
     * @param buttonType Specifies the type of buttons (if any) included in the table.
     */
    public OrderTableModel(List<Order> orders, ButtonType buttonType){
        this.orders = orders;
        this.buttonType = buttonType;
        // Define column names based on the presence or absence of action buttons
        if (buttonType == ButtonType.NONE){
            columnNames = new String[]{"Order ID", "Pizzanavne", "Total pris", "Order Tid"};
        } else {
            // Includes columns for action buttons
            columnNames = new String[]{"Prioritet", "Pizzanavne", "Total pris", "Fuldførelsestid", "C", "R"};
        }
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        // Switch based on buttonType to determine what values to return for each column
        if (buttonType == ButtonType.NONE){
            switch (columnIndex){
                case 0: return order.getOrderID();
                case 1: return getPizzaNames(order.getPizzas());
                case 2: return order.getSumPrice();
                case 3:
                    LocalDateTime temp = order.getOrderTime();
                    // Create a formatter for the pattern "yyyy/MM/dd - HH:mm:ss"
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
                    // Format the LocalDateTime instance
                    return temp.format(formatter);
                default: return null;
            }
        }
        else {
            switch (columnIndex){
                case 0: return order.getPriority();
                case 1: return getPizzaNames(order.getPizzas());
                case 2: return order.getSumPrice();
                case 3:
                    LocalDateTime temp = order.getCompletionTime();
                    // Create a formatter for the pattern "HH:mm"
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    // Format the LocalDateTime instance
                    return temp.format(formatter);
                case 4: return "Fuldfør"; // button text
                case 5: return "Aflys"; // button text
                default: return null;
            }
        }

    }

    public Order getOrderAt(int rowIndex){
        return orders.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        // Only allow editing (clicking) for columns with buttons
        return columnIndex ==4 || columnIndex ==5;
    }

    /**
     * Gets a list of pizza names from a list of Pizza objects.
     *
     * @param pizzas The list of pizzas.
     * @return A list of pizza names.
     */
    public List<String> getPizzaNames(List<Pizza> pizzas){
        List<String> pizzanames = new ArrayList<>();
        for (Pizza pizza:pizzas){
            pizzanames.add(pizza.getName());
        }
        return pizzanames;
    }

}
