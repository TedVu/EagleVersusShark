package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SaveGameDialog;
import view.mainframe.MainFrame;

/**
 * 
 * @author kevin & ted
 * 
 */
public class InvokeSaveGameDialogController implements ActionListener {

	private MainFrame mainFrame;
	
	/**
	 * 
	 * @see
	 * 
	 */
	public InvokeSaveGameDialogController(MainFrame mainFrame) {
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
