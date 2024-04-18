package PizzaUI;

import PizzaApp.UiController;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog window for creating new orders within the application. It allows the user to specify
 * an expected completion time for the order.
 * @author Kenneth Heimann
 */
public class ManageOrderDialog extends JDialog {
    private JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(10, 5, 100.0, 1));
    private JButton addButton = new JButton("Lav Order");
    private UiController uiController; // Reference to the controller
    private RightPanel rightPanel;

    /**
     * Constructs the ManageOrderDialog.
     *
     * @param parent The parent frame to which this dialog is attached.
     * @param uiController The UI controller for managing application logic.
     * @param rightPanel The RightPanel for updating the UI based on actions performed.
     */
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

    /**
     * Attaches event handlers to the dialog components, specifically the add button.
     */
    private void attachEventHandlers(){
        addButton.addActionListener(e -> {
            // Get the value from the spinner and cast it to an integer for the completion time
            double temp = (double) minuteSpinner.getValue();
            int completionTime = (int) temp;
            // Use the UI controller to add a new pizza order with the specified completion time
            uiController.addPizzaOrder(completionTime);
            // Update the UI to reflect the newly added order
            rightPanel.updateSelectedPizzaList(uiController.getPizzaList());
            // Show a confirmation message and close the dialog
            JOptionPane.showMessageDialog(this,"Ordre tilføjet med succes!");
            this.dispose(); // close the dialog window after action
        });
    }
}
