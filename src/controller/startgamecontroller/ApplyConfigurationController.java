package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.engine.EngineImpl;
import view.configuration.SettingsDialog;
import view.mainframe.AppMainFrame;
import view.mainframe.StartGameMainFrame;

/**
 * @author Ted & Kevin
 *
 *         A controller for settings panel to apply configuration
 */
public class ApplyConfigurationController implements ActionListener {

	private SettingsDialog settingGameDialog;
	private StartGameMainFrame startGameMainFrame;

	public ApplyConfigurationController(SettingsDialog settingGameDialog, StartGameMainFrame startGameMainFrame) {
		this.settingGameDialog = settingGameDialog;
		this.startGameMainFrame = startGameMainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		settingGameDialog.setVisible(false);
		startGameMainFrame.setVisible(false);

		boolean hasObstacle = settingGameDialog.getObstacleConfig().equalsIgnoreCase("YES") ? true : false;
		EngineImpl.getSingletonInstance().configBoard(settingGameDialog.getBoardSizeSelection(), hasObstacle);
		EngineImpl.getSingletonInstance().configNumPiece(settingGameDialog.getPieceNumberSelection());

		EventQueue.invokeLater(new Runnable() {
			@Override
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