package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import repositories.ClothingSizeRepository;
import repositories.ClothingTypeRepository;
import repositories.CustomerRepository;
import repositories.StaffRepository;

public class MainForm extends JFrame implements ActionListener {

    private JMenuBar menubar;
    private JDesktopPane desktopPane;

    private JMenuItem menuitem_clothing;
    private JMenuItem menuitem_logout;
    private JMenuItem menuitem_vendor;
    private JMenuItem menuitem_purchase;
    private JMenuItem menuitem_buyClothes;
    private JMenuItem menuitem_review;

    private JMenu menu_account;
    private JMenu menu_manage;
    private JMenu menu_transaction;

    public MainForm() {

        desktopPane = new JDesktopPane();
        add(desktopPane);

        menubar = new JMenuBar();

        menu_account = new JMenu("Account");
        menuitem_logout = new JMenuItem("Logout");
        menuitem_logout.addActionListener(this);
        menu_account.add(menuitem_logout);
        menubar.add(menu_account);

        menu_manage = new JMenu("Manage");
        menuitem_clothing = new JMenuItem("Clothing");
        menuitem_clothing.addActionListener(this);
        menuitem_vendor = new JMenuItem("Vendor");
        menuitem_vendor.addActionListener(this);
        menu_manage.add(menuitem_clothing);
        menu_manage.add(menuitem_vendor);
        menubar.add(menu_manage);

        menu_transaction = new JMenu("Transaction");

        menuitem_purchase = new JMenuItem("Purchase");
        menuitem_purchase.addActionListener(this);
        menu_transaction.add(menuitem_purchase);

        menuitem_buyClothes = new JMenuItem("Buy Clothes");
        menuitem_buyClothes.addActionListener(this);
        menu_transaction.add(menuitem_buyClothes);

        menuitem_review = new JMenuItem("Review");
        menuitem_review.addActionListener(this);
        menu_transaction.add(menuitem_review);
        menubar.add(menu_transaction);

        setJMenuBar(menubar);
        setTitle("Mainform");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }

    public void admin() {
        menu_account.setVisible(true);
        menu_manage.setVisible(true);
        menu_transaction.setVisible(false);
        setVisible(true);
    }

    public void staff() {
        menu_account.setVisible(true);
        menu_manage.setVisible(false);
        menu_transaction.setVisible(true);
        menuitem_purchase.setVisible(true);
        menuitem_buyClothes.setVisible(false);
        menuitem_review.setVisible(false);
        setVisible(true);
    }

    public void customer() {
        menu_account.setVisible(true);
        menu_manage.setVisible(false);
        menu_transaction.setVisible(true);
        menuitem_purchase.setVisible(false);
        menuitem_buyClothes.setVisible(true);
        menuitem_review.setVisible(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get what 'object' is clicked
        Object source = e.getSource();

        if (source == (menuitem_logout)) {
            this.dispose();
            new LoginForm(
                    new CustomerRepository(),
                    new StaffRepository()
            );
        } else if (source.equals(menuitem_vendor)) {
            desktopPane.add(new MasterVendorForm());
        } else if (source.equals(menuitem_clothing)) {
            desktopPane.add(new MasterClothingForm());
        } else if (source.equals(menuitem_purchase)) {
            desktopPane.add(
                    new PurchasesForm(
                            new ClothingTypeRepository(),
                            new ClothingSizeRepository()
                    )
            );
        } else if (source.equals(menuitem_buyClothes)) {
            desktopPane.add(new SalesForm());
        } else if (source.equals(menuitem_review)) {
            desktopPane.add(new ReviewForm());
        }
    }
}
