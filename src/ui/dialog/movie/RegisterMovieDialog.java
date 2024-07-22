package ui.dialog.movie;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Genre;
import entity.Manager;
import entity.Movie;
import ui.MovieFrame;

public class RegisterMovieDialog extends JDialog {

	private static JTextField movieIdField, managerField, titleField, directorField, releaseYearField;
	private static JComboBox<Genre> genreField;
	private static JButton registerButton;

	public RegisterMovieDialog(MovieFrame frame, DefaultTableModel tableModel) {
		setTitle("영화를 추가하세요");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(10, 3));

		movieIdField = new JTextField();
		managerField = new JTextField();
		titleField = new JTextField();
		genreField = new JComboBox<>(Genre.values());
		directorField = new JTextField();
		releaseYearField = new JTextField();

		inputPanel.add(new JLabel("Movie Id"));
		inputPanel.add(movieIdField);
		inputPanel.add(new JLabel("Manager Id"));
		inputPanel.add(managerField);
		inputPanel.add(new JLabel("Title"));
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("Genre"));
		inputPanel.add(genreField);
		inputPanel.add(new JLabel("Director"));
		inputPanel.add(directorField);
		inputPanel.add(new JLabel("Release Year"));
		inputPanel.add(releaseYearField);

		JPanel buttonPanel = new JPanel();

		registerButton = new JButton("등록");

		buttonPanel.add(registerButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		registerButton.addActionListener(e -> {
			final int movieId = Integer.parseInt(movieIdField.getText());
			final String managerId = managerField.getText();
			final String title = titleField.getText();
			final Genre genre = (Genre) genreField.getSelectedItem();
			final String director = directorField.getText();
			final int releaseYear = Integer.parseInt(releaseYearField.getText());

			Manager manager = new Manager();
			manager.setId(managerId);

			Movie movie = new Movie(movieId, title, genre, director, releaseYear, manager);

			frame.registerMovie(movie);

			dispose();
		});
	}
}

