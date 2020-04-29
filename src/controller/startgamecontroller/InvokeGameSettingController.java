package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SettingsDialog;

public class InvokeGameSettingController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new SettingsDialog();
	}

}
