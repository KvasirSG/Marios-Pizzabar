package PizzaUI;

import PizzaApp.Pizza;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private String label;
    private Pizza pizza; // The Pizza object to act upon

    private RightPanel rightPanel;

    private UiController uiController;

    public ButtonEditor(JCheckBox checkBox, UiController uiController, RightPanel rightPanel) {
        super(checkBox);
        this.uiController = uiController;
        this.rightPanel = rightPanel;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                isPushed = true;
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        PizzaTableModel model = (PizzaTableModel) table.getModel();
        label = (value == null) ? "" : value.toString();
        this.pizza = model.getPizzaAt(row);
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Perform action on button click
            uiController.addPizzaToList(pizza.getPizzaID());
            rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
