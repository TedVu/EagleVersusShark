package client;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.mainframe.StartGameMainFrame;

/**
 * Main
 * @author ktcs
 *
 */
public class A2Client {
	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final StartGameMainFrame configFrame = new StartGameMainFrame();
					configFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
