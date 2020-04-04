package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.mainframe.MainAppFrame;

public class LoadGameController implements ActionListener {

	private JFrame startFrame;
	private JDialog loadGameDialog;

	public LoadGameController(JFrame startFrame, JDialog loadGameDialog) {
		this.startFrame = startFrame;
		this.loadGameDialog = loadGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		startFrame.setVisible(false);
		loadGameDialog.setVisible(false);

		// perform loading game state here

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
