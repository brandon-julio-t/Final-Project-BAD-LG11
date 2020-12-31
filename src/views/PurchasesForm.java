package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PurchasesForm extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel mainPanel;
	private JPanel panelRow1;
	private JPanel panelRow2;
	private JPanel panelRow3;
	private JPanel panelRow4;

	private JLabel clothTitle;
	private JLabel cartTitle;
	private JLabel qtyLbl;
	private JLabel vendorLbl;

	private JTextField qtyField;
	private JComboBox<String> vendorList;

	private JButton addBtn;
	private JButton updateBtn;
	private JButton deleteBtn;

	private JTable tableCloth;
	private JTable tableCart;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private Vector<String> masterColumnNames;
	private Vector<String> cartColumnNames;

	public PurchasesForm() {
		super("Purchases Menu");

		clothTitle = new JLabel("Clothing List");
		cartTitle = new JLabel("Cart");
		qtyLbl = new JLabel("Quantity");
		vendorLbl = new JLabel("Vendor");

		qtyField = new JTextField();
		vendorList = new JComboBox<String>();

		addBtn = new JButton("ADD");
		updateBtn = new JButton("UPDATE");
		deleteBtn = new JButton("DELETE");

		// Main Panel
		mainPanel = new JPanel(new GridLayout(4, 1));

		// MAIN ROW 1
		panelRow1.setLayout(new GridLayout(2, 2));
		// Row 1
		panelRow1.add(clothTitle);
		panelRow1.add(cartTitle);
		// Row 2
		masterColumnNames = new Vector<String>();
		masterColumnNames.add("ID");
		masterColumnNames.add("Name");
		masterColumnNames.add("Type");
		masterColumnNames.add("Size");
		masterColumnNames.add("Quantity");

		dtm = new DefaultTableModel(masterColumnNames, 0);
		tableCloth = new JTable(dtm);
		jsp = new JScrollPane(tableCloth);

		panelRow1.add(jsp);

		cartColumnNames = new Vector<String>();
		cartColumnNames.add("ID");
		cartColumnNames.add("Name");
		cartColumnNames.add("Type");
		cartColumnNames.add("Size");
		cartColumnNames.add("Quantity");

		dtm = new DefaultTableModel(cartColumnNames, 0);
		tableCart = new JTable(dtm);
		jsp = new JScrollPane(tableCart);

		panelRow1.add(jsp);
		mainPanel.add(panelRow1);

		// MAIN ROW 2
		panelRow2.setLayout(new GridLayout(1, 3));
		// Column 1
		panelRow2.add(new JPanel());
		// Column 2
		panelRow2.setLayout(new GridLayout(2, 1));
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
		panelRow3.setLayout(new GridLayout(2, 1));
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

		add(mainPanel);

		// Frame
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setSize(2000, 2000);

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
