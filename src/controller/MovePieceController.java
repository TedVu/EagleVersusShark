package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import asset.PieceType;
import controllermodel.facade.ControllerModelFacade;
import controllermodel.interfaces.ControllerModelInterfaces;
import models.engine.EngineImpl;
import viewcontroller.interfaces.ViewControllerInterface;

/**
 * @author Ted
 * @implNote This controller handles event when user makes a move AFTER select a
 *           piece
 * @see documents on ggDrive for flow of events
 * 
 */
public class MovePieceController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterfaces controllerModelFacade=new ControllerModelFacade();
	
	
	private String pieceType;

	private EnumSet<PieceType> eagleSet = EnumSet.of(PieceType.ATTACKINGEAGLE, PieceType.LEADERSHIPEAGLE,
			PieceType.VISIONARYEAGLE);
	private EnumSet<PieceType> sharkSet = EnumSet.of(PieceType.AGGRESSIVESHARK, PieceType.DEFENSIVESHARK,
			PieceType.HEALINGSHARK);

	/**
	 * @param the
	 *            selected button when moving piece
	 * @implNote This method is a little bit heavyweight, may need decouple,
	 *           extraction in later stage
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean notEnterAlly = true;

		notEnterAlly = checkIfMoveOnAlly((AbstractButton) e.getSource(), notEnterAlly);
		if (notEnterAlly) {

			AbstractButton buttonClicked = (AbstractButton) e.getSource();
			viewControllerFacade.updateBoardMovingPiece(buttonClicked, pieceType);
			updateModel(buttonClicked);
			updateModelStateForNextTurn();

		} else if (!notEnterAlly) {
			AbstractButton buttonClicked = (AbstractButton) e.getSource();

			pieceType = buttonClicked.getActionCommand();
			viewControllerFacade.updateBoardRollback();
			viewControllerFacade.updateBoardAfterChoosingPiece(pieceType);
			viewControllerFacade.readdMovePieceController(pieceType, this);
		}
	}

	private boolean checkIfMoveOnAlly(AbstractButton buttonClicked, boolean notEnterAlly) {
		PieceType pieceTypeEnum = PieceType.valueOf(pieceType.toUpperCase());

		if (eagleSet.contains(pieceTypeEnum)) {

			if (!buttonClicked.getActionCommand().equals("NormalButton")
					&& eagleSet.contains(PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase()))) {
				notEnterAlly = false;
			}
		} else if (sharkSet.contains(pieceTypeEnum)) {
			if (!buttonClicked.getActionCommand().equals("NormalButton")
					&& sharkSet.contains(PieceType.valueOf(buttonClicked.getActionCommand().toUpperCase()))) {
				notEnterAlly = false;
			}
		}
		return notEnterAlly;
	}

	public void setUpControllerState(String pieceName, ViewControllerInterface facade) {
		pieceType = pieceName;
		this.viewControllerFacade = facade;
	}

	private void updateModel(AbstractButton buttonClicked) {

		Map<String, Integer> newPos = new HashMap<String, Integer>();
		viewControllerFacade.locateNewPos(buttonClicked, newPos);

		controllerModelFacade.updateModelAfterMovingPiece(newPos, pieceType);

	}

	private void updateModelStateForNextTurn() {
		PieceType pieceTypeEnum = PieceType.valueOf(pieceType.toUpperCase());
		if (eagleSet.contains(pieceTypeEnum)) {
			viewControllerFacade.restoreButtonStateForNextTurn(eagleSet);
			controllerModelFacade.updateModelStateForNextTurn("shark");

		} else if (sharkSet.contains(pieceTypeEnum)) {
			// refresh state ready for next turn
			viewControllerFacade.restoreButtonStateForNextTurn(sharkSet);
			controllerModelFacade.updateModelStateForNextTurn("eagle");
		}
	}

}
