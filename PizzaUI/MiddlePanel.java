package PizzaUI;

import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the middle section of the application's UI, capable of displaying
 * different panels (cards) such as the menu, active orders, and completed orders.
 * This panel uses a CardLayout to facilitate the switching between different views.
 * @author Kenneth Heimann
 */
public class MiddlePanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private MenuCard menuCard;
    private ActiveOrdersCard activeOrdersCard;
    private CompletedOrdersCard completedOrdersCard;
    private UiController uiController;

    /**
     * Constructs the MiddlePanel with references to the UIController and the RightPanel.
     * Initializes and displays various cards for the application's UI.
     *
     * @param uiController The controller for UI actions and interactions.
     * @param rightPanel The RightPanel, passed to the MenuCard for UI updates.
     */
    public MiddlePanel(UiController uiController, RightPanel rightPanel) {
        this.uiController = uiController;
        setLayout(cardLayout);
        // Initialize cards
        menuCard = new MenuCard(uiController, rightPanel); // Ideally, fetch the real data
        activeOrdersCard = new ActiveOrdersCard(uiController,this);
        completedOrdersCard = new CompletedOrdersCard(uiController.getAllCompletedOrders());

        // Add cards to the panel with identifiable names for switching
        add(menuCard, "Menu");
        add(activeOrdersCard, "Active Orders");
        add(completedOrdersCard, "Completed Orders");
    }

    /**
     * Switches the visible card in the CardLayout to the specified one.
     *
     * @param cardName The name of the card to be shown.
     */
    public void showCard(String cardName) {
        cardLayout.show(this, cardName);
    }

    /**
     * Updates the menu card with the latest pizza menu items.
     */
    public void updateMenu(){
        // Fetch the latest pizza menu from the controller and update the MenuCard
        menuCard.updatePizzaList(uiController.getPizzaMenu());
    }

    /**
     * Updates the active orders card with the latest active orders.
     */
    public void updateAOrders(){
        // Fetch the latest active orders from the controller and update the ActiveOrdersCard
        activeOrdersCard.updateActiveOrdersList(uiController.getAllOrders());
    }

    /**
     * Updates the completed orders card with the latest completed orders.
     */
    public void updateCOrders(){
        // Fetch the latest completed orders from the controller and update the CompletedOrdersCard
        completedOrdersCard.updateCompletedOrdersList(uiController.getAllCompletedOrders());
    }
}

