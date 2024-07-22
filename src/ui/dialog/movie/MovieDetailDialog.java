package ui.dialog.movie;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Movie;
import entity.Review;
import ui.MovieFrame;
import ui.dialog.review.WriteReviewDialog;

public class MovieDetailDialog extends JDialog {
	private static Movie movie;
	private static List<Review> reviews;
	private static MovieFrame parentFrame;
	private static JTable reviewTable;

	public MovieDetailDialog(MovieFrame frame, Movie movie, List<Review> reviews) {
		super(frame, "영화 상세 정보", true);
		this.movie = movie;
		this.reviews = reviews;
		this.parentFrame = frame;

		setLayout(new BorderLayout());
		setSize(400, 400);
		setLocationRelativeTo(frame);

		/* 영화 상세 정보 */
		JPanel moviePanel = new JPanel(new GridLayout(5, 2));
		moviePanel.add(new JLabel("🎞영화 제목:"));
		moviePanel.add(new JLabel(movie.getTitle()));
		moviePanel.add(new JLabel("🎞장르:"));
		moviePanel.add(new JLabel(movie.getGenre().toString()));
		moviePanel.add(new JLabel("🎞감독:"));
		moviePanel.add(new JLabel(movie.getDirector()));
		moviePanel.add(new JLabel("🎞개봉연도:"));
		moviePanel.add(new JLabel(String.valueOf(movie.getReleaseYear())));

		add(moviePanel, BorderLayout.NORTH);

		/* 리뷰 목록 조회 */
		JPanel reviewPanel = new JPanel(new BorderLayout());
		reviewPanel.add(new JLabel("리뷰 목록"), BorderLayout.NORTH);

		DefaultTableModel reviewTableModel = new DefaultTableModel(new String[]{"사용자", "내용", "평점", "작성일자"}, 0);
		reviewTable = new JTable(reviewTableModel);

		for (Review review : reviews) {
			reviewTableModel.addRow(new Object[]{
				review.getUser().getUsername(),
				review.getComment(),
				review.getRating(),
				review.getCreatedAt()
			});
		}

		reviewPanel.add(new JScrollPane(reviewTable), BorderLayout.CENTER);
		add(reviewPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton writeReviewButton = new JButton("리뷰 작성");

		writeReviewButton.addActionListener(e -> new WriteReviewDialog(parentFrame, movie).setVisible(true));

		buttonPanel.add(writeReviewButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
