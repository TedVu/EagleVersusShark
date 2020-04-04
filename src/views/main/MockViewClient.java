package views.main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.mainframe.MainAppFrame;

public class MockViewClient {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
