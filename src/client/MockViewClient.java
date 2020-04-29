package client;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.mainframe.AppMainFrame;

/**
 * <h1>Main class (for testing)</h1> MockViewClient is the main class. It has
 * only the game board. Used for fast test game board.
 * 
 * @author ted &#38; kevin
 * 
 */
public class MockViewClient {
	/**
	 * Main class for A1. 9x9 board with movable pieces and timer.
	 * 
	 * @author ktsc team
	 * @param args
	 */
	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel(
					"javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final AppMainFrame window = new AppMainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}
}
