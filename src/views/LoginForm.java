package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facades.Authentication;
import models.Customer;
import models.Staff;
import models.abstracts.User;
import repositories.CustomerRepository;
import repositories.StaffRepository;

public class LoginForm extends JFrame implements ActionListener {
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    JButton registerBtn;
    JButton loginBtn;
    JTextField emailTf;
    JPasswordField passTf;

    public LoginForm(CustomerRepository customerRepository, StaffRepository staffRepository) {
        init_login();
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

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
                Optional<Customer> customerOptional = customerRepository.getByEmailAndPassword(email, password);
                Optional<Staff> staffOptional = staffRepository.getByEmailAndPassword(email, password);

                boolean isCustomer = customerOptional.isPresent();
                boolean isStaff = staffOptional.isPresent();

                if (!isCustomer && !isStaff) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Username and password don't exist!",
                            "Invalid",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                User user = isCustomer ? customerOptional.get() : staffOptional.get();
                Authentication.login(user);

                boolean isAdmin = user.isAdmin();

                MainForm mainform = new MainForm();

                if (isAdmin) {
                    mainform.admin();
                } else if (isCustomer) {
                    mainform.customer();
                } else {
                    mainform.staff();
                }

                this.dispose();
            }
        } else if (source.equals(registerBtn)) {
            new RegisterCustomer();
        }

    }

}
