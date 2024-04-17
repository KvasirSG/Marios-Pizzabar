package PizzaUI;
import PizzaApp.Pizza;
import PizzaApp.UiController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private JTable pizzaTable;
    private PizzaTableModel pizzaTableModel;
    private UiController uiController;
    private JButton addOrderButton = new JButton("Tilføj Order");
    private JTextField subTotalField;

    public RightPanel(UiController uiController) {
        this.uiController = uiController;
        setLayout(new BorderLayout());
        pizzaTableModel = new PizzaTableModel(uiController.getPizzaList(),ButtonType.RPIZZA);
        pizzaTable = new JTable(pizzaTableModel);

        // Setting the custom renderer and editor for the "Remove" button column
        buttonRender(pizzaTable);
        add(new JScrollPane(pizzaTable), BorderLayout.CENTER);



        subTotalField = new JTextField(String.valueOf(subTotal()));
        subTotalField.setEditable(false);
        // Create a panel to hold the field and the button
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

    public void updateSelectedPizzaList(List<Pizza> pizzas){
        if (!uiController.getPizzaList().isEmpty()){
            addOrderButton.setEnabled(true);
        }
        pizzaTableModel = new PizzaTableModel(pizzas, ButtonType.RPIZZA); // Re-initialize pizzaTableModel with new pizzas
        pizzaTable.setModel(pizzaTableModel);
        subTotalField.setText(String.valueOf(subTotal()));
        buttonRender(pizzaTable);
    }
    private double subTotal(){
        double temp=0;
        for (Pizza pizza: uiController.getPizzaList()){
            temp+= pizza.getPrice();
        }
            return temp;
    }
    public void buttonRender(JTable pizzaTable){
        pizzaTable.getColumn("Fjern").setCellRenderer(new ButtonRenderer());
        pizzaTable.getColumn("Fjern").setCellEditor(new ButtonEditor(new JCheckBox(), uiController, this,ButtonType.RPIZZA));
    }
}

