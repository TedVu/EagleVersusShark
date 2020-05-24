package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.engine.EngineImpl;
import view.configuration.LoadGameDialog;
import view.mainframe.AppMainFrame;
import view.mainframe.StartGameMainFrame;

public class LoadGameController implements ActionListener {

	private StartGameMainFrame startFrame;
	private LoadGameDialog loadGameDialog;

	public LoadGameController(StartGameMainFrame startFrame, LoadGameDialog loadGameDialog) {
		this.startFrame = startFrame;
		this.loadGameDialog = loadGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = loadGameDialog.getFileNameInput();

		String filename = nameCheck(s);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileInputStream file = new FileInputStream(filename);
					ObjectInputStream in = new ObjectInputStream(file);

					EngineImpl engine = (EngineImpl) in.readObject();

					in.close();
					file.close();

					EngineImpl.getSingletonInstance().loadGame(engine);
					
					final AppMainFrame window = new AppMainFrame();
					window.setVisible(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		startFrame.dispose();
		loadGameDialog.dispose();
	}

	private String nameCheck(String s) {
		if (s.length() == 3) {
			return s.concat(".ser");
		} else if (s.length() > 3) {
			if (s.substring(s.length() - 3).equals("ser")) {
				return s.substring(s.length() - 3);
			} else {
				return s.substring(s.length() - 3).concat(".ser");
			}
		} else {
			return s.concat(".ser");
		}
	}
}
