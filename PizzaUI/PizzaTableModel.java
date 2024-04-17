package PizzaUI;

import PizzaApp.Pizza;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PizzaTableModel extends AbstractTableModel {
    private final List<Pizza> pizzas;
    private final String[] columnNames;
    private String buttonOption;
    private ButtonType buttonType;

    public PizzaTableModel(List<Pizza> pizzas, ButtonType buttonType) {
        this.pizzas = pizzas;
        this.buttonType=buttonType;
        if (buttonType == ButtonType.RPIZZA){
            buttonOption = "Fjern";
            this.columnNames = new String[]{"Nummer", "Navn", "Pris", buttonOption};
        } else {
            buttonOption = "Tilføj";
            this.columnNames = new String[]{"Nummer", "Navn", "ingredienser", "Pris", buttonOption};
        }

    }

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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4; // Only the "Add" button column is editable
    }
}

