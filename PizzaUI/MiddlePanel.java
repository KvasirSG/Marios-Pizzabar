package PizzaUI;

import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiddlePanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private MenuCard menuCard;
    private ActiveOrdersCard activeOrdersCard;
    private CompletedOrdersCard completedOrdersCard;
    private UiController uiController;

    public MiddlePanel(UiController uiController, RightPanel rightPanel) {
        this.uiController = uiController;
        setLayout(cardLayout);
        // Initialize cards with dummy or initial data
        menuCard = new MenuCard(uiController, rightPanel); // Ideally, fetch the real data
        activeOrdersCard = new ActiveOrdersCard(uiController.getAllOrders());
        completedOrdersCard = new CompletedOrdersCard(uiController.getAllCompletedOrders());

        add(menuCard, "Menu");
        add(activeOrdersCard, "Active Orders");
        add(completedOrdersCard, "Completed Orders");
    }

    public void showCard(String cardName) {
        cardLayout.show(this, cardName);
    }

    public void updateMenu(){
        menuCard.updatePizzaList(uiController.getPizzaMenu());
    }
    public void updateAOrders(){
        activeOrdersCard.updateActiveOrdersList(uiController.getAllOrders());
    }
    public void updateCOrders(){
        completedOrdersCard.updateCompletedOrdersList(uiController.getAllCompletedOrders());
    }
}

