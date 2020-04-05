package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.SaveGameDialog;
import view.mainframe.MainAppFrame;

public class InvokeSaveGameDialogController implements ActionListener {

	private MainAppFrame mainFrame;

	public InvokeSaveGameDialogController(MainAppFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		new SaveGameDialog(mainFrame);
	}

}
