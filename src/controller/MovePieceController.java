package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

public class MovePieceController implements PropertyChangeListener, ActionListener {

	private BoardPanel boardPanel;
	private Map<String, String> fileName = new HashMap<String, String>();
	private String pieceType;
	private Set<List<Integer>> validMoves;

	/**
	 * REFACTORING - Change to relative path
	 */
	public MovePieceController() {
		fileName.put("AttackingEagle", "/asset/AttackingEagle.png");
		fileName.put("LeadershipEagle", "/asset/LeadershipEagle.png");
		fileName.put("VisionaryEagle", "/asset/VisionaryEagle.png");

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// call timer on Model
		// get valid move of specific piece from model & evt.getNewValue()
		// disable invalid cell move
		// remove select piece listener on these valid move button (instead of having a
		// boolean check)
		// register this controller to valid move cell
		// call relevant method on board/model to update board/model inside
		// actionPerform()
		boardPanel = (BoardPanel) evt.getNewValue();

		List<List<AbstractButton>> buttons = boardPanel.getButtonList();
		pieceType = (String) evt.getOldValue();
		validMoves = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).getValidMove();
		System.out.println(validMoves);

		// enable available move
		for (List<Integer> l : validMoves) {
			buttons.get(l.get(0)).get(l.get(1)).setBackground(Color.yellow);

			// remove old listener
			ActionListener[] selectPieceListener = buttons.get(l.get(0)).get(l.get(1)).getActionListeners();
			buttons.get(l.get(0)).get(l.get(1)).removeActionListener(selectPieceListener[0]);

			// add new listener
			buttons.get(l.get(0)).get(l.get(1)).addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Update Model
		// Call method on view to update model
		List<List<AbstractButton>> buttons = boardPanel.getButtonList();

		Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).getPosition();
		boardPanel.getButtonList().get(oldPos.get("x")).get(oldPos.get("y")).setIcon(null);

		// default action command of button is NormalButton
		boardPanel.getButtonList().get(oldPos.get("x")).get(oldPos.get("y")).setActionCommand("NormalButton");

		// Identify button being clicked
		AbstractButton buttonClicked = (AbstractButton) e.getSource();
		buttonClicked.setActionCommand(pieceType);

		Map<String, Integer> newPos = new HashMap<String, Integer>();

		// get coordinate to update model
		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (buttons.get(i).get(j).equals(buttonClicked)) {
					newPos.put("x", i);
					newPos.put("y", j);
					break;
				}
			}
		}
		EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).movePiece(newPos.get("x"), newPos.get("y"));

		// Update view here
		Image animal = null;
		try {
			animal = ImageIO.read(getClass().getResource(fileName.get(pieceType)));
		} catch (IOException e1) {
			System.err.println("IMAGE NOT FOUND");
		}
		buttonClicked.setIcon(new ImageIcon(animal));

		// Allow button to be clicked again
		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				buttons.get(i).get(j).setEnabled(true);
				buttons.get(i).get(j).setBackground(Color.WHITE);
			}
		}

		// Remove MovePieceController and Reregister SelectPieceController for valid
		// buttons
		for (List<Integer> l : validMoves) {
			ActionListener[] movePieceController = buttons.get(l.get(0)).get(l.get(1)).getActionListeners();
			buttons.get(l.get(0)).get(l.get(1)).removeActionListener(movePieceController[0]);

			buttons.get(l.get(0)).get(l.get(1))
					.addActionListener(new SelectPieceController(buttons.get(l.get(0)).get(l.get(1)), boardPanel));

		}

	}

}
