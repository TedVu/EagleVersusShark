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
	private Set<String> sharkNames = new HashSet<String>();

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
		// register this controller to valid move cell
		// call relevant method on board/model to update board/model inside
		// actionPerform()

		boardPanel = (BoardPanel) evt.getNewValue();

		List<List<AbstractButton>> buttons = boardPanel.getButtonList();
		pieceType = (String) evt.getOldValue();

		validMoves = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).getValidMove();
		enableViewAvailableMove(buttons);
	}

	/**
	 * @param buttons
	 * @implNote This method does the colouring for available move cell, also
	 *           deregister the original selectPieceListener which is registered at
	 *           the start when creating board, and add move piece listener to allow
	 *           move action
	 * @see BoardPanel.java
	 */
	private void enableViewAvailableMove(List<List<AbstractButton>> buttons) {
		for (List<Integer> moves : validMoves) {
			if (eagleNames.contains(pieceType.toLowerCase())) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.yellow);
			} else if (sharkNames.contains(pieceType.toLowerCase())) {
				buttons.get(moves.get(1)).get(moves.get(0)).setBackground(Color.blue);
			}

			ActionListener[] selectPieceListener = buttons.get(moves.get(1)).get(moves.get(0)).getActionListeners();
			buttons.get(moves.get(1)).get(moves.get(0)).removeActionListener(selectPieceListener[0]);

			buttons.get(moves.get(1)).get(moves.get(0)).addActionListener(this);

		}
	}

	/**
	 * @param the selected button when moving piece
	 * @implNote This method is a little bit heavyweight, may need decouple,
	 *           extraction in later stage
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean notEnterAlly = true;

		notEnterAlly = checkIfMoveOnAlly(e, notEnterAlly);

		if (notEnterAlly) {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType)
					.getPosition();

			restoreViewForOldPosButton(oldPos);

			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			buttonClicked.setActionCommand(pieceType);

			Map<String, Integer> newPos = new HashMap<String, Integer>();

			updateModel(buttons, buttonClicked, newPos);

			EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).movePiece(newPos.get("x"),
					newPos.get("y"));

			updateIcon(buttonClicked);

			repaintWhiteCell(buttons);

			restoreStateForPossibileValidMoves(buttons);

			updateStateForNextTurn(buttons);
		} else if (!notEnterAlly) {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			restoreButtonStateForColoredButton(buttons);
			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			pieceType = buttonClicked.getActionCommand();
			validMoves = EngineImpl.getSingletonInstance().getAllPiecesTed().get(pieceType).getValidMove();

			repaintWhiteCell(buttons);
			enableViewAvailableMove(buttons);

		}
	}

	private void restoreViewForOldPosButton(Map<String, Integer> oldPos) {
		boardPanel.getButtonList().get(oldPos.get("y")).get(oldPos.get("x")).setIcon(null);

		// default action command of button is NormalButton, so reset the old postition
		// into the normal button to avoid any unwanted triggering of events
		boardPanel.getButtonList().get(oldPos.get("y")).get(oldPos.get("x")).setActionCommand("NormalButton");
	}

	private void updateModel(List<List<AbstractButton>> buttons, AbstractButton buttonClicked,
			Map<String, Integer> newPos) {
		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (buttons.get(i).get(j).equals(buttonClicked)) {
					newPos.put("y", i);
					newPos.put("x", j);
					break;
				}
			}
		}
	}

	private void updateStateForNextTurn(List<List<AbstractButton>> buttons) {
		if (eagleNames.contains(pieceType.toLowerCase())) {

			restoreButtonStateForNextTurn(buttons, eagleNames);

			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("shark", true);

		} else if (sharkNames.contains(pieceType.toLowerCase())) {
			// refresh state ready for next turn
			restoreButtonStateForNextTurn(buttons, sharkNames);
			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("eagle", true);

		}
	}

	private boolean checkIfMoveOnAlly(ActionEvent e, boolean notEnterAlly) {
		if (eagleNames.contains(pieceType.toLowerCase())) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (eagleNames.contains(buttonClicked.getActionCommand().toLowerCase())) {
				notEnterAlly = false;
			}
		} else if (sharkNames.contains(pieceType.toLowerCase())) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (sharkNames.contains(buttonClicked.getActionCommand().toLowerCase())) {
				notEnterAlly = false;

			}
		}
		return notEnterAlly;
	}

	private void restoreStateForPossibileValidMoves(List<List<AbstractButton>> buttons) {
		for (List<Integer> l : validMoves) {
			ActionListener[] movePieceController = buttons.get(l.get(1)).get(l.get(0)).getActionListeners();
			buttons.get(l.get(1)).get(l.get(0)).removeActionListener(movePieceController[0]);

			buttons.get(l.get(1)).get(l.get(0))
					.addActionListener(new SelectPieceController(buttons.get(l.get(1)).get(l.get(0)), boardPanel));

		}
	}

	private void updateIcon(AbstractButton buttonClicked) {
		Image animal = null;
		try {
			animal = ImageIO.read(getClass().getResource(Asset.fileName.get(pieceType)));
		} catch (IOException e1) {
			System.err.println("IMAGE NOT FOUND");
		}
		buttonClicked.setIcon(new ImageIcon(animal));
	}

	/**
	 * @param buttons
	 */
	private void restoreButtonStateForColoredButton(List<List<AbstractButton>> buttons) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if ((buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
					for (ActionListener l : listeners) {
						buttons.get(row).get(col).removeActionListener(l);
					}
					buttons.get(row).get(col)
							.addActionListener(new SelectPieceController(buttons.get(row).get(col), boardPanel));
				}
			}
		}
	}

	private void restoreButtonStateForNextTurn(List<List<AbstractButton>> buttons, Set<String> pieceName) {
		for (int i = 0; i < buttons.size(); ++i) {
			for (int j = 0; j < buttons.get(0).size(); ++j) {
				if (pieceName.contains(buttons.get(i).get(j).getActionCommand().toLowerCase())) {
					AbstractButton button = buttons.get(i).get(j);
					for (ActionListener l : button.getActionListeners()) {
						button.removeActionListener(l);
					}
					button.addActionListener(new SelectPieceController(button, boardPanel));
				}
			}
		}
	}

	/**
	 * @implNote: This method acts a rollback when user reselect another piece
	 * @param buttons
	 */
	private void repaintWhiteCell(List<List<AbstractButton>> buttons) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
				}
			}
		}
	}
}
