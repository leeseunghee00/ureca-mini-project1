package ui.panel;

import java.awt.*;

import javax.swing.*;

import ui.frame.MovieFrame;

/**
 * 리뷰 버튼
 */
public class ReviewPanel extends JPanel {

	public ReviewPanel(final MovieFrame frame) {
		setLayout(new FlowLayout());

		final JButton writeButton = new JButton("리뷰 작성");
		final JButton editButton = new JButton("리뷰 수정/삭제");

		writeButton.addActionListener(e -> frame.showWriteReviewDialog());
		editButton.addActionListener(e -> frame.showEditReviewDialog());

		add(writeButton);
		add(editButton);
	}
}
