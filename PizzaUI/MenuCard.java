package PizzaUI;
import PizzaApp.Pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import PizzaApp.UiController;

public class MenuCard extends JPanel {
    private JTable pizzaTable;
    private PizzaTableModel pizzaTableModel;

    private UiController uiController;
    private RightPanel rightPanel;

    public MenuCard(UiController uiController, RightPanel rightPanel) {
        this.rightPanel = rightPanel;
        this.uiController = uiController;
        setLayout(new BorderLayout());
        pizzaTableModel = new PizzaTableModel(uiController.getPizzaMenu(),ButtonType.PIZZA);
        pizzaTable = new JTable(pizzaTableModel);

        // Setting the custom renderer and editor for the "Add" button column
        buttonRender(pizzaTable);

        add(new JScrollPane(pizzaTable), BorderLayout.CENTER);
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

    // Method to update the pizza list if the menu changes
    public void updatePizzaList(List<Pizza> pizzas) {
        pizzaTableModel = new PizzaTableModel(pizzas, ButtonType.PIZZA); // Re-initialize pizzaTableModel with new pizzas
        pizzaTable.setModel(pizzaTableModel);
        buttonRender(pizzaTable);
    }

    public void buttonRender(JTable pizzaTable){
        pizzaTable.getColumn("Tilføj").setCellRenderer(new ButtonRenderer());
        pizzaTable.getColumn("Tilføj").setCellEditor(new ButtonEditor(new JCheckBox(), uiController, rightPanel,ButtonType.PIZZA));
    }
}


