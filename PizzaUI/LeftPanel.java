package PizzaUI;
import javax.swing.*;

public class LeftPanel extends JPanel {
    private JButton viewMenuButton = new JButton("Se menu");
    private JButton viewActiveOrdersButton = new JButton("Se aktive ordrer");
    private JButton viewCompletedOrdersButton = new JButton("Se fuldførte ordrer");
    private JButton manageMenuButton = new JButton("Administrer menu");

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

