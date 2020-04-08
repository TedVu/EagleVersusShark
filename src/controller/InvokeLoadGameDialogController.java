package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.configuration.LoadGameDialog;

/**
 * 
 * @author kevin & ted
 * 
 */
public class InvokeLoadGameDialogController implements ActionListener {

	private JFrame startFrame;
	
	/**
	 * 
	 * @see
	 * 
	 */
	public InvokeLoadGameDialogController(JFrame startFrame) {
		this.startFrame = startFrame;
	}
	
	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new LoadGameDialog(startFrame);
	}

}
