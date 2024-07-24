package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.MovieDao;
import dao.UserDao;
import entity.Movie;
import entity.User;
import ui.dialog.movie.EditMovieDialog;
import ui.dialog.movie.RegisterMovieDialog;
import ui.dialog.user.SignupDialog;
import ui.panel.MainPanel;
import ui.panel.SearchPanel;

/**
 * 영화 UI
 */
public class MainFrame extends JFrame {

	private static JTable table;
	private static DefaultTableModel tableModel;
	private final MovieDao movieDao = new MovieDao();
	private final UserDao userDao = new UserDao();

	public MainFrame() {
		setTitle("🎬 Movie 🍿");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initComponents();
		addEventListeners();
		movieList();
	}

	private void initComponents() {
		tableModel = new DefaultTableModel(new Object[]{"ID", "제목", "장르", "영화감독", "개봉연도"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);

		// Search Panel
		final SearchPanel searchPanel = new SearchPanel(this);

		// Button Panel
		final MainPanel mainPanel = new MainPanel(this);

		setLayout(new BorderLayout());
		add(searchPanel, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(mainPanel, BorderLayout.SOUTH);
	}

	private void addEventListeners() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					final int selectRow = table.getSelectedRow();
					final int movieId = (int) tableModel.getValueAt(selectRow, 0);

					new MovieFrame(movieId).setVisible(true);
				}
			}
		});
	}

	public void showSignupDialog() {
		final SignupDialog signupDialog = new SignupDialog(this, this.tableModel);
		signupDialog.setVisible(true);
	}

	public void showSearchMovies(final String keyword) {
		if (!keyword.isBlank()) {
			searchMovies(keyword);
		}
	}

	public void showRegisterMovieDialog() {
		final RegisterMovieDialog movieDialog = new RegisterMovieDialog(this, this.tableModel);
		movieDialog.setVisible(true);
	}

	public void showEditMovieDialog() {
		final int selectRow = table.getSelectedRow();

		if (selectRow >= 0) {
			final EditMovieDialog movieDialog = new EditMovieDialog(this, this.tableModel, selectRow);
			movieDialog.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "영화를 선택하세요.");
		}
	}

	public void signup(final User user) {
		final int ret = userDao.signup(user);

		if (ret == 1) {
			movieList();
		}
	}

	public void registerMovie(final Movie movie) {
		final int ret = movieDao.registerMovie(movie);

		if (ret == 1) {
			movieList();
		}
	}

	public void updateMovie(final Movie movie) {
		final int ret = movieDao.updateMovie(movie);

		if (ret == 1) {
			movieList();
		}
	}

	public void deleteMovie(final int movieId) {
		final int ret = movieDao.deleteMovie(movieId);

		if (ret == 1) {
			movieList();
		}
	}

	private void clearTable() {
		tableModel.setRowCount(0);
	}

	public void movieList() {
		clearTable();

		final List<Movie> movies = movieDao.movieList();

		for (final Movie movie : movies) {
			tableModel.addRow(new Object[]{movie.getId(), movie.getTitle(), movie.getGenre(), movie.getDirector(), movie.getReleaseYear()});
		}
	}

	public void searchMovies(final String keyword) {
		clearTable();

		final List<Movie> movies = movieDao.searchMovie(keyword);

		for (Movie movie : movies) {
			tableModel.addRow(new Object[]{
				movie.getId(),
				movie.getTitle(),
				movie.getGenre(),
				movie.getDirector(),
				movie.getReleaseYear()
			});
		}
	}

	public User findUserByEmail(final String email) {
		return userDao.findUserByEmail(email);
	}
}
