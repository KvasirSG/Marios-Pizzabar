package PizzaUI;
import PizzaApp.Pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import PizzaApp.UiController;

/**
 * Represents a panel displaying the pizza menu. Each row in the panel represents a pizza,
 * including a button for adding the pizza to an order. This class handles both the display
 * of the pizzas and the interaction with them through the UI.
 * @author Kenneth Heimann
 */
public class MenuCard extends JPanel {
    private JTable pizzaTable;
    private PizzaTableModel pizzaTableModel;

    private UiController uiController;
    private RightPanel rightPanel;

    /**
     * Constructs a MenuCard panel.
     *
     * @param uiController The controller for UI logic and interactions.
     * @param rightPanel The RightPanel instance for updating the UI based on user actions.
     */
    public MenuCard(UiController uiController, RightPanel rightPanel) {
        this.rightPanel = rightPanel;
        this.uiController = uiController;
        setLayout(new BorderLayout());

        // Initialize the pizza table model with the pizzas from the controller and set it to the table
        pizzaTableModel = new PizzaTableModel(uiController.getPizzaMenu(),ButtonType.PIZZA);
        pizzaTable = new JTable(pizzaTableModel);

        // Setup the custom renderer and editor for the "Add" button in the pizza table
        buttonRender(pizzaTable);

        // Add the pizza table to the panel inside a scroll pane
        add(new JScrollPane(pizzaTable), BorderLayout.CENTER);

        // Add a mouse listener to handle clicks on the table, specifically for the button interactions
        pizzaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = pizzaTable.columnAtPoint(e.getPoint());
                int row = pizzaTable.rowAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    pizzaTable.editCellAt(row, column);
                    Object editorComp = pizzaTable.getCellEditor(row, column).getTableCellEditorComponent(pizzaTable, null, true, row, column);
                    if (editorComp instanceof JButton) {
                        ((JButton) editorComp).doClick();
                    }
                }
            }
        });

    }

    /**
     * Updates the pizza list displayed in the table. This is typically called when the pizza menu changes.
     *
     * @param pizzas The new list of pizzas to display.
     */
    public void updatePizzaList(List<Pizza> pizzas) {
        pizzaTableModel = new PizzaTableModel(pizzas, ButtonType.PIZZA); // Re-initialize pizzaTableModel with new pizzas
        pizzaTable.setModel(pizzaTableModel);
        buttonRender(pizzaTable);
    }

    /**
     * Configures custom renderers and editors for the table to display interactive buttons.
     *
     * @param pizzaTable The JTable to be configured.
     */
    public void buttonRender(JTable pizzaTable){
        pizzaTable.getColumn("Tilføj").setCellRenderer(new ButtonRenderer());
        pizzaTable.getColumn("Tilføj").setCellEditor(new ButtonEditor(new JCheckBox(), uiController, rightPanel,ButtonType.PIZZA));
    }
}


