package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.configuration.LoadGameDialog;

public class InvokeLoadGameDialogController implements ActionListener {

	private JFrame startFrame;

	public InvokeLoadGameDialogController(JFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		new LoadGameDialog(startFrame);
	}

}
