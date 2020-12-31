package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facades.Database;

public class RegisterStaff extends JFrame implements ActionListener {

	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panelGender;

	private JLabel titleLabel;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JLabel phoneNoLabel;
	private JLabel addressLabel;
	private JLabel positionLabel;
	private JLabel NPWPLabel;
	private JLabel salaryLabel;

	private JTextArea addressText;
	private JScrollPane addressScroll;

	private JTextField nameText;
	private JTextField phoneNoText;
	private JTextField NPWPText;
	private JTextField salaryText;

	private JComboBox<String> positionCB;

	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private ButtonGroup groupGender;

	private JButton submitBtn;
	private JButton resetBtn;

	public RegisterStaff() {

		setLayout(new BorderLayout());

		// NORTH
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		titleLabel = new JLabel("Register Staff");
		panelNorth.add(titleLabel);
		add(panelNorth, "North");

		// CENTER
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(14, 1));
		panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Row1
		nameLabel = new JLabel("Name");
		panelCenter.add(nameLabel);

		// Row2
		nameText = new JTextField();
		panelCenter.add(nameText);

		// Row3
		genderLabel = new JLabel("Gender");
		panelCenter.add(genderLabel);

		// Row 4
		panelGender = new JPanel();
		panelGender.setLayout(new GridLayout(1, 2));

		radioMale = new JRadioButton("Male");
		radioFemale = new JRadioButton("Female");

		groupGender = new ButtonGroup();
		groupGender.add(radioMale);
		groupGender.add(radioFemale);

		panelGender.add(radioMale);
		panelGender.add(radioFemale);
		panelCenter.add(panelGender);

		radioMale.setSelected(true);

		// Row5
		phoneNoLabel = new JLabel("Phone Number");
		panelCenter.add(phoneNoLabel);
		// Row6
		phoneNoText = new JTextField();
		panelCenter.add(phoneNoText);
		// Row7
		addressLabel = new JLabel("Address");
		panelCenter.add(addressLabel);
		// Row8
		addressText = new JTextArea();
		addressScroll = new JScrollPane(addressText);
		panelCenter.add(addressScroll);
		// Row9
		positionLabel = new JLabel("Position");
		panelCenter.add(positionLabel);
		// Row10
		positionCB = new JComboBox<String>();
		positionCB.addItem("Admin");
		positionCB.addItem("Staff");
		panelCenter.add(positionCB);
		//
		salaryLabel = new JLabel("Salary");
		panelCenter.add(salaryLabel);
		//
		salaryText = new JTextField();
		panelCenter.add(salaryText);
		// Row11
		NPWPLabel = new JLabel("NPWP");
		panelCenter.add(NPWPLabel);
		// Row12
		NPWPText = new JTextField();
		panelCenter.add(NPWPText);

		add(panelCenter, "Center");

		// SOUTH
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

		resetBtn = new JButton("Cancel");
		panelSouth.add(resetBtn);

		submitBtn = new JButton("Submit");
		panelSouth.add(submitBtn);

		submitBtn.addActionListener(this);
		resetBtn.addActionListener(this);

		add(panelSouth, "South");

		setTitle("Register Form");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			String nameTemp = nameText.getText();
			String phoneNoTemp = phoneNoText.getText();
			String addressTemp = addressText.getText();
			String NPWPTemp = NPWPText.getText();
			// [jet, armstrong, cycle, cyclone] 3
			String[] nameSplit = nameTemp.split(" ");
			String firstName = nameSplit[0];
			String lastName = nameSplit[nameSplit.length - 1];
			String emailTemp = String.format("%s.%s@bad.com", firstName, lastName);
			String passwordTemp = String.format("%s123", firstName);
			String genderTemp = "Male"; // (default)
			String salaryTemp = salaryText.getText();
			String positionTemp = "1";

			if (radioFemale.isSelected()) {
				genderTemp = "Female";
			}

			if (positionCB.getSelectedItem().equals("Staff")) {
				positionTemp = "2";
			}

			if (nameTemp.isEmpty() || phoneNoTemp.isEmpty() || addressTemp.isEmpty() || NPWPTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			boolean phoneNoIsNumeric = false;
			for (int i = 0; i < phoneNoTemp.length(); i++) {
				if (Character.isDigit(phoneNoTemp.charAt(i))) {
					phoneNoIsNumeric = true;
				} else if (Character.isAlphabetic(phoneNoTemp.charAt(i))) {
					phoneNoIsNumeric = false;
				}
			}

			if (!phoneNoIsNumeric) {
				JOptionPane.showMessageDialog(this, "Phone Number must be numeric!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!addressTemp.startsWith("Jl. ")) {
				JOptionPane.showMessageDialog(this, "Address must start with Jl. !", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			boolean salaryIsNumeric = false;
			for (int i = 0; i < salaryTemp.length(); i++) {
				if (Character.isDigit(salaryTemp.charAt(i))) {
					salaryIsNumeric = true;
				} else if (Character.isAlphabetic(salaryTemp.charAt(i))) {
					salaryIsNumeric = false;
				}
			}

			if (!salaryIsNumeric) {
				JOptionPane.showMessageDialog(this, "Salary must be numeric!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			boolean NPWPIsNumeric = false;
			for (int i = 0; i < NPWPTemp.length(); i++) {
				if (Character.isDigit(NPWPTemp.charAt(i))) {
					NPWPIsNumeric = true;
				} else if (Character.isAlphabetic(phoneNoTemp.charAt(i))) {
					NPWPIsNumeric = false;
				}
			}

			if (!NPWPIsNumeric) {
				JOptionPane.showMessageDialog(this, "NPWP must be numeric!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (NPWPTemp.length() != 15) {
				JOptionPane.showMessageDialog(this, "NPWP length must be 15 digit!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			try {
				Database.getInstance().executeUpdate(
						"INSERT INTO `user`(`UserName`, `UserGender`, `UserPhoneNumber`, `UserAddress`, `UserEmail`, `UserPassword`) VALUES ('"
								+ nameTemp + "', '" + genderTemp + "', '" + phoneNoTemp + "', '" + addressTemp + "', '"
								+ emailTemp + "', '" + passwordTemp + "')");

				ResultSet rs = Database.getInstance()
						.executeQuery("SELECT * FROM User WHERE UserEmail = '" + emailTemp + "'");
				rs.next();
				String UserId = rs.getString("UserId");
				Database.getInstance().executeUpdate(
						"INSERT INTO `staff`(`userId`, `StaffPositionId`, `StaffSalary`, `NPWP`) VALUES ('" + UserId
								+ "', '" + positionTemp + "', '" + salaryTemp + "', '" + NPWPTemp + "')");

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(this, "Register Success!", "Message", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == resetBtn) {
			nameText.setText("");
			phoneNoText.setText("");
			addressText.setText("");
			NPWPText.setText("");
			salaryText.setText("");
		}
	}
}
