package ui.dialog.review;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Movie;
import entity.Review;
import entity.User;
import ui.frame.MovieFrame;

public class EditReviewDialog extends JDialog {

	private static JTextField userField, commentField;
	private static JSlider ratingField;
	private JButton updateButton, deleteButton;

	public EditReviewDialog(
		final MovieFrame frame,
		final DefaultTableModel tableModel,
		final Review review
	) {
		setTitle("✅리뷰를 수정/삭제하세요");
		setSize(400, 400);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 2));

		userField = new JTextField(String.valueOf(review.getUser().getEmail()));
		userField.setEnabled(false);
		ratingField = new JSlider(JSlider.HORIZONTAL, 1, 5, review.getRating());
		ratingField.setMajorTickSpacing(1);
		ratingField.setPaintTicks(true);
		ratingField.setPaintLabels(true);
		commentField = new JTextField(review.getComment());

		inputPanel.add(new JLabel("사용자 이메일"));
		inputPanel.add(userField);
		inputPanel.add(new JLabel("평점 (5점만점)"));
		inputPanel.add(ratingField);
		inputPanel.add(new JLabel("후기"));
		inputPanel.add(commentField);

		final JPanel buttonPanel = new JPanel();
		updateButton = new JButton("수정");
		deleteButton = new JButton("삭제");

		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		updateButton.addActionListener(e -> {
			final int ret = JOptionPane.showConfirmDialog(this, "수정할까요?", "수정 확인", JOptionPane.YES_NO_OPTION);

			if(ret == JOptionPane.YES_OPTION) {
				final int rating = ratingField.getValue();
				final String comment = commentField.getText();
				final User user = review.getUser();
				final Movie movie = review.getMovie();

				// TODO: 비밀번호 일치 확인 필요

				frame.updateReview(new Review(review.getId(), comment, rating, user, movie));

				dispose();
			}
		});

		deleteButton.addActionListener(e -> {
			final int ret = JOptionPane.showConfirmDialog(this, "삭제할까요?", "삭제 확인 메시지", JOptionPane.YES_NO_OPTION);

			if( ret == JOptionPane.YES_OPTION ) {
				frame.deleteReview(review);

				dispose();
			}
		});
	}
}
