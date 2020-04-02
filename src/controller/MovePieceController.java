package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;

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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equalsIgnoreCase("movepiece")) {

			boardPanel = (BoardPanel) evt.getNewValue();

			List<List<AbstractButton>> buttons = boardPanel.getButtonList();
			pieceType = (String) evt.getOldValue();

			validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();
			enableViewAvailableMove(buttons);
		}
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
		boardPanel.updateBoardAfterChoosingPiece(validMoves, pieceType);
		for (List<Integer> moves : validMoves) {
			buttons.get(moves.get(1)).get(moves.get(0)).addActionListener(this);
		}
	}

	/**
	 * @param the
	 *            selected button when moving piece
	 * @implNote This method is a little bit heavyweight, may need decouple,
	 *           extraction in later stage
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean notEnterAlly = true;

		notEnterAlly = checkIfMoveOnAlly(e, notEnterAlly);

		if (notEnterAlly) {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getPosition();

			boardPanel.restoreViewForOldPos(oldPos);

			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			buttonClicked.setActionCommand(pieceType);

			Map<String, Integer> newPos = new HashMap<String, Integer>();

			updateModel(buttons, buttonClicked, newPos);

			EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).movePiece(newPos.get("x"), newPos.get("y"));

			boardPanel.updateBoardAfterMovingPiece(buttonClicked, pieceType, validMoves);

			updateModelStateForNextTurn(buttons);
		} else if (!notEnterAlly) {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			pieceType = buttonClicked.getActionCommand();
			validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType).getValidMove();

			boardPanel.updateBoardRollback();
			enableViewAvailableMove(buttons);

		}
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

	private void updateModelStateForNextTurn(List<List<AbstractButton>> buttons) {
		if (AssetHelper.eagleNames.contains(pieceType.toLowerCase())) {

			boardPanel.restoreButtonStateForNextTurn(AssetHelper.eagleNames);

			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("shark", true);

		} else if (AssetHelper.sharkNames.contains(pieceType.toLowerCase())) {
			// refresh state ready for next turn
			boardPanel.restoreButtonStateForNextTurn(AssetHelper.sharkNames);
			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("eagle", true);

		}
	}

	private boolean checkIfMoveOnAlly(ActionEvent e, boolean notEnterAlly) {
		if (AssetHelper.eagleNames.contains(pieceType.toLowerCase())) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (AssetHelper.eagleNames.contains(buttonClicked.getActionCommand().toLowerCase())) {
				notEnterAlly = false;
			}
		} else if (AssetHelper.sharkNames.contains(pieceType.toLowerCase())) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (AssetHelper.sharkNames.contains(buttonClicked.getActionCommand().toLowerCase())) {
				notEnterAlly = false;

			}
		}
		return notEnterAlly;
	}

}
