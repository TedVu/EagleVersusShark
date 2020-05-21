package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.contract.Engine;
import model.engine.EngineImpl;
import view.configuration.SaveGameDialog;
import viewcontroller.contract.ViewControllerInterface;

public class SaveGameBtnController implements ActionListener {

	private SaveGameDialog saveGameDialog;
	private ViewControllerInterface viewControllerFacade;

	public SaveGameBtnController(SaveGameDialog saveGameDialog, ViewControllerInterface viewControllerFacade) {
		this.saveGameDialog = saveGameDialog;
		this.viewControllerFacade = viewControllerFacade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = saveGameDialog.getFileNameInput();

		String filename = nameCheck(s);

		Engine engine = EngineImpl.getSingletonInstance();

		// Serialization
		try {
			// Saving of object in a file
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(engine);
			out.close();
			file.close();
			System.out.println("Object has been serialized");
			viewControllerFacade.updateBoardNotiDialog("Save game successful !!! Please resume game");
			saveGameDialog.dispose();
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}
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
