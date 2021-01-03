package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facades.Authentication;
import facades.Database;

public class SalesForm extends JInternalFrame implements ActionListener, MouseListener {

    String clothingId;
    JTable MasterTable;
    JTable CartTable;
    JButton AddButton, UpdateButton, DeleteButton, CheckOutButton;
    JPanel northPanel, southPanel, mainPanel;
    Vector<Object> tHeader;
    DefaultTableModel dtm, dtm2;
    JScrollPane scroll;
    JSpinner QtySpinner = new JSpinner();
    int row;
    private boolean clothDataSelected = false;
    private boolean cartDataSelected = false;

    public SalesForm() {
        // main Panel
        mainPanel = new JPanel();
        mainPanel.setSize(1200, 750);
        mainPanel.setLayout(new BorderLayout());

        // north Panel
        northPanel = new JPanel();
        northPanel.setSize(600, 750);
        northPanel.setLayout(new BorderLayout());
        northPanel.setVisible(true);

        // left north panel
        JLabel clothing = new JLabel("Clothing");

        // table master
        tHeader = new Vector<>();
        tHeader.add("ID");
        tHeader.add("Name");
        tHeader.add("Type");
        tHeader.add("Size");
        tHeader.add("Price");
        tHeader.add("Stock");
        dtm = new DefaultTableModel(tHeader, 0);
        MasterTable = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        MasterTable.addMouseListener(this);

        scroll = new JScrollPane(MasterTable);
        scroll.setSize(500, 300);

        // right north panel
        JLabel cart = new JLabel("Cart");

        // table cart
        tHeader = new Vector<>();
        tHeader.add("ID");
        tHeader.add("Name");
        tHeader.add("Type");
        tHeader.add("Size");
        tHeader.add("Price");
        tHeader.add("Quantity");
        dtm2 = new DefaultTableModel(tHeader, 0);
        CartTable = new JTable(dtm2) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        CartTable.addMouseListener(this);
        scroll = new JScrollPane(CartTable);
        scroll.setSize(500, 300);

        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        labelPanel.add(clothing);
        labelPanel.add(cart);

        JPanel tablePanel = new JPanel(new GridLayout(1, 2));
        tablePanel.add(new JScrollPane(MasterTable));
        tablePanel.add(new JScrollPane(CartTable));

        northPanel.add(labelPanel, BorderLayout.NORTH);
        northPanel.add(tablePanel, BorderLayout.CENTER);

        // south Panel
        southPanel = new JPanel();
        southPanel.setSize(400, 250);
        southPanel.setLayout(new GridLayout(3, 3, 0, 0));

        // up southPanel
        JPanel upSouthPanel = new JPanel();
        JLabel quantity = new JLabel("Quantity");
        QtySpinner.setPreferredSize(new Dimension(100, 20));
        upSouthPanel.add(quantity);
        upSouthPanel.add(QtySpinner);

        southPanel.add(new JPanel());
        southPanel.add(upSouthPanel);
        southPanel.add(new JPanel());

        // down southPanel
        JPanel downSouthPanel = new JPanel();
        downSouthPanel.setLayout(new GridLayout(1, 3, 16, 0));
        AddButton = new JButton("Add");
        UpdateButton = new JButton("Update");
        DeleteButton = new JButton("Delete");
        downSouthPanel.add(AddButton);
        downSouthPanel.add(UpdateButton);
        downSouthPanel.add(DeleteButton);

        southPanel.add(new JPanel());
        southPanel.add(downSouthPanel);
        southPanel.add(new JPanel());

        JPanel bottomSouthPanel = new JPanel();
        CheckOutButton = new JButton("Check Out");
        bottomSouthPanel.setLayout(new GridLayout(1, 1));
        bottomSouthPanel.add(CheckOutButton);

        southPanel.add(new JPanel());
        southPanel.add(bottomSouthPanel);
        southPanel.add(new JPanel());

        AddButton.addActionListener(this);
        UpdateButton.addActionListener(this);
        DeleteButton.addActionListener(this);
        CheckOutButton.addActionListener(this);

        queryClothingData();

        setTitle(title);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        mainPanel.add(northPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setSize(1366, 768);
        setTitle("Sales Form");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // setMaximizable(false);
    }

    private void queryClothingData() {
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        while (dtm2.getRowCount() > 0) {
            dtm2.removeRow(0);
        }

        try {
            ResultSet rs = getClothingData();
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                Vector<String> data = new Vector<>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    String str = rs.getObject(i).toString();
                    data.add(str);
                }
                dtm.addRow(data);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private ResultSet getClothingData() {
        String query = "SELECT `ClothingId`, `ClothingName`, `ClothingTypeName`, `ClothingSizeName`, `ClothingPrice`, `ClothingStock` FROM `clothing` a JOIN `ClothingType` b ON a.`ClothingTypeId` = b.`ClothingTypeId` JOIN `ClothingSize` c ON a.`ClothingSizeId` = c.`ClothingSizeId`";
        return Database.getInstance().executeQuery(query);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddButton) {
            if (!clothDataSelected) {
                JOptionPane.showMessageDialog(this, "Please select item to be inserted", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                int valueSpinner = (int) QtySpinner.getValue();
                if (valueSpinner <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be zero or lower!", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        String dos = "Insert";
                        clothingId = (String) MasterTable.getModel().getValueAt(row, 0);
                        String query = "SELECT ClothingId, ClothingName, ClothingTypeName, ClothingSizeName, ClothingPrice, ClothingStock FROM clothing a JOIN clothingtype b ON a.ClothingTypeId = b.ClothingTypeId JOIN clothingsize c ON a.ClothingSizeId = c.ClothingSizeId WHERE ClothingId = '%s'";
                        query = String.format(query, clothingId);
                        ResultSet rs = Database.getInstance().executeQuery(query);
                        rs.next();
                        String clothId = rs.getString("ClothingId");
                        String clothName = rs.getString("ClothingName");
                        String clothType = rs.getString("ClothingTypeName");
                        String clothSize = rs.getString("ClothingSizeName");
                        String clothPrice = rs.getString("ClothingPrice");

                        try {
                            QtySpinner.commitEdit();
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
                        int quantityTemp = (Integer) QtySpinner.getValue();
                        String quantity = Integer.toString(quantityTemp);
                        int row = 0;
                        for (int i = 0; i < CartTable.getRowCount(); i++) {
                            String value = (String) CartTable.getValueAt(i, 0);
                            if (clothId.equals(value)) {
                                row = i;
                                dos = "Update";
                                break;
                            }
                        }
                        Vector<String> data = new Vector<>();
                        data.add(clothId);
                        data.add(clothName);
                        data.add(clothType);
                        data.add(clothSize);
                        data.add(clothPrice);
                        data.add(quantity);
                        if (dos.equals("Insert")) {
                            dtm2.addRow(data);
                        } else {
                            int qtyTemp = Integer.parseInt((String) dtm2.getValueAt(row, 5))
                                    + Integer.parseInt(data.get(5));
                            String qtyString = Integer.toString(qtyTemp);
                            dtm2.setValueAt(qtyString, row, 5);
                        }
                        CartTable.setModel(dtm2);
                        JOptionPane.showMessageDialog(this, "Item Added!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        QtySpinner.setValue(0);
                        CartTable.getSelectionModel().clearSelection();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } else if (e.getSource() == UpdateButton) {
            if (!cartDataSelected) {
                JOptionPane.showMessageDialog(this, "Please select item to be updated", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                int valueSpinner = (int) QtySpinner.getValue();
                if (valueSpinner <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be zero or lower!", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // update Query
                    try {
                        QtySpinner.commitEdit();
                    } catch (java.text.ParseException e1) {
                        e1.printStackTrace();
                    }
                    int quantityTemp = (Integer) QtySpinner.getValue();
                    String quantity = Integer.toString(quantityTemp);
                    CartTable.getModel().setValueAt(quantity, row, 5);
                    CartTable.setModel(dtm2);
                    JOptionPane.showMessageDialog(this, "Item Updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    QtySpinner.setValue(0);
                    CartTable.getSelectionModel().clearSelection();
                    clothDataSelected = false;
                }
            }
        } else if (e.getSource() == DeleteButton) {
            if (!cartDataSelected) {
                JOptionPane.showMessageDialog(this, "Please select item to be deleted", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                dtm2.removeRow(row);
                CartTable.setModel(dtm2);
                JOptionPane.showMessageDialog(this, "Item Removed!", "Message", JOptionPane.INFORMATION_MESSAGE);
                QtySpinner.setValue(0);
                CartTable.getSelectionModel().clearSelection();
                cartDataSelected = false;
            }
        } else if (e.getSource() == CheckOutButton) {
            if (dtm2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Empty Cart!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {

                    String query = "INSERT INTO `SaleHeader` (`CustomerId`, `SaleDate`) VALUES (?, CURRENT_DATE)";
                    PreparedStatement stmt = Database.getInstance().prepareStatement(query);
                    stmt.setInt(1, Authentication.getUser().getUserId());
                    stmt.executeUpdate();

                    try (ResultSet generatedKey = stmt.getGeneratedKeys()) {
                        generatedKey.next();
                        Long SaleId = generatedKey.getLong(1);

                        Vector<String> cloth = new Vector<>();
                        Vector<String> CartQuantity = new Vector<>();
                        try {
                            for (int i = 0; i < dtm2.getRowCount(); i++) {
                                String CartValue = (String) dtm2.getValueAt(i, 0);
                                cloth.add(CartValue);
                                String QuantityValue = (String) dtm2.getValueAt(i, 5);
                                CartQuantity.add(QuantityValue);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }

                        for (int i = 0; i < cloth.size(); i++) {
                            String clothingId = cloth.get(i);
                            String qty = CartQuantity.get(i);
                            String query3 = "INSERT INTO `SaleDetail` (`SaleId`, `ClothingId`, `SaleQuantity`) VALUES ('%s','%s','%s')";
                            query3 = String.format(query3, SaleId, clothingId, qty);
                            Database.getInstance().executeUpdate(query3);
                            String sql = "UPDATE Clothing SET ClothingStock = ClothingStock - ? WHERE ClothingId = ?";
                            stmt = Database.getInstance().prepareStatement(sql);
                            stmt.setInt(1, Integer.parseInt(qty));
                            stmt.setInt(2, Integer.parseInt(clothingId));
                            stmt.executeUpdate();
                        }
                    } catch (Exception e0) {
                        e0.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(this, "Transaction Success!");
                    queryClothingData();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == MasterTable) {
            try {
                CartTable.clearSelection();
                clothDataSelected = true;
                cartDataSelected = false;
                row = MasterTable.getSelectedRow();
            } catch (Exception error) {
                error.printStackTrace();
            }
        } else if (e.getSource() == CartTable) {
            try {
                MasterTable.clearSelection();
                clothDataSelected = false;
                cartDataSelected = true;
                row = CartTable.getSelectedRow();
                String quantity = CartTable.getModel().getValueAt(row, 5).toString();
                QtySpinner.setValue(Integer.valueOf(quantity));
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
