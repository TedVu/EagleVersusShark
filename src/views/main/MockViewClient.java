package views.main;

import javax.swing.SwingUtilities;

import view.mainframe.MainAppFrame;

public class MockViewClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainAppFrame();
			}

		});
	}
}
