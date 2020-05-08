package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SettingsDialog;
import view.mainframe.StartGameMainFrame;

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
