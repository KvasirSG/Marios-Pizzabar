package PizzaUI;
import PizzaApp.Order;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActiveOrdersCard extends JPanel {
   private JTable orderTable;
   private UiController uiController;
   private MiddlePanel middlePanel;
    private OrderTableModel orderTableModel;

    public ActiveOrdersCard(UiController uiController, MiddlePanel middlePanel) {
        this.uiController = uiController;
        this.middlePanel = middlePanel;
        setLayout(new BorderLayout());
        // Populate the list with active orders
        orderTableModel = new OrderTableModel(uiController.getAllOrders());
        orderTable = new JTable(orderTableModel);

        buttonRender(orderTable);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);
    }

    // Method to update the active orders list
    public void updateActiveOrdersList(List<Order> activeOrders) {
        orderTableModel = new OrderTableModel(activeOrders);
        orderTable.setModel(orderTableModel);
        buttonRender(orderTable);

    }

    public void buttonRender(JTable orderTable){
        orderTable.getColumn("C").setCellRenderer(new ButtonRenderer());
        orderTable.getColumn("C").setCellEditor(new ButtonEditor(new JCheckBox(),uiController,middlePanel,ButtonType.CORDER));
        orderTable.getColumn("R").setCellRenderer(new ButtonRenderer());
        orderTable.getColumn("R").setCellEditor(new ButtonEditor(new JCheckBox(),uiController,middlePanel,ButtonType.RORDER));
    }
}

