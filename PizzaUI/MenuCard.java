package PizzaUI;
import PizzaApp.Pizza;

import javax.swing.*;
import java.awt.*;
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


