package views;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import commands.ReviewCommand;
import core.AbstractInternalFrame;
import interfaces.ReadonlyRepository;
import models.SaleHistory;
import repositories.SaleHistoryRepository;

public class SaleHistoryView extends AbstractInternalFrame {
    private Vector<String> tableHeaders;
    private JTable clothingTable;

    public SaleHistoryView() {
        super("Sale History", new Dimension(1366, 768));
    }

    @Override
    protected JPanel buildUI() {
        tableHeaders = new Vector<>(Arrays.asList("ID", "Name", "Type", "Price", "Quantity", "Sub Total", "Date"));
        clothingTable = new JTable(new DefaultTableModel());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JButton reviewButton = new JButton("Review");
        reviewButton.addActionListener(e -> new ReviewCommand(clothingTable, this).execute());
        reviewButton.setAlignmentX(CENTER_ALIGNMENT);

        contentPane.add(new JScrollPane(clothingTable));
        contentPane.add(Box.createRigidArea(new Dimension(0, 16)));
        contentPane.add(reviewButton);

        queryData();
        return contentPane;
    }

    private void queryData() {
        ReadonlyRepository<SaleHistory> repository = new SaleHistoryRepository();

        Vector<Vector<Object>> data = new Vector<>();
        repository.getAll().forEach(saleHistory -> {
            Vector<Object> row = new Vector<>();
            row.add(saleHistory.getClothingId());
            row.add(saleHistory.getClothingName());
            row.add(saleHistory.getClothingType());
            row.add(saleHistory.getClothingPrice());
            row.add(saleHistory.getSaleQuantity());
            row.add(saleHistory.getSubTotal());
            row.add(saleHistory.getSaleDate());
            data.add(row);
        });

        ((DefaultTableModel) clothingTable.getModel()).setDataVector(data, tableHeaders);
    }
}
