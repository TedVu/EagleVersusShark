package views.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.configuration.ConfigurationFrame;

public class MockViewClientA2 {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
