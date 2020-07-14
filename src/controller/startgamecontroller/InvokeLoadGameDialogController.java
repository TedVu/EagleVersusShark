package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.LoadGameDialog;
import view.mainframe.StartGameMainFrame;

/**
 * @author Ted & Kevin
 * 
 *         A controller to invoke loadgame dialog at the start panel
 *
 */
public class InvokeLoadGameDialogController implements ActionListener {

	private StartGameMainFrame startFrame;

	public InvokeLoadGameDialogController(StartGameMainFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new LoadGameDialog(startFrame);
	}

}
