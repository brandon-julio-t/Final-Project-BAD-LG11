package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import facades.Authentication;
import facades.Database;
import interfaces.ReadonlyRepository;
import models.Clothing;
import models.ClothingSize;
import models.ClothingType;
import models.Vendor;

public class PurchasesForm extends JInternalFrame implements ActionListener, MouseListener {

    private final ReadonlyRepository<ClothingType> clothingTypeRepository;
    private final ReadonlyRepository<ClothingSize> clothingSizeRepository;

    private JPanel mainPanel;
    private JPanel panelRow1;
    private JPanel panelRow2;
    private JPanel panelRow3;
    private JPanel panelRow4;

    private JLabel clothTitle;
    private JLabel cartTitle;
    private JLabel qtyLbl;
    private JLabel vendorLbl;

    private JSpinner qtyField;
    private DefaultComboBoxModel<Vendor> vendorListModel;
    private JComboBox<Vendor> vendorList;

    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton checkoutBtn;

    private JTable tableCloth;
    private JTable tableCart;
    private DefaultTableModel dtm;
    private Vector<String> masterColumnNames;
    private Vector<String> cartColumnNames;

    public PurchasesForm(
            ReadonlyRepository<ClothingType> clothingTypeRepository,
            ReadonlyRepository<ClothingSize> clothingSizeRepository
    ) {
        super("Purchases Menu");
        this.clothingTypeRepository = clothingTypeRepository;
        this.clothingSizeRepository = clothingSizeRepository;

        clothTitle = new JLabel("Clothing List");
        cartTitle = new JLabel("Cart");
        qtyLbl = new JLabel("Quantity");
        vendorLbl = new JLabel("Vendor");

        qtyField = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        vendorListModel = new DefaultComboBoxModel<>(Vendor.getAll());
        vendorList = new JComboBox<>(vendorListModel);

        addBtn = new JButton("ADD");
        updateBtn = new JButton("UPDATE");
        deleteBtn = new JButton("DELETE");

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        panelRow1 = new JPanel();
        panelRow2 = new JPanel();
        panelRow3 = new JPanel();
        panelRow4 = new JPanel();

        // MAIN ROW 1
        panelRow1.setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        headerPanel.add(clothTitle);
        headerPanel.add(cartTitle);
        // Row 1
        panelRow1.add(headerPanel, BorderLayout.NORTH);
        // Row 2
        masterColumnNames = new Vector<>();
        masterColumnNames.add("ID");
        masterColumnNames.add("Name");
        masterColumnNames.add("Type");
        masterColumnNames.add("Size");
        masterColumnNames.add("Quantity");

        JPanel tablePanel = new JPanel(new GridLayout(1, 2));

        Vector<Vector<Object>> data = queryClothings();

        dtm = new DefaultTableModel(data, masterColumnNames);
        tableCloth = new JTable(dtm);
        tablePanel.add(new JScrollPane(tableCloth));

        cartColumnNames = new Vector<>();
        cartColumnNames.add("ID");
        cartColumnNames.add("Name");
        cartColumnNames.add("Type");
        cartColumnNames.add("Size");
        cartColumnNames.add("Quantity");

        dtm = new DefaultTableModel(cartColumnNames, 0);
        tableCart = new JTable(dtm);
        tablePanel.add(new JScrollPane(tableCart));

        panelRow1.add(tablePanel, BorderLayout.CENTER);

        mainPanel.add(panelRow1);

        // =============================================================================================================

        tableCart.getSelectionModel().addListSelectionListener(e -> {
            int row = tableCart.getSelectedRow();
            if (row == -1) {
                return;
            }

            Object quantity = tableCart.getValueAt(row, 4);
            qtyField.setValue(quantity);
        });

        // =============================================================================================================

        // MAIN ROW 2
        panelRow2.setLayout(new GridLayout(1, 3));
        // Column 1
        panelRow2.add(new JPanel());
        // Column 2
        panelRow2.setLayout(new GridLayout(1, 2));
        panelRow2.add(qtyLbl);
        panelRow2.add(qtyField);
        // Column 3
        panelRow2.add(new JPanel());
        mainPanel.add(panelRow2);

        // MAIN ROW 3
        panelRow3.setLayout(new GridLayout(1, 3));
        // Column 1
        panelRow3.add(new JPanel());
        // Column 2
        panelRow3.setLayout(new GridLayout(1, 2));
        panelRow3.add(vendorLbl);
        panelRow3.add(vendorList);
        // Column 3
        panelRow3.add(new JPanel());
        mainPanel.add(panelRow3);

        // MAIN ROW 4
        panelRow4.setLayout(new GridLayout(1, 3));
        // Column 1
        panelRow4.add(new JPanel());
        // Column 2
        panelRow4.setLayout(new GridLayout(1, 3));
        panelRow4.add(addBtn);
        panelRow4.add(updateBtn);
        panelRow4.add(deleteBtn);
        // Column 3
        panelRow4.add(new JPanel());
        mainPanel.add(panelRow4);

        JPanel checkoutPanel = new JPanel(new GridLayout(1, 3));
        checkoutPanel.add(new JLabel());
        checkoutPanel.add(checkoutBtn = new JButton("Checkout"));
        checkoutPanel.add(new JLabel());
        mainPanel.add(checkoutPanel);

        add(mainPanel);

        assignButtonsAction();

        setTitle(title);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        // Frame
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setSize(1366, 768);
        setVisible(true);
    }

