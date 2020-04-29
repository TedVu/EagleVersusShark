package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.LoadGameDialog;
import view.mainframe.StartingGameFrame;

public class InvokeLoadGameDialogController implements ActionListener {

	private StartingGameFrame startFrame;

	public InvokeLoadGameDialogController(StartingGameFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new LoadGameDialog(startFrame);
	}

}
