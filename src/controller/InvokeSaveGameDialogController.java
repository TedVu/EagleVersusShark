package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SaveGameDialog;
import view.mainframe.MainAppFrame;

/**
 * 
 * @author kevin & ted
 * 
 */
public class InvokeSaveGameDialogController implements ActionListener {

	private MainAppFrame mainFrame;
	
	/**
	 * 
	 * @see
	 * 
	 */
	public InvokeSaveGameDialogController(MainAppFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	/**
	 * 
	 * @see
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		new SaveGameDialog(mainFrame);
	}

}
