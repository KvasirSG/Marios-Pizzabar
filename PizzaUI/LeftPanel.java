package PizzaUI;
import javax.swing.*;

/**
 * Represents the left panel in the application's UI. This panel contains buttons
 * for navigating various sections of the application, such as viewing the menu,
 * active orders, completed orders, and managing the menu.
 * @author Kenneth Heimann
 */
public class LeftPanel extends JPanel {
    private JButton viewMenuButton = new JButton("Se menu");
    private JButton viewActiveOrdersButton = new JButton("Se aktive ordrer");
    private JButton viewCompletedOrdersButton = new JButton("Se fuldførte ordrer");
    private JButton manageMenuButton = new JButton("Administrer menu");

    /**
     * Constructs the LeftPanel and initializes its layout and components.
     * Buttons are added to the panel for the different navigation options.
     */
    public LeftPanel() {
        // Set the panel's layout to BoxLayout, aligning components vertically
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Add navigation buttons to the panel
        add(viewMenuButton);
        add(viewActiveOrdersButton);
        add(viewCompletedOrdersButton);
        add(manageMenuButton);
    }

    // Below are getter methods for each button. They allow other parts of the application
    // to attach action listeners to these buttons for navigation.

    /**
     * Gets the 'View Menu' button.
     *
     * @return The button for viewing the menu.
     */
    public JButton getViewMenuButton() {
        return viewMenuButton;
    }

    /**
     * Gets the 'View Active Orders' button.
     *
     * @return The button for viewing active orders.
     */
    public JButton getViewActiveOrdersButton() {
        return viewActiveOrdersButton;
    }

    /**
     * Gets the 'View Completed Orders' button.
     *
     * @return The button for viewing completed orders.
     */
    public JButton getViewCompletedOrdersButton() {
        return viewCompletedOrdersButton;
    }

    /**
     * Gets the 'Manage Menu' button.
     *
     * @return The button for managing the menu.
     */
    public JButton getManageMenuButton() {
        return manageMenuButton;
    }
}

