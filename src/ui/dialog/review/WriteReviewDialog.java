package ui.dialog.review;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Review;
import ui.frame.MovieFrame;

public class WriteReviewDialog extends JDialog {
	private JTextField userField, commentField;
	private JSlider ratingField;
	private JButton writeButton;

	/**
	 * 리뷰 작성 완료
	 */
	public WriteReviewDialog(
		final MovieFrame frame,
		final DefaultTableModel tableModel,
		final int movieId
	) {
		setTitle("✅리뷰를 작성하세요");
		setSize(400, 400);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 2));

		userField = new JTextField();
		ratingField = new JSlider(1, 5, 1);
		ratingField.setMajorTickSpacing(1);
		ratingField.setPaintTicks(true);
		ratingField.setPaintLabels(true);
		commentField = new JTextField();

		inputPanel.add(new JLabel("사용자 이메일"));
		inputPanel.add(userField);
		inputPanel.add(new JLabel("평점 (5점만점)"));
		inputPanel.add(ratingField);
		inputPanel.add(new JLabel("후기"));
		inputPanel.add(commentField);

		final JPanel buttonPanel = new JPanel();
		writeButton = new JButton("리뷰 작성");
		buttonPanel.add(writeButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		writeButton.addActionListener(e -> {
			final String email = userField.getText();
			final int rating = ratingField.getValue();
			final String comment = commentField.getText();

			final Review review = new Review(comment, rating);

			frame.writeReview(review, email, movieId);

			dispose();
		});
	}
}