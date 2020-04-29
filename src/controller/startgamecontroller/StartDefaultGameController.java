package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainframe.AppMainFrame;
import view.mainframe.StartGameMainFrame;

public class StartDefaultGameController implements ActionListener {

	private StartGameMainFrame startFrame;

	public StartDefaultGameController(StartGameMainFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
	}

}