    private Vector<Vector<Object>> queryClothings() {
        Vector<Clothing> clothings = Clothing.getAll();
        Vector<Vector<Object>> data = new Vector<>();

        clothings.forEach(clothing -> {
            int clothingTypeId = clothing.getClothingTypeId();
            int clothingSizeId = clothing.getClothingSizeId();

            Optional<ClothingType> clothingTypeOptional = clothingTypeRepository.getById(clothingTypeId);
            Optional<ClothingSize> clothingSizeOptional = clothingSizeRepository.getById(clothingSizeId);

            if (!clothingTypeOptional.isPresent() || !clothingSizeOptional.isPresent()) {
                return;
            }

            ClothingType clothingType = clothingTypeOptional.get();
            ClothingSize clothingSize = clothingSizeOptional.get();

            Vector<Object> row = new Vector<>();
            row.add(clothing.getClothingId());
            row.add(clothing.getClothingName());
            row.add(clothingType);
            row.add(clothingSize);
            row.add(clothing.getClothingStock());
            data.add(row);
        });

        return data;
    }

    private void assignButtonsAction() {
        addBtn.addActionListener(e -> {
            int row = tableCloth.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row in clothing table first!");
                return;
            }

            DefaultTableModel tableClothModel = (DefaultTableModel) tableCloth.getModel();

            Object id = tableClothModel.getValueAt(row, 0);
            Object name = tableClothModel.getValueAt(row, 1);
            Object type = tableClothModel.getValueAt(row, 2);
            Object size = tableClothModel.getValueAt(row, 3);

            Integer purchaseQuantity = Integer.parseInt(String.valueOf(qtyField.getValue()));

            DefaultTableModel tableCartModel = (DefaultTableModel) tableCart.getModel();

            Vector<Object> data = new Vector<>();
            data.add(id);
            data.add(name);
            data.add(type);
            data.add(size);
            data.add(purchaseQuantity);
            tableCartModel.addRow(data);
        });

        updateBtn.addActionListener(e -> {
            int row = tableCart.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row in cart table first!");
                return;
            }

            Object quantity = qtyField.getValue();
            tableCart.setValueAt(quantity, row, 4);
        });

        deleteBtn.addActionListener(e -> {
            int row = tableCart.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row in cart table first!");
                return;
            }

            DefaultTableModel tableCartModel = (DefaultTableModel) tableCart.getModel();
            tableCartModel.removeRow(row);
        });

        checkoutBtn.addActionListener(e -> {
            DefaultTableModel tableModel = (DefaultTableModel) tableCart.getModel();
            Vector purchaseData = tableModel.getDataVector();

            Database.getInstance().beginTransaction();

            try {
                PreparedStatement stmt;
                String sql;

                sql = "insert into `PurchaseHeader` (`StaffId`, `VendorId`, `PurchaseDate`) values (?, ?, CURRENT_DATE)";
                stmt = Database.getInstance().prepareStatement(sql);
                stmt.setInt(1, Authentication.getUser().getUserId());
                stmt.setInt(2, ((Vendor) vendorListModel.getSelectedItem()).getVendorId());
                stmt.executeUpdate();

                sql = "select `PurchaseId` from `PurchaseHeader` order by `PurchaseId` desc limit 1";
                ResultSet rs = Database.getInstance().executeQuery(sql);
                rs.next();
                Integer purchaseId = rs.getInt("PurchaseId");

                for (Object data : purchaseData) {
                    Vector dataVector = (Vector) data;

                    int clothingId = Integer.parseInt(String.valueOf(dataVector.get(0)));
                    int quantity = Integer.parseInt(String.valueOf(dataVector.get(4)));

                    sql = "insert into `PurchaseDetail` (PurchaseId, ClothingId, PurchaseQuantity) values (?, ?, ?)";
                    stmt = Database.getInstance().prepareStatement(sql);
                    stmt.setInt(1, purchaseId);
                    stmt.setInt(2, clothingId);
                    stmt.setInt(3, quantity);
                    stmt.executeUpdate();

                    sql = "update `Clothing` set `ClothingStock` = `ClothingStock` + ? where `ClothingId` = ?";
                    stmt = Database.getInstance().prepareStatement(sql);
                    stmt.setInt(1, quantity);
                    stmt.setInt(2, clothingId);
                    stmt.executeUpdate();
                }

                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }

                JOptionPane.showMessageDialog(null, "Transaction Success!");

                DefaultTableModel tableClothModel = (DefaultTableModel) tableCloth.getModel();
                tableClothModel.setDataVector(queryClothings(), masterColumnNames);

                Database.getInstance().commit();
            } catch (SQLException err) {
                Database.getInstance().rollback();
                err.printStackTrace();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
