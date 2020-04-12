package views.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.configuration.ConfigurationFrame;

/**
 * <h1>Main class</h1> MockViewClientA2 is the main class. It has full
 * functionalities, including "start up" menu (Select various board size, Load
 * game).
 * 
 * @author kevin & ted
 * 
 */
public class MockViewClientA2 {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationFrame window = new ConfigurationFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
