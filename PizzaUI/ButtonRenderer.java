package PizzaUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * A custom renderer for displaying JButton components within table cells.
 * It configures the button's appearance and sets its text based on the cell value.
 * @author Kenneth Heimann
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    /**
     * Constructor to make the button opaque, ensuring visual consistency in the table.
     */
    public ButtonRenderer() {
        setOpaque(true);
    }

    // Overrides TableCellRenderer's method to configure the appearance of the button in a cell.
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Set the text of the button to the cell's value, or leave it empty if the value is null.
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

