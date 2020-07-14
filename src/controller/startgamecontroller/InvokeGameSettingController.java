package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SettingsDialog;
import view.mainframe.StartGameMainFrame;

/**
 * @author Ted & Kevin
 *
 *         A controller to invoke gamesetting dialog in the start panel
 */
public class InvokeGameSettingController implements ActionListener {

	private StartGameMainFrame startGameMainFrame;

	public InvokeGameSettingController(StartGameMainFrame startGameMainFrame) {
		this.startGameMainFrame = startGameMainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SettingsDialog(startGameMainFrame);
	}

}
