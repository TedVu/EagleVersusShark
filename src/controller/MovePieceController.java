package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;

import asset.PieceType;
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
	private PieceType pieceType;
	
	private Set<List<Integer>> validMoves;
	
	private EnumSet<PieceType> eagleSet = EnumSet.of(PieceType.ATTACKINGEAGLE, PieceType.LEADERSHIPEAGLE,
			PieceType.VISIONARYEAGLE);
	private EnumSet<PieceType> sharkSet = EnumSet.of(PieceType.AGGRESSIVESHARK, PieceType.DEFENSIVESHARK,
			PieceType.HEALINGSHARK);

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

			Map<String, Integer> oldPos = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString()).getPosition();

			boardPanel.restoreViewForOldPos(oldPos);

			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			buttonClicked.setActionCommand(pieceType.toString());

			Map<String, Integer> newPos = new HashMap<String, Integer>();

			updateModel(buttons, buttonClicked, newPos);

			EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString()).movePiece(newPos.get("x"), newPos.get("y"));

			boardPanel.updateBoardAfterMovingPiece(buttonClicked, pieceType, validMoves);

			updateModelStateForNextTurn(buttons);
		} else if (!notEnterAlly) {
			List<List<AbstractButton>> buttons = boardPanel.getButtonList();

			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			pieceType = PieceType.valueOf(buttonClicked.getActionCommand().toString());
			validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString()).getValidMove();

			boardPanel.updateBoardRollback();
			enableViewAvailableMove(buttons);

		}
	}

	private boolean checkIfMoveOnAlly(ActionEvent e, boolean notEnterAlly) {
		if (eagleSet.contains(pieceType)) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (!buttonClicked.getActionCommand().equals("NormalButton")
					&& eagleSet.contains(PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase()))) {
				notEnterAlly = false;
			}
		} else if (sharkSet.contains(pieceType)) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			if (!buttonClicked.getActionCommand().equals("NormalButton")
					&& sharkSet.contains(PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase()))) {
				notEnterAlly = false;

			}
		}
		return notEnterAlly;
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equalsIgnoreCase("movepiece")) {

			boardPanel = (BoardPanel) evt.getNewValue();

			List<List<AbstractButton>> buttons = boardPanel.getButtonList();
			pieceType = PieceType.valueOf(evt.getOldValue().toString().toUpperCase());

			validMoves = EngineImpl.getSingletonInstance().getAllPieces().get(pieceType.toString()).getValidMove();
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

		if (eagleSet.contains(pieceType)) {
			boardPanel.restoreButtonStateForNextTurn(eagleSet);

			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("shark", true);

		} else if (sharkSet.contains(pieceType)) {
			// refresh state ready for next turn
			boardPanel.restoreButtonStateForNextTurn(sharkSet);
			EngineImpl.getSingletonInstance().cancelTimer();
			EngineImpl.getSingletonInstance().setActivePlayer("eagle", true);

		}
	}

}
