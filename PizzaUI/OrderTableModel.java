package PizzaUI;

import PizzaApp.Order;
import PizzaApp.Pizza;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private final List<Order> orders;
    private final String[] columnNames = {"Prioritet", "Pizzanavne","Total pris","Fuldførelsestid","C","R"};
    public OrderTableModel(List<Order> orders){
        this.orders = orders;
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

    public Order getOrderAt(int rowIndex){
        return orders.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex ==4 || columnIndex ==5;
    }
    public List<String> getPizzaNames(List<Pizza> pizzas){
        List<String> pizzanames = new ArrayList<>();
        for (Pizza pizza:pizzas){
            pizzanames.add(pizza.getName());
        }
        return pizzanames;
    }

}
