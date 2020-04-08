package views.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.mainframe.MainAppFrame;

/**
 * <h1>Main class (for testing)</h1> MockViewClient is the main class. It has
 * only the game board. Used for fast test game board.
 * 
 * @author kevin & ted
 * 
 */
public class MockViewClient {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppFrame window = new MainAppFrame();
					window.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
