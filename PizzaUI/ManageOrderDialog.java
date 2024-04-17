package PizzaUI;

import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;

public class ManageOrderDialog extends JDialog {
    private JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(10, 5, 100.0, 1));
    private JButton addButton = new JButton("Make Order");
    private UiController uiController; // Reference to the controller
    private RightPanel rightPanel;

    public ManageOrderDialog(JFrame parent, UiController uiController, RightPanel rightPanel){
        super(parent,"Administrer Order", true);
        this.uiController=uiController;
        this.rightPanel=rightPanel;
        setSize(400,100);
        setLayout(new GridLayout(0,2));
        add(new JLabel("Tilføj forventet afhentningstid:"));
        add(minuteSpinner);
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(new JPanel()); // Add an empty JPanel as a placeholder
        add(addButton);
        attachEventHandlers();
    }

    private void attachEventHandlers(){
        addButton.addActionListener(e -> {
            double temp = (double) minuteSpinner.getValue();
            int completionTime = (int) temp;
            uiController.addPizzaOrder(completionTime);
            rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
            JOptionPane.showMessageDialog(this,"Ordre tilføjet med succes!");
        });
    }
}
