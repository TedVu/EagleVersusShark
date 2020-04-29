package client;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.mainframe.StartingGameFrame;

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
					final StartingGameFrame configFrame = new StartingGameFrame();
					configFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
