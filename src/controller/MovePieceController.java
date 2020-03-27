package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

/**
 * @author Ted
 * @implNote This controller handles event when user makes a move AFTER select a
 *           piece
 * @see documents on ggDrive for flow of events
 * 
 */
public class MovePieceController implements PropertyChangeListener, ActionListener {

	private BoardPanel boardPanel;
	private String pieceType;
	private Set<List<Integer>> validMoves;
	private Set<String> eagleNames = new HashSet<String>();
	private Set<String> sharkNames = new HashSet<>();

	/**
	 * REFACTORING - Change to relative path
	 */
	public MovePieceController() {
		Asset.populate();

		eagleNames.add("attackingeagle");
		eagleNames.add("visionaryeagle");
		eagleNames.add("leadershipeagle");

		sharkNames.add("aggressiveshark");
		sharkNames.add("defensiveshark");
		sharkNames.add("healingshark");

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

		// Casting is allowed since we always know we get a BoardPanel
		boardPanel = (BoardPanel) evt.getNewValue();

		List<List<AbstractButton>> buttons = boardPanel.getButtonList();
		pieceType = (String) evt.getOldValue();

		validMoves = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).getValidMove();
		// enable available move, focusing on the VIEW-CONTROLLER
		enableViewAvailableMove(buttons);
	}

	/**
	 * @param buttons
	 * @implNote This method does the colouring for available move cell, also
	 *           deregister the original selectPieceListener which is registered at
	 *           the start when creating board
	 * @see BoardPanel.java
	 */
	private void enableViewAvailableMove(List<List<AbstractButton>> buttons) {
		for (List<Integer> l : validMoves) {
			if (eagleNames.contains(pieceType.toLowerCase())) {
				buttons.get(l.get(1)).get(l.get(0)).setBackground(Color.yellow);
			} else if (sharkNames.contains(pieceType.toLowerCase())) {
				buttons.get(l.get(1)).get(l.get(0)).setBackground(Color.blue);
			}
			ActionListener[] selectPieceListener = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
			buttons.get(l.get(1)).get(l.get(0)).removeActionListener(selectPieceListener[0]);

			buttons.get(l.get(1)).get(l.get(0)).addActionListener(this);
		}
	}

	/**
	 * @param the
	 *            selected button when moving piece
	 * @implNote This method is a little bit heavy weight, may need decouple,
	 *           extraction in later stage
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean notEnterAlly = true;
		if (pieceType.equalsIgnoreCase("attackingeagle") || pieceType.equalsIgnoreCase("leadershipeagle")
				|| pieceType.equalsIgnoreCase("visionaryeagle")) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (buttonClicked.getActionCommand().equalsIgnoreCase("attackingeagle")
					|| buttonClicked.getActionCommand().equalsIgnoreCase("leadershipeagle")
					|| buttonClicked.getActionCommand().equalsIgnoreCase("visionaryeagle")) {
				notEnterAlly = false;
			}
		} else {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (buttonClicked.getActionCommand().equalsIgnoreCase("aggressiveshark")
					|| buttonClicked.getActionCommand().equalsIgnoreCase("defensiveshark")
					|| buttonClicked.getActionCommand().equalsIgnoreCase("healingshark")) {
				notEnterAlly = false;
			}
		}

		if (notEnterAlly) {
			// Call method on view to update model
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			// before setting a new position for piece in model store the old position for
			// view update
			Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType)
					.getPosition();

			// update view here basically remove the icon
			boardPanel.getButtonList().get(oldPos.get("y")).get(oldPos.get("x")).setIcon(null);

			// default action command of button is NormalButton, so reset the old postition
			// into the normal button to avoid any unwanted triggering of events
			boardPanel.getButtonList().get(oldPos.get("y")).get(oldPos.get("x")).setActionCommand("NormalButton");

			// Identify button being clicked
			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			// The piece has been moved, remember to setActionCommand() for next turn
			buttonClicked.setActionCommand(pieceType);

			Map<String, Integer> newPos = new HashMap<String, Integer>();

			// get coordinate to update model
			for (int i = 0; i < buttons.size(); ++i) {
				for (int j = 0; j < buttons.get(0).size(); ++j) {
					if (buttons.get(i).get(j).equals(buttonClicked)) {
						newPos.put("y", i);
						newPos.put("x", j);
						break;
					}
				}
			}

			// call movePiece to update model - including board + piece position
			// @see EngineImpl
			EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).movePiece(newPos.get("x"),
					newPos.get("y"));

			// Update icon on view here
			Image animal = null;
			try {
				animal = ImageIO.read(getClass().getResource(Asset.fileName.get(pieceType)));
			} catch (IOException e1) {
				System.err.println("IMAGE NOT FOUND");
			}
			buttonClicked.setIcon(new ImageIcon(animal));

			// Allow button to be clicked again
			for (int row = 0; row < buttons.size(); ++row) {
				for (int col = 0; col < buttons.get(0).size(); ++col) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
				}
			}

			// Remove MovePieceController and Reregister SelectPieceController for valid
			// buttons
			for (List<Integer> l : validMoves) {
				ActionListener[] movePieceController = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
				buttons.get(l.get(1)).get(l.get(0)).removeActionListener(movePieceController[0]);

				buttons.get(l.get(1)).get(l.get(0))
						.addActionListener(new SelectPieceController(buttons.get(l.get(1)).get(l.get(0)), boardPanel));

			}

			// turn based occur here
			if (pieceType.equalsIgnoreCase("attackingeagle") || pieceType.equalsIgnoreCase("leadershipeagle")
					|| pieceType.equalsIgnoreCase("visionaryeagle")) {

				for (int i = 0; i < buttons.size(); ++i) {
					for (int j = 0; j < buttons.get(0).size(); ++j) {
						if (sharkNames.contains(buttons.get(i).get(j).getActionCommand())) {
							AbstractButton button = buttons.get(i).get(j);
							for (ActionListener l : button.getActionListeners()) {
								button.removeActionListener(l);
							}
							button.addActionListener(new SelectPieceController(button, boardPanel));
						}
					}
				}
				EngineImpl.getSingletonInstance().setActivePlayer("shark", false);


			} else {
				System.out.println("MOVE");
				
				for (int i = 0; i < buttons.size(); ++i) {
					for (int j = 0; j < buttons.get(0).size(); ++j) {
						if (eagleNames.contains(buttons.get(i).get(j).getActionCommand())) {
							AbstractButton button = buttons.get(i).get(j);
							for (ActionListener l : button.getActionListeners()) {
								button.removeActionListener(l);
							}
							button.addActionListener(new SelectPieceController(button, boardPanel));
						}
					}
				}
				EngineImpl.getSingletonInstance().setActivePlayer("eagle", false);

			}
		} else {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			pieceType = buttonClicked.getActionCommand();
			validMoves = EngineImpl.getSingletonInstance().getAllPiecesTed().get(buttonClicked.getActionCommand())
					.getValidMove();

			for (int row = 0; row < buttons.size(); ++row) {
				for (int col = 0; col < buttons.get(0).size(); ++col) {
					if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
							|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
						buttons.get(row).get(col).setBackground(Color.WHITE);
					}
				}
			}
			enableViewAvailableMove(buttons);

		}
	}
}
