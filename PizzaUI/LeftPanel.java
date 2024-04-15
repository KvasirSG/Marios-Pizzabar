package PizzaUI;
import javax.swing.*;

public class LeftPanel extends JPanel {
    private JButton viewMenuButton = new JButton("View Menu");
    private JButton viewActiveOrdersButton = new JButton("View Active Orders");
    private JButton viewCompletedOrdersButton = new JButton("View Completed Orders");
    private JButton manageMenuButton = new JButton("Manage Menu");

    public LeftPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(viewMenuButton);
        add(viewActiveOrdersButton);
        add(viewCompletedOrdersButton);
        add(manageMenuButton);
    }

    // Getters for buttons to add action listeners in the main UI controller
    public JButton getViewMenuButton() {
        return viewMenuButton;
    }

    public JButton getViewActiveOrdersButton() {
        return viewActiveOrdersButton;
    }

    public JButton getViewCompletedOrdersButton() {
        return viewCompletedOrdersButton;
    }

    public JButton getManageMenuButton() {
        return manageMenuButton;
    }
}

