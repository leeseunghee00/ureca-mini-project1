package ui.panel;

import java.awt.*;

import javax.swing.*;

import ui.frame.MainFrame;

/**
 * 검색 버튼
 */
public class SearchPanel extends JPanel {

	private static final JTextField searchField = new JTextField();
	private static JButton searchButton;

	public SearchPanel(final MainFrame frame) {
		setLayout(new FlowLayout());

		final Dimension textFieldSize = new Dimension(400, 28);

		searchField.setPreferredSize(textFieldSize);
		searchButton = new JButton("검색");

		add(new JLabel("영화 검색 🔎"));
		add(searchField);
		add(searchButton);

		searchButton.addActionListener(e -> frame.showSearchMovies(searchField.getText()));
	}
}
