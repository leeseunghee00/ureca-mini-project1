package ui.frame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.MovieDao;
import dao.ReviewDao;
import dao.UserDao;
import entity.Movie;
import entity.Review;
import entity.User;
import ui.dialog.review.EditReviewDialog;
import ui.dialog.review.WriteReviewDialog;
import ui.panel.ReviewPanel;

/**
 * 영화 상세 조회 (사용자)
 */
public class MovieFrame extends JFrame {

	private JTable movieTable;
	private DefaultTableModel movieTableModel;
	private JTable reviewTable;
	private DefaultTableModel reviewTableModel;
	private final MovieDao movieDao = new MovieDao();
	private final ReviewDao reviewDao = new ReviewDao();
	private final UserDao userDao = new UserDao();

	public MovieFrame(int movieId) {
		setTitle("영화 상세 조회");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		initComponents();
		showMovieDetail(movieId);
		addEventListeners();
	}

	private void initComponents() {
		// Layouts
		setLayout(new BorderLayout());

		// Movie Panel
		JPanel moviePanel = new JPanel(new BorderLayout());
		moviePanel.add(new JLabel("🍿 영화 정보", SwingConstants.CENTER), BorderLayout.NORTH);

		// Movie Table
		movieTableModel = new DefaultTableModel(new Object[]{"영화 ID", "영화 제목", "장르", "감독", "개봉일자"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		movieTable = new JTable(movieTableModel);
		moviePanel.add(new JScrollPane(movieTable), BorderLayout.CENTER);

		// Review Panel
		JPanel reviewPanel = new JPanel(new BorderLayout());
		reviewPanel.add(new JLabel("🤍 영화 리뷰 🤍", SwingConstants.CENTER), BorderLayout.NORTH);

		// Review Table
		reviewTableModel = new DefaultTableModel(new Object[]{"리뷰 ID", "사용자", "평점", "후기", "작성일자"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		reviewTable = new JTable(reviewTableModel);
		reviewPanel.add(new JScrollPane(reviewTable), BorderLayout.CENTER);

		// Button Panel
		ReviewPanel buttonPanel = new ReviewPanel(this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, moviePanel, reviewPanel);
		splitPane.setResizeWeight(0.2);
		splitPane.setDividerLocation(0.2);

		add(splitPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void addEventListeners() {
		reviewTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					final int selectRow = reviewTable.getSelectedRow();

					if (selectRow >= 0) {
						final int reviewId = (int) reviewTableModel.getValueAt(selectRow, 0);
						final Review review = reviewDao.reviewDetail(reviewId);

						final EditReviewDialog editReviewDialog = new EditReviewDialog(MovieFrame.this, reviewTableModel, review);

						editReviewDialog.setVisible(true);

					}
				}
			}
		});
	}

	public void showWriteReviewDialog() {
		final int selectRow = movieTable.getSelectedRow();

		if (selectRow >= 0) {
			final int movieId = (int) movieTableModel.getValueAt(selectRow, 0);
			final WriteReviewDialog reviewDialog = new WriteReviewDialog(this, reviewTableModel, movieId);

			reviewDialog.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "영화를 선택하세요.");
		}
	}

	public void showEditReviewDialog() {
		final int selectRow = reviewTable.getSelectedRow();

		if (selectRow >= 0) {
			final int reviewId = (int) reviewTableModel.getValueAt(selectRow, 0);
			final Review review = reviewDao.reviewDetail(reviewId);

			final EditReviewDialog reviewDialog = new EditReviewDialog(this, reviewTableModel, review);

			reviewDialog.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "리뷰를 선택하세요.");
		}
	}

	public void showMovieDetail(final int movieId) {
		final Movie movie = movieDao.movieDetail(movieId);

		if (movie != null) {
			movieTableModel.setRowCount(0);
			movieTableModel.addRow(new Object[]{
				movie.getId(),
				movie.getTitle(),
				movie.getGenre().toString(),
				movie.getDirector(),
				movie.getReleaseYear()
			});

			showMovieReviews(movieId);
		}
	}

	public void showMovieReviews(final int movieId) {
		final List<Review> reviews = reviewDao.getMovieReviews(movieId);

		reviewTableModel.setRowCount(0);

		if (reviews == null || reviews.isEmpty()) {
			reviewTableModel.addRow(new Object[]{"", "", "", "아직 리뷰가 존재하지 않습니다.", ""});
		} else {
			for (final Review review : reviews) {
				reviewTableModel.addRow(new Object[]{
					review.getId(),
					review.getUser().getUsername(),
					review.getRating(),
					review.getComment(),
					review.getCreatedAt()
				});
			}
		}
	}

	public void writeReview(final Review review, final String email, final int movieId) {
		final User user = userDao.findUserByEmail(email);
		final Movie movie = movieDao.movieDetail(movieId);

		if (user == null) {
			JOptionPane.showMessageDialog(this, "회원만 작성할 수 있습니다.");
		} else {
			review.setUser(user);
			review.setMovie(movie);

			final int ret = reviewDao.writeReview(review);

			if (ret == 1) {
				showMovieReviews(review.getMovie().getId());
			}
		}
	}

	public void updateReview(final Review review) {
		final int ret = reviewDao.updateReview(review);

		// TODO: 비밀번호 일치 확인 필요

		if (ret == 1) {
			showMovieReviews(review.getMovie().getId());
		}
	}

	public void deleteReview(final Review review) {
		final int ret = reviewDao.deleteReview(review.getId());

		if (ret == 1) {
			showMovieReviews(review.getMovie().getId());
		}
	}
}
