package controller.startgamecontroller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

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
		String filename = loadGameDialog.getFileNameInput();

		boolean fileExist = true;
		try {
			FileInputStream file = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			fileExist = false;
			JOptionPane.showMessageDialog(loadGameDialog, "No file found in the system\nPlease check filename (appending  .ser)");

		}
		if (fileExist) {
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
						JOptionPane.showMessageDialog(loadGameDialog, "No file found in the system");

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
		
	}

}
