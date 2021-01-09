package commands;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import interfaces.Command;
import models.Clothing;
import repositories.ClothingTypeRepository;
import views.MainForm;
import views.ReviewForm;

public class ReviewCommand implements Command {
    private final JTable clothingTable;
    private final JInternalFrame parent;

    public ReviewCommand(JTable clothingTable, JInternalFrame parent) {
        this.clothingTable = clothingTable;
        this.parent = parent;
    }

    @Override
    public void execute() {
        int row = clothingTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row first!");
            return;
        }

        int id = Integer.parseInt(String.valueOf(clothingTable.getValueAt(row, 0)));
        int quantity = Integer.parseInt(String.valueOf(clothingTable.getValueAt(row, 4)));

        Clothing.getAll()
                .stream()
                .filter(clothing -> clothing.getClothingId() == id)
                .findFirst()
                .ifPresent(
                        clothing -> {
                            JInternalFrame frame = new ReviewForm(clothing, quantity, new ClothingTypeRepository());
                            MainForm.getInstance().addInternalFrame(frame);
                            parent.dispose();
                        }
                );
    }
}
