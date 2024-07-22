package ui.dialog.review;

import java.awt.*;
import java.util.Date;

import javax.swing.*;

import entity.Movie;
import entity.Review;
import entity.User;
import ui.MovieFrame;

public class WriteReviewDialog extends JDialog {
	private JTextField userField, commentField, ratingField;
	private JButton writeButton;
	private Movie movie;
	private MovieFrame parentFrame;

	public WriteReviewDialog(MovieFrame frame, Movie movie) {
		super(frame, "리뷰를 작성하세요", true);
		this.movie = movie;
		this.parentFrame = frame;

		setTitle("리뷰를 작성하세요");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 2));

		userField = new JTextField();
		commentField = new JTextField();
		ratingField = new JTextField();

		inputPanel.add(new JLabel("User Id"));
		inputPanel.add(userField);
		inputPanel.add(new JLabel("Comment"));
		inputPanel.add(commentField);
		inputPanel.add(new JLabel("Rating"));
		inputPanel.add(ratingField);

		JPanel buttonPanel = new JPanel();
		writeButton = new JButton("작성");

		writeButton.addActionListener(e -> {
			final int userId = Integer.parseInt(userField.getText());
			final String comment = commentField.getText();
			final int rating = Integer.parseInt(ratingField.getText());

			User user = parentFrame.findUserById(userId);

			if (user == null) {
				JOptionPane.showMessageDialog(this, "회원가입을 해주세요", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				Review review = new Review(comment, rating, user, movie, new Date());
				parentFrame.writeReview(review);

				dispose();
			}
		});

		buttonPanel.add(writeButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}