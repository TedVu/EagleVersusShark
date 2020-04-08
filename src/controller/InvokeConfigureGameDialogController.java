package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.configuration.SettingsDialog;

/**
 * 
 * @author kevin & ted
 * 
 */
public class InvokeConfigureGameDialogController implements ActionListener {

	private JFrame startFrame;

	/**
	 * 
	 * @see
	 * 
	 */
	public InvokeConfigureGameDialogController(JFrame startFrame) {
		this.startFrame = startFrame;
	}

	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new SettingsDialog(startFrame);
	}

}
