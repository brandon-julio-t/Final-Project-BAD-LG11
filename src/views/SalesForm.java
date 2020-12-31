package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class SalesForm extends JFrame implements ActionListener, MouseListener {

	SalesForm salesform;
	JTable MasterTable, CartTable;
	JTextField ItemQty;
	JButton Add, Update, Delete;
	JPanel northPanel, southPanel, rightPanel, mainPanel;
	Vector<Object> tHeader, tRow;
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	public SalesForm () {
		setSize(1200,750);
		setTitle("Sales Form");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setMaximizeable(false);
		
		//main Panel
		mainPanel = new JPanel(); 
		mainPanel.setSize(1200,750);
		mainPanel.setLayout(new GridLayout(2,1)); 
		
			//north Panel 
			northPanel = new JPanel(); 
			northPanel.setSize(600,750);
			northPanel.setLayout(new GridLayout(1,2));
			northPanel.setVisible(true);
			
				//left north panel 
				JPanel leftNorth = new JPanel();
				leftNorth.setLayout(new GridLayout(2,1,0,0));
				JLabel master = new JLabel("Master");
				
				leftNorth.setVisible(true);
				
				//table master
				tHeader = new Vector<>();
				tHeader.add("ID");
				tHeader.add("Name");
				tHeader.add("Type");
				tHeader.add("Quantity");
				dtm = new DefaultTableModel(tHeader,0);
				MasterTable = new JTable(dtm) {
					public boolean  isCellEditable(int row, int column) {
						return false;
					}	
				};
				scroll = new JScrollPane(MasterTable);
				scroll.setSize(500, 300);
				leftNorth.add(master);
				leftNorth.add(scroll);
				northPanel.add(leftNorth, BorderLayout.EAST);
				
				//right north panel 
				JPanel rightNorth = new JPanel(); 
				rightNorth.setLayout(new GridLayout(2,1,0,0));
				JLabel cart = new JLabel("Cart");
				
				//table cart
				tHeader = new Vector<>();
				tHeader.add("ID");
				tHeader.add("Name");
				tHeader.add("Type");
				tHeader.add("Quantity");
				dtm = new DefaultTableModel(tHeader,0);
				CartTable = new JTable(dtm) {
					public boolean  isCellEditable(int row, int column) {
						return false;
					}	
				};
				scroll = new JScrollPane(CartTable);
				scroll.setSize(500, 300);
				rightNorth.add(cart);
				rightNorth.add(scroll);
				northPanel.add(rightNorth, BorderLayout.EAST);
				
			//south Panel 
				southPanel = new JPanel(); 
				southPanel.setSize(400,250);
				southPanel.setLayout(new GridLayout(2,1));
				
					//up southPanel 
					JPanel upSouthPanel = new JPanel();
					JLabel quantity = new JLabel("Quantity");
					JSpinner qty = new JSpinner(); 
					upSouthPanel.add(quantity);
					upSouthPanel.add(qty);
					
					southPanel.add(upSouthPanel);
					//down southPanel 
					JPanel downSouthPanel = new JPanel(); 
					downSouthPanel.setLayout(new GridLayout(1,3,10,10));
					JButton add = new JButton("Add");
					JButton update = new JButton("Update");
					JButton delete = new JButton ("Delete");
					downSouthPanel.add(add);
					downSouthPanel.add(update);
					downSouthPanel.add(delete);
					
					southPanel.add(downSouthPanel);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}