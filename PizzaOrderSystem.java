import PizzaApp.UiController;
import PizzaUI.WindowFrame;
import com.formdev.flatlaf.*;
import javax.swing.*;

/**
 * Main class representing the pizza ordering system
 * Acting as interface between the logic and the UI
 */
public class PizzaOrderSystem {
    public static void main(String[] args) {
        // making an instance of the UiController
        UiController uiController = new UiController();
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> {
            WindowFrame mainFrame = new WindowFrame(uiController);
            mainFrame.setVisible(true);
        });
    }
}
