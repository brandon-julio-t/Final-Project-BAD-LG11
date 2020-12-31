package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facades.Database;

public class LoginForm extends JFrame implements ActionListener {
	JButton registerBtn;
	JButton loginBtn;
	JTextField emailTf;
	JPasswordField passTf;

	public void init_login() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// //atas
		JPanel topPanel = new JPanel();
		JLabel headinglbl = new JLabel("LOGIN");
		headinglbl.setBorder(new EmptyBorder(20, 0, 10, 0));
		topPanel.add(headinglbl);

		// tengah
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 1));
		centerPanel.setBorder(new EmptyBorder(0, 40, 10, 40));

		// Email
		JLabel emaillbl = new JLabel("Email");
		emailTf = new JTextField();
		centerPanel.add(emaillbl);
		centerPanel.add(emailTf);

		// Password
		JLabel passlbl = new JLabel("Password");
		passTf = new JPasswordField();
		centerPanel.add(passlbl);
		centerPanel.add(passTf);

		// bawah
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1, 0, 10));
		bottomPanel.setBorder(new EmptyBorder(10, 100, 20, 100));
		loginBtn = new JButton("   Login   ");
		loginBtn.addActionListener(this);
		bottomPanel.add(loginBtn);

		registerBtn = new JButton("   Register as Customer   ");
		bottomPanel.add(registerBtn);
		registerBtn.addActionListener(this);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		add(mainPanel);
		setSize(400, 350);
		setLocationRelativeTo(null);
		setTitle("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public LoginForm() {
		init_login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(loginBtn)) {
			String email = emailTf.getText();
			String password = String.valueOf(passTf.getPassword());

			if (email.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username and password must be filled!", "Invalid",
						JOptionPane.ERROR_MESSAGE);
			} else {
				ResultSet rs = Database.getInstance().executeQuery("SELECT * FROM `user` WHERE UserEmail = '" + email
						+ "' AND UserPassword = '" + password + "'  ");
				try {
					boolean hasUser = rs.next();
					if (!hasUser) {
						JOptionPane.showMessageDialog(null, "Username and password don't exist!", "Invalid",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					MainForm mainform = new MainForm();

					if (email.equals("admin@admin.com")) {
						mainform.admin();
						mainform.setVisible(true);
					} else {
						String userId = rs.getString("UserId");
						ResultSet idStaff = Database.getInstance().executeQuery(
								"SELECT * FROM `user` JOIN staff ON user.UserId = staff.UserId WHERE staff.UserId = '"
										+ userId + "' ");
						boolean hasStaff = idStaff.next();
						if (hasStaff) {
							mainform.staff();
							mainform.setVisible(true);
						} else {
							ResultSet idCust = Database.getInstance().executeQuery(
									"SELECT * FROM `user` JOIN customer ON user.UserId = customer.UserId WHERE customer.UserId = '"
											+ userId + "' ");
							boolean hasCustomer = idCust.next();
							if (hasCustomer) {
								mainform.customer();
								mainform.setVisible(true);
							}
						}
					}

					this.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (source.equals(registerBtn)) {
			new RegisterCustomer();
		}

	}

}
