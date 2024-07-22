package ui.dialog.movie;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Genre;
import entity.Movie;
import ui.MovieFrame;

public class EditMovieDialog extends JDialog {

	private static JTextField movieIdField, managerField, titleField, directorField, releaseYearField;
	private static JComboBox<Genre> genreField;
	private static JButton updateButton, deleteButton;

	public EditMovieDialog(MovieFrame frame, DefaultTableModel tableModel, int selectRow) {
		setTitle("영화를 수정/삭제하세요.");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(10, 3));

		movieIdField = new JTextField(tableModel.getValueAt(selectRow, 0).toString());
		movieIdField.setEnabled(false);
		managerField = new JTextField(tableModel.getValueAt(selectRow, 1).toString());
		managerField.setEnabled(false);
		titleField = new JTextField(tableModel.getValueAt(selectRow, 2).toString());
		genreField = new JComboBox<>(Genre.values());
		genreField.setSelectedItem(Genre.valueOf(tableModel.getValueAt(selectRow, 3).toString()));
		directorField = new JTextField(tableModel.getValueAt(selectRow, 4).toString());
		releaseYearField = new JTextField(tableModel.getValueAt(selectRow, 5).toString());

		inputPanel.add(new JLabel("영화ID"));
		inputPanel.add(movieIdField);
		inputPanel.add(new JLabel("제목"));
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("장르"));
		inputPanel.add(genreField);
		inputPanel.add(new JLabel("영화감독"));
		inputPanel.add(directorField);
		inputPanel.add(new JLabel("개봉연도"));
		inputPanel.add(releaseYearField);

		JPanel buttonPanel = new JPanel();

		updateButton = new JButton("수정");
		deleteButton = new JButton("삭제");

		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		updateButton.addActionListener(e -> {
			final int movieId = Integer.parseInt(movieIdField.getText());
			final String title = titleField.getText();
			final Genre genre = (Genre) genreField.getSelectedItem();
			final String director = directorField.getText();
			final int releaseYear = Integer.parseInt(releaseYearField.getText());

			Movie movie = new Movie(movieId, title, genre, director, releaseYear);

			frame.updateMovie(movie);

			dispose();
		});

		deleteButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "삭제할까요?", "삭제 확인 메시지", JOptionPane.YES_NO_OPTION);

			if( ret == JOptionPane.YES_OPTION ) {
				final int movieId = Integer.parseInt(movieIdField.getText());

				frame.deleteMovie(movieId);

				dispose();
			}
		});
	}
}

