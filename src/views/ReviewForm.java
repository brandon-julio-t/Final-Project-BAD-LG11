package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import facades.Database;

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ReviewForm extends JInternalFrame {

	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JLabel titleLabel;
	private JLabel RatingLabel;
	private JLabel CommentsLabel;
	private JLabel BlueEyesLabel;
	private JLabel TotalLabel;

	public JSpinner RatingText;
	private JTextField CommentsText;
	private JButton submitBtn;
	private JButton cancelBtn;
	private JButton ResetBtn;

	public ReviewForm() {

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

		BlueEyesLabel = new JLabel("Blue Eyes White Dragon (T-Shirt) x3 @Rp12000");
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

		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (RegisterData()) {
					JOptionPane.showMessageDialog(null, "Register Data Successfully");
				}
				String Rating = RatingText.getValue().toString();
				String Comments = CommentsText.getText();

				TimeZone tz = TimeZone.getTimeZone("UTC");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				df.setTimeZone(tz);
				String reviewDate = df.format(new Date());

				Database.getInstance().executeUpdate(
						"INSERT INTO review (ClothingId, UserId, ReviewScore, ReviewDescription, ReviewDate) VALUES (1, 1, "
								+ Rating + ", '" + Comments + "', '" + reviewDate + "')");
			}

		});

		ResetBtn = new JButton("Reset");
		panelSouth.add(ResetBtn);

		ResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				CommentsText.setText("");

			}
		});

		add(panelSouth, "South");

		setTitle("Review Form");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private Boolean RegisterData() {

		String strcomments = CommentsText.getText();

		if (strcomments.equals("")) {
			JOptionPane.showMessageDialog(null, "Please write your comments");
			CommentsText.requestFocusInWindow();
			return false;
		}

		return false;

	}
}
