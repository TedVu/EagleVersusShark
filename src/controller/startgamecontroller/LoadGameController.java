package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.configuration.LoadGameDialog;
import view.mainframe.AppMainFrame;
import view.mainframe.StartingGameFrame;

public class LoadGameController implements ActionListener {

	private StartingGameFrame startFrame;
	private LoadGameDialog loadGameDialog;

	public LoadGameController(StartingGameFrame startFrame, LoadGameDialog loadGameDialog) {
		this.startFrame = startFrame;
		this.loadGameDialog = loadGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// perform validation file exist
		// calling methods to set up model
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AppMainFrame window = new AppMainFrame();
					window.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		startFrame.dispose();
		loadGameDialog.dispose();
	}

}
