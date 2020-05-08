package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.engine.EngineImpl;
import view.configuration.SettingsDialog;
import view.mainframe.AppMainFrame;

public class ApplyConfigurationController implements ActionListener {

	private SettingsDialog settingGameDialog;

	public ApplyConfigurationController(SettingsDialog settingGameDialog) {
		this.settingGameDialog = settingGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		settingGameDialog.setVisible(false);
		EngineImpl.getSingletonInstance().configBoardSize(settingGameDialog.getBoardSizeSelection());
		EngineImpl.getSingletonInstance().configNumPiece(settingGameDialog.getPieceNumberSelection());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMainFrame window = new AppMainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
