package PizzaUI;
import PizzaApp.Pizza;
import PizzaApp.UiController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the right side panel in the application's UI, which displays selected pizzas for an order,
 * allows removal of pizzas, and enables order submission. It also shows the subtotal for the selected pizzas.
 */
public class RightPanel extends JPanel {
    private JTable pizzaTable; // Table to display selected pizzas
    private PizzaTableModel pizzaTableModel; // Model for the pizza table
    private UiController uiController; // Controller to interact with the application logic
    private JButton addOrderButton = new JButton("Tilføj Order"); // Button to add the order
    private JTextField subTotalField; // Field to display the subtotal of the order

    /**
     * Constructs the RightPanel with a reference to the UiController.
     *
     * @param uiController The UiController for managing UI actions.
     */
    public RightPanel(UiController uiController) {
        this.uiController = uiController;
        setLayout(new BorderLayout());
        // Initialize the pizza table model and table
        pizzaTableModel = new PizzaTableModel(uiController.getPizzaList(),ButtonType.RPIZZA);
        pizzaTable = new JTable(pizzaTableModel);

        // Setup the custom renderer and editor for the "Remove" button column
        buttonRender(pizzaTable);

        // Add the table to the panel inside a scroll pane
        add(new JScrollPane(pizzaTable), BorderLayout.CENTER);

        // Initialize the subtotal field and disable editing
        subTotalField = new JTextField(String.valueOf(subTotal()));
        subTotalField.setEditable(false);

        // Setup the south panel to hold the subtotal field and the add order button
        JPanel southPanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 column
        // add the addOrderButton and the textfield to the panel
        southPanel.add(new JLabel("Subtotal:"));
        southPanel.add(subTotalField);
        if (uiController.getPizzaList().isEmpty()){
            addOrderButton.setEnabled(false);
        }
        addOrderButton.setOpaque(true);
        southPanel.add(addOrderButton,BorderLayout.EAST);

        // add the panel
        add(southPanel, BorderLayout.SOUTH);

        // Add mouse listener to handle clicks on the table's button column
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

    public JButton getAddOrderButton() {
        return addOrderButton;
    }

    /**
     * Updates the list of selected pizzas displayed in the table and recalculates the subtotal.
     *
     * @param pizzas The new list of selected pizzas to display.
     */
    public void updateSelectedPizzaList(List<Pizza> pizzas){
        if (!uiController.getPizzaList().isEmpty()){
            addOrderButton.setEnabled(true);
        } else {
            addOrderButton.setEnabled(false);
        }
        pizzaTableModel = new PizzaTableModel(pizzas, ButtonType.RPIZZA); // Re-initialize pizzaTableModel with new pizzas
        pizzaTable.setModel(pizzaTableModel);
        subTotalField.setText(String.valueOf(subTotal()));
        buttonRender(pizzaTable);
    }

    /**
     * Calculates the subtotal price of the selected pizzas.
     *
     * @return The subtotal price.
     */
    private double subTotal(){
        double temp=0;
        for (Pizza pizza: uiController.getPizzaList()){
            temp+= pizza.getPrice();
        }
            return temp;
    }

    /**
     * Configures custom renderers and editors for the table to display interactive "Remove" buttons.
     *
     * @param pizzaTable The JTable to be configured.
     */
    public void buttonRender(JTable pizzaTable){
        pizzaTable.getColumn("Fjern").setCellRenderer(new ButtonRenderer());
        pizzaTable.getColumn("Fjern").setCellEditor(new ButtonEditor(new JCheckBox(), uiController, this,ButtonType.RPIZZA));
    }
}

