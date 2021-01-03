package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import facades.Authentication;
import facades.Database;
import interfaces.ReadonlyRepository;
import models.Clothing;
import models.ClothingType;

public class ReviewForm extends JInternalFrame {

    public JSpinner RatingText;
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JPanel panelSouth;
    private JLabel titleLabel;
    private JLabel RatingLabel;
    private JLabel CommentsLabel;
    private JLabel BlueEyesLabel;
    private JLabel TotalLabel;
    private JTextField CommentsText;
    private JButton submitBtn;
    private JButton cancelBtn;
    private JButton ResetBtn;

    public ReviewForm(Clothing clothing, int saleQuantity, ReadonlyRepository<ClothingType> clothingTypeRepository) {

        setLayout(new BorderLayout());

        new JPanel();
        panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        titleLabel = new JLabel("Review");
        panelNorth.add(titleLabel);
        add(panelNorth, "North");

        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(7, 1));
        panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        Optional<ClothingType> clothingType = clothingTypeRepository.getById(clothing.getClothingTypeId());
        assert clothingType.isPresent();

        BlueEyesLabel = new JLabel(String.format("%s (%s) x%s @Rp%s", clothing.getClothingName(), clothingType.get().getClothingTypeName(), saleQuantity, clothing.getClothingPrice()));
        panelCenter.add(BlueEyesLabel);

        new JTextField();
        panelCenter.add(BlueEyesLabel);

        TotalLabel = new JLabel("Total: Rp 34000");
        panelCenter.add(TotalLabel);

        new JTextField();
        panelCenter.add(TotalLabel);

        RatingLabel = new JLabel("Rating");
        panelCenter.add(RatingLabel);

        RatingText = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 5.0, 1.0));
        panelCenter.add(RatingText);

        CommentsLabel = new JLabel("Comments");
        panelCenter.add(CommentsLabel);

        CommentsText = new JTextField();
        panelCenter.add(CommentsText);

        add(panelCenter, "Center");

        panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        cancelBtn = new JButton("Cancel");
        panelSouth.add(cancelBtn);

        submitBtn = new JButton("Submit");
        panelSouth.add(submitBtn);

        submitBtn.addActionListener(e -> {
            if (RegisterData()) {
                String Rating = RatingText.getValue().toString();
                String Comments = CommentsText.getText();

                TimeZone tz = TimeZone.getTimeZone("UTC");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setTimeZone(tz);
                String reviewDate = df.format(new Date());

                Database.getInstance().executeUpdate(
                        "INSERT INTO review (ClothingId, UserId, ReviewScore, ReviewDescription, ReviewDate) VALUES (" + clothing.getClothingId() + ", " + Authentication.getUser().getUserId() + ", "
                                + Rating + ", '" + Comments + "', '" + reviewDate + "')");

                JOptionPane.showMessageDialog(this, "Thank you for the review!");
                this.dispose();
            }
        });

        ResetBtn = new JButton("Reset");
        panelSouth.add(ResetBtn);

        ResetBtn.addActionListener(e -> CommentsText.setText(""));

        add(panelSouth, "South");

        setTitle(title);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setTitle("Review Form");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private Boolean RegisterData() {

        String strcomments = CommentsText.getText();

        if (strcomments.equals("")) {
            JOptionPane.showMessageDialog(this, "Please write your comments");
            CommentsText.requestFocusInWindow();
            return false;
        }

        return true;

    }
}
