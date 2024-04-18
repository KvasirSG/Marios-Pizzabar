package PizzaUI;
import PizzaApp.Order;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        orderTableModel = new OrderTableModel(uiController.getAllOrders(),ButtonType.AORDER);
        orderTable = new JTable(orderTableModel);

        buttonRender(orderTable);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);
        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = orderTable.columnAtPoint(e.getPoint());
                int row = orderTable.rowAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    // This line ensures the cell is in editing mode, which is necessary for the custom editor (button) to be active
                    orderTable.editCellAt(row, column);

                    // Attempt to directly click the button if the cell editor is a JButton
                    Component editorComp = orderTable.getEditorComponent();
                    if (editorComp instanceof JButton) {
                        ((JButton) editorComp).doClick();
                    }
                }
            }
        });

    }

    // Method to update the active orders list
    public void updateActiveOrdersList(List<Order> activeOrders) {
        orderTableModel = new OrderTableModel(activeOrders, ButtonType.AORDER);
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

