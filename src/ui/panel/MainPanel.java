package ui.panel;

import java.awt.*;

import javax.swing.*;

import ui.MovieFrame;

public class ButtonPanel extends JPanel {

	public ButtonPanel(final MovieFrame frame) {
		setLayout(new FlowLayout());

		final JButton signupButton = new JButton("회원가입");
		final JButton registerButton = new JButton("등록");
		final JButton editButton = new JButton("수정/삭제");
		final JButton listButton = new JButton("목록");

		signupButton.addActionListener(e -> frame.showSignupDialog());
		registerButton.addActionListener(e -> frame.showRegisterMovieDialog());
		editButton.addActionListener(e -> frame.showEditMovieDialog());
		listButton.addActionListener(e -> frame.movieList());

		add(signupButton);
		add(registerButton);
		add(editButton);
		add(listButton);
	}
}
