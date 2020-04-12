package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.configuration.SettingsDialog;

public class InvokeConfigureGameDialogController implements ActionListener {

	private JFrame startFrame;

	public InvokeConfigureGameDialogController(JFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SettingsDialog(startFrame);
	}

}
