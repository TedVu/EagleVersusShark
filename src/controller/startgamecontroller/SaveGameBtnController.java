package controller.startgamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.contract.EngineInterface;
import model.engine.EngineImpl;

public class SaveGameBtnController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String filename = "file.ser";
		EngineInterface engine = EngineImpl.getSingletonInstance();

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
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
