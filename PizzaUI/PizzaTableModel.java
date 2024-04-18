package PizzaUI;

import PizzaApp.Pizza;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * A table model for displaying pizzas in a JTable, including functionality for adding or removing
 * pizzas from an order based on the specified ButtonType.
 * @author Kenneth Heimann
 */
public class PizzaTableModel extends AbstractTableModel {
    private final List<Pizza> pizzas;
    private final String[] columnNames;
    private String buttonOption;
    private ButtonType buttonType;

    /**
     * Constructs a PizzaTableModel with a list of pizzas and a specified button type.
     *
     * @param pizzas The list of Pizza objects to display in the table.
     * @param buttonType The type of button to include in the table, influencing the column setup.
     */
    public PizzaTableModel(List<Pizza> pizzas, ButtonType buttonType) {
        this.pizzas = pizzas;
        this.buttonType=buttonType;
        // Determine the button option and set up column names accordingly
        if (buttonType == ButtonType.RPIZZA){
            buttonOption = "Fjern"; // "Remove" option
            this.columnNames = new String[]{"Nummer", "Navn", "Pris", buttonOption};
        } else {
            buttonOption = "Tilføj"; // "Add" option
            this.columnNames = new String[]{"Nummer", "Navn", "ingredienser", "Pris", buttonOption};
        }

    }

    /**
     * Retrieves the Pizza object at the specified row index.
     *
     * @param rowIndex The index of the row for which to get the Pizza object.
     * @return The Pizza object at the specified row index.
     */
    public Pizza getPizzaAt(int rowIndex) {
        return pizzas.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return pizzas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pizza pizza = pizzas.get(rowIndex);
        // Populate cell values based on the column index
        if (buttonType==ButtonType.RPIZZA){
            switch (columnIndex) {
                case 0: return pizza.getPizzaID();
                case 1: return pizza.getName();
                case 2: return pizza.getPrice();
                case 3: return buttonOption; // Button text
                default: return null;
            }
        } else {
            switch (columnIndex) {
                case 0: return pizza.getPizzaID();
                case 1: return pizza.getName();
                case 2: return pizza.getIngredient();
                case 3: return pizza.getPrice();
                case 4: return buttonOption; // Button text
                default: return null;
            }
        }
    }

    /**
     * Determines if the cell at the specified row and column index is editable.
     *
     * @param rowIndex The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return true if the cell is editable, otherwise false.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (buttonType== ButtonType.RPIZZA){
            // Editable if the column is the button column
            return columnIndex == 3; // Only the "Remove" button column is editable
        } else {
            return columnIndex == 4; // Only the "Add" button column is editable
        }

    }
}

