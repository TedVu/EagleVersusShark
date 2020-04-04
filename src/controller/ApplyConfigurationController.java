package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.mainframe.MainAppFrame;

public class ApplyConfigurationController implements ActionListener {

	private JFrame startFrame;
	private JDialog settingGameDialog;

	public ApplyConfigurationController(JFrame startFrame, JDialog settingGameDialog) {
		this.startFrame = startFrame;
		this.settingGameDialog = settingGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startFrame.setVisible(false);
		settingGameDialog.setVisible(false);

		// perform configuration for board size and number of piece here

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
