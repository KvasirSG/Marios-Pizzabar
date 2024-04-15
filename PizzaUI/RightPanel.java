package PizzaUI;
import PizzaApp.Pizza;
import PizzaApp.UiController;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private JList<Pizza> selectedPizzasList = new JList<>(); // You'll need a custom list model
    private JButton addOrderButton = new JButton("Add to Order");

    public RightPanel(UiController uiController) {
        setLayout(new BorderLayout());
        selectedPizzasList.setListData(uiController.getPizzaList().toArray(new Pizza[0]));
        add(new JScrollPane(selectedPizzasList), BorderLayout.CENTER);
        add(addOrderButton, BorderLayout.SOUTH);
    }

    public JButton getAddOrderButton() {
        return addOrderButton;
    }

    public void updateSelectedPizzaList(List<Pizza> pizzas){
        selectedPizzasList.setListData(pizzas.toArray(new Pizza[0]));
    }
}

