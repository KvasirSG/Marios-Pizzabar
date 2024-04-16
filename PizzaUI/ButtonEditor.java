package PizzaUI;

import PizzaApp.Order;
import PizzaApp.Pizza;
import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
enum ButtonType {PIZZA,CORDER, RORDER}
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private String label;
    private Pizza pizza; // The Pizza object to act upon
    private Order order; // The Order object to act upon

    private RightPanel rightPanel;
    private MiddlePanel middlePanel;

    private UiController uiController;

    private ButtonType buttonType;

    public ButtonEditor(JCheckBox checkBox, UiController uiController, RightPanel rightPanel) {
        super(checkBox);
        this.uiController = uiController;
        this.rightPanel = rightPanel;
        this.buttonType= ButtonType.PIZZA;
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

    public ButtonEditor(JCheckBox checkBox, UiController uiController,MiddlePanel middlePanel, ButtonType buttonType){
        super(checkBox);
        this.uiController = uiController;
        this.buttonType = buttonType;
        this.middlePanel = middlePanel;
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
        label = (value == null) ? "" : value.toString();
        if (buttonType == ButtonType.PIZZA){
            PizzaTableModel model = (PizzaTableModel) table.getModel();
            this.pizza = model.getPizzaAt(row);
        } else if (buttonType==ButtonType.CORDER||buttonType==ButtonType.RORDER){
            OrderTableModel model = (OrderTableModel) table.getModel();
            this.order = model.getOrderAt(row);

        }

        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Perform action on button click
            if (buttonType == ButtonType.PIZZA){
                uiController.addPizzaToList(pizza.getPizzaID());
                rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
            }
            if (buttonType == ButtonType.CORDER){
                uiController.completeOrder(order.getPriority());
                middlePanel.updateAOrders();
            }
            if (buttonType == ButtonType.RORDER){
                uiController.removePizzaOrder(order.getPriority());
            }

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
