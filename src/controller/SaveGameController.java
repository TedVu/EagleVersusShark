package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import models.engine.EngineImpl;
import view.configuration.SaveGameDialog;

public class SaveGameController implements ActionListener {

	private SaveGameDialog saveGameDialog;

	public SaveGameController(SaveGameDialog saveGameDialog) {
		this.saveGameDialog = saveGameDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (saveGameDialog.getFileNameInput().endsWith(".status")) {
			String fileName = saveGameDialog.getFileNameInput();
			try {
				PrintWriter writer = new PrintWriter(fileName);
				writer.print("BoardSize: ");
				writer.println(EngineImpl.getSingletonInstance().getBoard().getCol());
				writer.print("NumPiece: ");
				writer.println(EngineImpl.getSingletonInstance().getAllPieces().size());

				for (String pieceName : EngineImpl.getSingletonInstance().getAllPieces().keySet()) {
					writer.print(pieceName);
					writer.print(" ");
					writer.print(
							EngineImpl.getSingletonInstance().getAllPieces().get(pieceName).getPosition().get("x"));
					writer.print(" ");
					writer.println(
							EngineImpl.getSingletonInstance().getAllPieces().get(pieceName).getPosition().get("y"));
				}
				writer.print("CurrentTurn: ");
				writer.println(EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType());

				writer.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(saveGameDialog, "SaveGameSuccessful");
			saveGameDialog.dispose();
		} else {
			JOptionPane.showMessageDialog(saveGameDialog, "File name to save must ends with .status");

		}

	}

}
