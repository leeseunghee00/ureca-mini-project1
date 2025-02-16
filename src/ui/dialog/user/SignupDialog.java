package ui.dialog.user;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.User;
import ui.frame.MainFrame;

public class SignupDialog extends JDialog {
	private JTextField userIdField, usernameField, emailField, passwordField;
	private JButton signupButton;

	public SignupDialog(MainFrame frame, DefaultTableModel tableModel) {
		setTitle("🎉 회원가입을 환영합니다. 🎉");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5, 3));

		userIdField = new JTextField();
		usernameField = new JTextField();
		emailField = new JTextField();
		passwordField = new JTextField();

		inputPanel.add(new JLabel("User Id"));
		inputPanel.add(userIdField);
		inputPanel.add(new JLabel("User name"));
		inputPanel.add(usernameField);
		inputPanel.add(new JLabel("Email"));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("Password"));
		inputPanel.add(passwordField);

		final JPanel buttonPanel = new JPanel();
		signupButton = new JButton("가입하기");
		buttonPanel.add(signupButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		signupButton.addActionListener(e -> {
			final int userId = Integer.parseInt(userIdField.getText());
			final String username = usernameField.getText();
			final String email = emailField.getText();
			final String password = passwordField.getText();

			if (frame.findUserByEmail(email) != null) {
				JOptionPane.showMessageDialog(this, "이미 존재하는 사용자입니다.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				final User user = new User(userId, username, email, password);
				frame.signup(user);
			}

			dispose();
		});
	}

}
