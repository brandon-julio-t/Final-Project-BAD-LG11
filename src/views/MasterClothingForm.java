package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Clothing;

public class MasterClothingForm extends JInternalFrame implements ActionListener {

	private JPanel mainPnl;
	private JPanel panelSouth;
	private JPanel panelJudul;
	private JPanel panelTabel;
	private JPanel panelName;
	private JPanel panelClothingTypeList;
	private JPanel panelSizeList;
	private JPanel panelQuantity;
	private JPanel panelPrice;
	private JPanel panelButton;

	private JLabel clothingForm;
	private JLabel nameLbl;
	private JLabel clothingtypeLbl;
	private JLabel clothingsizeLbl;
	private JLabel clothingpriceLbl;
	private JLabel quantityLbl;

	private JTextField nameField;
	private JTextField priceField;
	private JSpinner quantityField;
	private JComboBox<String> clothingTypeList;
	private JComboBox<String> sizeList;

	private JButton add;
	private JButton update;
	private JButton delete;

	private JTable table;
	private Vector<String> master;
	private DefaultTableModel dtm;
	private JScrollPane jsp;

	private Vector<Clothing> products;
	private int selectedId = -1;

	public MasterClothingForm() {
		setLayout(new BorderLayout());

		nameLbl = new JLabel("Name");
		clothingtypeLbl = new JLabel("Clothing Type");
		clothingsizeLbl = new JLabel("Size List");
		clothingpriceLbl = new JLabel("Clothing Price");
		quantityLbl = new JLabel("Quantity");

		nameField = new JTextField();
		clothingTypeList = new JComboBox<String>();
		sizeList = new JComboBox<String>();
		priceField = new JTextField();
		quantityField = new JSpinner();

		add = new JButton("Add");
		update = new JButton("Update");
		delete = new JButton("Delete");

		// Main Panel
		panelTabel = new JPanel();
		panelName = new JPanel();
		panelClothingTypeList = new JPanel();
		panelSizeList = new JPanel();
		panelPrice = new JPanel();
		panelQuantity = new JPanel();
		panelButton = new JPanel();

		// North
		panelJudul = new JPanel();
		panelJudul.setLayout(new FlowLayout());
		clothingForm = new JLabel("CLOTHING FORM");
		panelJudul.add(clothingForm);
		add(panelJudul, BorderLayout.NORTH);

		// Center
		panelTabel.setLayout(new GridLayout(1, 0));
		panelTabel.setBorder(new EmptyBorder(0, 10, 0, 10));

		Vector<Object> master;
		master = new Vector<Object>();
		master.add("ID");
		master.add("Size");
		master.add("Type");
		master.add("Name");
		master.add("Price");
		master.add("Quantity");

		dtm = new DefaultTableModel(master, 0);
		refreshTable();
		table = new JTable(dtm);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				selectedId = products.get(row).getClothingId();
				nameField.setText(products.get(row).getClothingName());
				clothingTypeList.setSelectedIndex(products.get(row).getClothingTypeId() - 1);
				sizeList.setSelectedIndex(products.get(row).getClothingSizeId() - 1);
				priceField.setText(products.get(row).getClothingPrice() + "");
				quantityField.setValue(products.get(row).getClothingStock());
			}
		});
		jsp = new JScrollPane(table);

		panelTabel.add(jsp);
		add(panelTabel, BorderLayout.CENTER);
		// South
		panelSouth = new JPanel();
		panelSouth.setLayout(new GridLayout(6, 0));
		panelSouth.setBorder(new EmptyBorder(10, 0, 10, 0));

		// Name
		panelName.setLayout(new GridLayout(2, 1));
		panelName.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelName.add(nameLbl);
		panelName.add(nameField);
		panelSouth.add(panelName);

		// ClothingType List
		panelClothingTypeList.setLayout(new GridLayout(2, 1));
		panelClothingTypeList.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelClothingTypeList.add(clothingtypeLbl);
		panelClothingTypeList.add(clothingTypeList);
		clothingTypeList.addItem("Hoodie");
		clothingTypeList.addItem("Jacket");
		clothingTypeList.addItem("Jeans");
		clothingTypeList.addItem("Pants");
		clothingTypeList.addItem("Shirt");
		clothingTypeList.addItem("Sweetheart");
		clothingTypeList.addItem("T-Shirt");
		clothingTypeList.addItem("Trousers");
		panelSouth.add(panelClothingTypeList);

		// Size List
		panelSizeList.setLayout(new GridLayout(2, 1));
		panelSizeList.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelSizeList.add(clothingsizeLbl);
		panelSizeList.add(sizeList);
		sizeList.addItem("XS");
		sizeList.addItem("S");
		sizeList.addItem("M");
		sizeList.addItem("L");
		sizeList.addItem("XL");
		sizeList.addItem("XXL");
		panelSouth.add(panelSizeList);

		// Price
		panelPrice.setLayout(new GridLayout(2, 1));
		panelPrice.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelPrice.add(clothingpriceLbl);
		panelPrice.add(priceField);
		panelSouth.add(panelPrice);

		// Quantity
		panelQuantity.setLayout(new GridLayout(2, 1));
		panelQuantity.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelQuantity.add(quantityLbl);
		panelQuantity.add(quantityField);
		panelSouth.add(panelQuantity);

		// Button
		panelButton.add(new JPanel());
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButton.add(add);

		add.addActionListener(this);
		panelButton.add(update);
		update.addActionListener(this);
		panelButton.add(delete);
		delete.addActionListener(this);
		panelButton.add(new JPanel());
		panelSouth.add(panelButton);
		add(panelSouth, BorderLayout.SOUTH);

		setTitle("Clothing Form");
		setSize(350, 500);
		setResizable(false);
		setVisible(true);

	}

	private boolean validateData() {

		String strnameField = nameField.getText();

		// name
		if (strnameField.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input the name");
			nameField.requestFocusInWindow();
			return false;
		} else if (strnameField.length() < 5) {
			JOptionPane.showMessageDialog(null, "Name length minimal 5");
			nameField.requestFocusInWindow();
			return false;
		}

		// price
		if (priceField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please input the price");
			priceField.requestFocusInWindow();
			return false;
		} else if (!priceField.getText().endsWith("000")) {
			JOptionPane.showMessageDialog(null, "Price must ends with 000");
			priceField.requestFocusInWindow();
			return false;
		} else {
			int intpriceField = Integer.parseInt(priceField.getText());
			if (intpriceField <= 0) {
				JOptionPane.showMessageDialog(null, "Price must be at least 1");
				priceField.requestFocusInWindow();
				return false;
			}
		}

		// quantity
		int intquantityField = (Integer) (quantityField.getValue());
		if (intquantityField <= 0) {
			JOptionPane.showMessageDialog(null, "Quantity must at least 1");
			quantityField.requestFocusInWindow();
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ((e.getSource() == delete || e.getSource() == update) && selectedId == -1) {
			JOptionPane.showMessageDialog(null, "You have not select any row from table");
			return;
		}

		if (e.getSource() == delete) {
			Clothing.deleteProduct(selectedId);
			JOptionPane.showMessageDialog(null, "Delete Data Success");
		} else {
			if (!validateData())
				return;

			String strnameField = nameField.getText();
			int intclothingtypeField = clothingTypeList.getSelectedIndex() + 1;
			int intsizeField = sizeList.getSelectedIndex() + 1;
			int intpriceField = Integer.parseInt(priceField.getText());
			int intquantityField = (Integer) quantityField.getValue();

			if (e.getSource() == add) {
				Clothing.insertProduct(strnameField, intclothingtypeField, intsizeField, intpriceField,
						intquantityField);
				JOptionPane.showMessageDialog(null, "Register Data Success");
			} else if (e.getSource() == update) {
				JOptionPane.showMessageDialog(null, "Update Data Success");
				Clothing.updateProduct(selectedId, strnameField, intclothingtypeField, intsizeField, intpriceField,
						intquantityField);
			}
		}

		selectedId = -1;
		refreshTable();
		initView();
	}

	private void initView() {
		nameField.setText("");
		clothingTypeList.setSelectedIndex(0);
		sizeList.setSelectedIndex(0);
		priceField.setText("");
		quantityField.setValue(0);
	}

	private void refreshTable() {
		Vector<Object> tableData;
		dtm.setRowCount(0);

		products = Clothing.getAll();
		for (Clothing product : products) {
			tableData = new Vector<>();
			tableData.add(product.getClothingId());
			tableData.add(product.getClothingSizeId());
			tableData.add(product.getClothingTypeId());
			tableData.add(product.getClothingName());
			tableData.add(product.getClothingPrice());
			tableData.add(product.getClothingStock());

			dtm.addRow(tableData);
		}
	}

}
