import javax.swing.*;

import ui.MovieFrame;

public class MovieMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MovieFrame().setVisible(true);
		});
	}
}