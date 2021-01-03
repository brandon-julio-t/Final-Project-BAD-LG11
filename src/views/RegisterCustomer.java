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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facades.Database;

public class RegisterCustomer extends JFrame implements ActionListener {

	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panelGender;

	private JLabel titleLabel;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JLabel phoneNoLabel;
	private JLabel addressLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;

	private JTextField nameText;
	private JTextField phoneNoText;
	private JTextField emailText;

	private JTextArea addressText;
	private JScrollPane addressScroll;

	private JPasswordField passwordText;

	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private ButtonGroup groupGender;

	private JButton submitBtn;
	private JButton resetBtn;

	public RegisterCustomer() {

		setLayout(new BorderLayout());

		// NORTH
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		titleLabel = new JLabel("Register Customer");
		panelNorth.add(titleLabel);
		add(panelNorth, "North");

		// CENTER
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(12, 1));
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
		emailLabel = new JLabel("Email");
		panelCenter.add(emailLabel);
		// Row10
		emailText = new JTextField();
		panelCenter.add(emailText);
		// Row11
		passwordLabel = new JLabel("Password");
		panelCenter.add(passwordLabel);
		// Row12
		passwordText = new JPasswordField();
		panelCenter.add(passwordText);

		add(panelCenter, "Center");

		// SOUTH
		panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

		resetBtn = new JButton("Reset");
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			String nameTemp = nameText.getText();
			String phoneNoTemp = phoneNoText.getText();
			String addressTemp = addressText.getText();
			String emailTemp = emailText.getText();
			String passwordTemp = new String(passwordText.getPassword());
			String genderTemp = "Male"; // (default)

			if (radioFemale.isSelected()) {
				genderTemp = "Female";
			}

			if (nameTemp.isEmpty() || phoneNoTemp.isEmpty() || addressTemp.isEmpty() || emailTemp.isEmpty()
					|| passwordTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All Fields must be filled!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;

			} else {
				String[] temp = emailTemp.split("@");
				// email validation
				if (emailTemp.startsWith("@") || emailTemp.endsWith("@")) {
					JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (!emailTemp.contains("@") || !(temp.length == 2)) {
					JOptionPane.showMessageDialog(this, "Wrong email format!", "Message",
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

				// password validation
				boolean containsAlpha = false;
				boolean containsNumeric = false;
				for (int i = 0; i < passwordTemp.length(); i++) {

					if (Character.isAlphabetic(passwordTemp.charAt(i))) {
						containsAlpha = true;
					} else if (Character.isDigit(passwordTemp.charAt(i))) {
						containsNumeric = true;
					}
				}
				if (containsAlpha == false || containsNumeric == false) {
					JOptionPane.showMessageDialog(this, "Password must be Alphanumeric!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				try {
					ResultSet rs = Database.getInstance()
							.executeQuery("SELECT * FROM User WHERE UserEmail = '" + emailTemp + "'");
					if (rs.next()) {
						JOptionPane.showMessageDialog(this, "Email already exists");
						return;
					}

					Database.getInstance().executeUpdate(
							"INSERT INTO `user`(`UserName`, `UserGender`, `UserPhoneNumber`, `UserAddress`, `UserEmail`, `UserPassword`) VALUES ('"
									+ nameTemp + "', '" + genderTemp + "', '" + phoneNoTemp + "', '" + addressTemp
									+ "', '" + emailTemp + "', '" + passwordTemp + "')");

					rs = Database.getInstance()
							.executeQuery("SELECT * FROM User WHERE UserEmail = '" + emailTemp + "'");
					rs.next();
					String UserId = rs.getString("UserId");
					Database.getInstance().executeUpdate(
							"INSERT INTO `customer`(`UserId`, `CustomerPoint`) VALUES ('" + UserId + "', 0)");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(this, "Register Success!", "Message", JOptionPane.INFORMATION_MESSAGE);
				nameText.setText("");
				phoneNoText.setText("");
				emailText.setText("");
				addressText.setText("");
				passwordText.setText("");
			}
		} else if (e.getSource() == resetBtn) {
			nameText.setText("");
			phoneNoText.setText("");
			emailText.setText("");
			addressText.setText("");
			passwordText.setText("");
		}
	}
}
