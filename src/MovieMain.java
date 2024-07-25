import javax.swing.*;

import ui.frame.MainFrame;

public class MovieMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainFrame().setVisible(true);
		});
	}
}