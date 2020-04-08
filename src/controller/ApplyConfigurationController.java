package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import models.engine.EngineImpl;
import view.configuration.SettingsDialog;
import view.mainframe.MainAppFrame;

/**
 * 
 * @author kevin & ted
 * 
 */
public class ApplyConfigurationController implements ActionListener {
	private JFrame startFrame;
	private SettingsDialog settingGameDialog;

	/**
	 * 
	 * @see
	 * 
	 */
	public ApplyConfigurationController(JFrame startFrame, SettingsDialog settingGameDialog) {
		this.startFrame = startFrame;
		this.settingGameDialog = settingGameDialog;
	}

	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		EngineImpl.getSingletonInstance().configBoardSize(settingGameDialog.getBoardSizeSelection(),
				settingGameDialog.getBoardSizeSelection());

		// perform configuration for board size and number of piece here
		EngineImpl.getSingletonInstance().configNumPiece(settingGameDialog.getPieceNumberSelection());
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

		startFrame.dispose();
		settingGameDialog.dispose();
	}

}
