package PizzaUI;
import PizzaApp.Order;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents a panel within the Pizza application UI that displays active orders.
 * It allows for interaction with the orders, such as completion or removal, through a table interface.
 * @author Kenneth Heimann
 */
public class ActiveOrdersCard extends JPanel {
   private JTable orderTable;
   private UiController uiController;
   private MiddlePanel middlePanel;
    private OrderTableModel orderTableModel;

    /**
     * Constructs an ActiveOrdersCard panel which is responsible for displaying a table of active orders.
     *
     * @param uiController The UI controller used to manage the application's data and state.
     * @param middlePanel The middle panel of the application's UI, used for interaction callbacks.
     */
    public ActiveOrdersCard(UiController uiController, MiddlePanel middlePanel) {
        this.uiController = uiController;
        this.middlePanel = middlePanel;
        setLayout(new BorderLayout());

        // Initialize and populate the order table with active orders
        orderTableModel = new OrderTableModel(uiController.getAllOrders(),ButtonType.AORDER);
        orderTable = new JTable(orderTableModel);

        // Setup button renderers and editors for the table
        buttonRender(orderTable);

        // Add the table to the panel within a scroll pane
        add(new JScrollPane(orderTable), BorderLayout.CENTER);

        // Add a mouse listener to handle clicks on the table's buttons
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

    /**
     * Updates the list of active orders displayed in the table.
     *
     * @param activeOrders The list of active orders to display.
     */
    public void updateActiveOrdersList(List<Order> activeOrders) {
        orderTableModel = new OrderTableModel(activeOrders, ButtonType.AORDER);
        orderTable.setModel(orderTableModel);
        buttonRender(orderTable);

    }

    /**
     * Configures button renderers and editors for the order table to enable interactive buttons within the table cells.
     *
     * @param orderTable The JTable to which button renderers and editors are to be added.
     */
    public void buttonRender(JTable orderTable){
        orderTable.getColumn("C").setCellRenderer(new ButtonRenderer());
        orderTable.getColumn("C").setCellEditor(new ButtonEditor(new JCheckBox(),uiController,middlePanel,ButtonType.CORDER));
        orderTable.getColumn("R").setCellRenderer(new ButtonRenderer());
        orderTable.getColumn("R").setCellEditor(new ButtonEditor(new JCheckBox(),uiController,middlePanel,ButtonType.RORDER));
    }
}

