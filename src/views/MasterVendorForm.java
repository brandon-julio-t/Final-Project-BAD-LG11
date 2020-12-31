package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Vendor;

public class MasterVendorForm extends JInternalFrame implements ActionListener {

	private JPanel mainPnl;
	private JPanel panelJudul;
	private JPanel panelTabel;
	private JPanel panelSouth;
	private JPanel panelName;
	private JPanel panelEmail;
	private JPanel panelAddress;
	private JPanel panelPhone;
	private JPanel panelButton;

	private JLabel vendorForm;
	private JLabel nameLbl;
	private JLabel emailLbl;
	private JLabel addressLbl;
	private JLabel phoneLbl;

	private JTextField nameField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField phoneField;

	private JButton add;
	private JButton update;
	private JButton delete;

	private JTable table;
	private Vector<String> master;
	private DefaultTableModel dtm;
	private JScrollPane jsp;

	private Vector<Vendor> vendors;
	private int selectedId = -1;

	public MasterVendorForm() {
		setLayout (new BorderLayout());

		nameLbl = new JLabel("Name");
		emailLbl = new JLabel("Email");
		addressLbl = new JLabel("Address");
		phoneLbl = new JLabel("Phone");

		nameField = new JTextField();
		emailField = new JTextField();
		addressField = new JTextField();
		phoneField = new JTextField();

		add = new JButton("Add");
		update = new JButton("Update");
		delete = new JButton("Delete");

		// Main Panel
		panelTabel = new JPanel();
		panelName = new JPanel();
		panelEmail = new JPanel();
		panelAddress = new JPanel();
		panelPhone = new JPanel();
		panelButton = new JPanel();

		//North
		panelJudul = new JPanel();
		panelJudul.setLayout(new FlowLayout());
		vendorForm = new JLabel ("VENDOR FORM");
		panelJudul.add(vendorForm);
		add (panelJudul, BorderLayout.NORTH);

		// Center
		panelTabel.setLayout(new GridLayout(1, 0));
		panelTabel.setBorder(new EmptyBorder(0, 10, 0, 10));

		Vector<Object> master;
		master = new Vector<Object>();
		master.add("ID");
		master.add("Name");
		master.add("Email");
		master.add("Address");
		master.add("Phone");

		dtm = new DefaultTableModel(master, 0);
		refreshTable();
		table = new JTable(dtm);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        selectedId = vendors.get(row).getVendorId();
		        nameField.setText(vendors.get(row).getVendorName());
		        emailField.setText(vendors.get(row).getVendorEmail());
		        addressField.setText(vendors.get(row).getVendorAddress());
		        phoneField.setText(vendors.get(row).getVendorPhoneNumber());
		    }
		});
		jsp = new JScrollPane(table);

		panelTabel.add(jsp);
		add(panelTabel, BorderLayout.CENTER);

		//South
		panelSouth = new JPanel();
		panelSouth.setLayout (new GridLayout(6,0));
		panelSouth.setBorder(new EmptyBorder(10, 0, 10, 0));

		//Name
				panelName.setLayout(new GridLayout(2,1));
				panelName.setBorder(new EmptyBorder(0, 20, 0, 20));
				panelName.add(nameLbl);
				panelName.add(nameField);
				panelSouth.add(panelName);

		//Email
				panelEmail.setLayout(new GridLayout(2,1));
				panelEmail.setBorder(new EmptyBorder(0, 20, 0, 20));
				panelEmail.add(emailLbl);
				panelEmail.add(emailField);
				panelSouth.add(panelEmail);

		//Address
				panelAddress.setLayout(new GridLayout(2,1));
				panelAddress.setBorder(new EmptyBorder(0, 20, 0, 20));
				panelAddress.add(addressLbl);
				panelAddress.add(addressField);
				panelSouth.add(panelAddress);

		//Phone
				panelPhone.setLayout(new GridLayout(2,1));
				panelPhone.setBorder(new EmptyBorder(0, 20, 0, 20));
				panelPhone.add(phoneLbl);
				panelPhone.add(phoneField);
				panelSouth.add(panelPhone);

		//Button
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

		setTitle(title);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);

		setTitle("Vendor Form");
		setSize(350, 500);
		setResizable(false);
		setVisible(true);

	}

private Boolean validateData() {

		String strnameField = nameField.getText();
		String stremailField = emailField.getText();
		String straddressField = addressField.getText();
		String strphoneField = phoneField.getText();

	// name
		if (strnameField.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input your name");
			nameField.requestFocusInWindow();
			return false;
		} else if (strnameField.length()<5) {
			JOptionPane.showMessageDialog(null, "Name length minimal 5");
			nameField.requestFocusInWindow();
			return false;
		}

	// email
		if (stremailField.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input your email");
			emailField.requestFocusInWindow();
			return false;
		} else if (stremailField.length()<5) {
			JOptionPane.showMessageDialog(null, "Email length minimal 5");
			emailField.requestFocusInWindow();
			return false;
		} else if (!stremailField.contains("@")) {
			JOptionPane.showMessageDialog(null, "Email must contains '@'");
			emailField.requestFocusInWindow();
			return false;
		}

	// Address
		if (straddressField.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input your address");
			addressField.requestFocusInWindow();
			return false;
		} else if (straddressField.length()<5) {
			JOptionPane.showMessageDialog(null, "Address length minimal 5");
			addressField.requestFocusInWindow();
			return false;
		}

	// Phone
		if (strphoneField.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input your phone");
			phoneField.requestFocusInWindow();
			return false;
		} else if (strphoneField.length()<10) {
			JOptionPane.showMessageDialog(null, "Phone number be at least 10 characters");
			phoneField.requestFocusInWindow();
			return false;
		}

		return  true;
	}

@Override
public void actionPerformed(ActionEvent e) {

	if ((e.getSource() == delete || e.getSource() == update) && selectedId == -1){
		JOptionPane.showMessageDialog(null, "You have not select any row from table");
		return;
	}

	if (e.getSource() == delete) {
		Vendor.deleteVendor(selectedId);
		JOptionPane.showMessageDialog(null, "Delete Data Success");
	} else {
		if (!validateData())  return;

		String strnameField = nameField.getText();
		String stremailField = emailField.getText();
		String straddressField = addressField.getText();
		String strphoneField = phoneField.getText();

		if (e.getSource() == add) {
			Vendor.insertVendor(strnameField, stremailField, straddressField, strphoneField);
			JOptionPane.showMessageDialog(null, "Register Data Success");
		} else if (e.getSource() == update) {
			JOptionPane.showMessageDialog(null, "Update Data Success");
			Vendor.updateVendor(selectedId,strnameField, stremailField, straddressField, strphoneField);
		}
	}

		selectedId = -1;
		refreshTable();
		initView();
	}

		private void initView() {
			nameField.setText("");
			emailField.setText("");
			addressField.setText("");
			phoneField.setText("");
		}

		private void refreshTable() {
			Vector<Object> tableData;
			dtm.setRowCount(0);

			vendors = Vendor.getAll();
			for (Vendor vendor : vendors) {
				tableData = new Vector<>();
				tableData.add(vendor.getVendorId());
				tableData.add(vendor.getVendorName());
				tableData.add(vendor.getVendorEmail());
				tableData.add(vendor.getVendorAddress());
				tableData.add(vendor.getVendorPhoneNumber());

				dtm.addRow(tableData);
			}

	}

}
