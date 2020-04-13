package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import asset.PieceType;
import asset.TeamType;
import models.engine.EngineImpl;
import view.operationview.BoardPanel;
import viewcontroller.interfaces.ViewControllerInterface;

/**
 * Invoked when user choose a piece before making a move.<br>
 * NOTE: we separate into two actions Select a Piece and Make a Move
 * 
 * @author ted &#38; kevin
 * 
 */
public class SelectPieceController implements ActionListener {

	private AbstractButton buttonClicked;
	private MovePieceController movePieceController;

	private ViewControllerInterface viewControllerFacade;

	/**
	 * @param facade
	 * @param boardView
	 */
	public SelectPieceController(ViewControllerInterface facade, BoardPanel boardView) {
		this.viewControllerFacade = facade;
		movePieceController = new MovePieceController();
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonClicked = (AbstractButton) e.getSource();

		if (EngineImpl.getSingletonInstance().getStartGame()) {
			viewControllerFacade.rollbackSelectedPiece(buttonClicked);
			checkPieceSelectedTurn();
		} else {
			viewControllerFacade.notifyNotStartGame();
		}
	}

	private void checkAllowTransitToMovePieceAction(TeamType teamType) {
		if (PieceType.parsePieceType(buttonClicked.getActionCommand()).team() == teamType) {
			movePieceController.setUpControllerState(PieceType.parsePieceType(buttonClicked.getActionCommand()),
					viewControllerFacade);
			viewControllerFacade.enableAvailableMove(buttonClicked);
			viewControllerFacade.addListenerOnValidMovesCell(buttonClicked, movePieceController);
		} else {
			viewControllerFacade.notifySelectWrongTeam();
		}
	}

	private void checkPieceSelectedTurn() {
		if (EngineImpl.getSingletonInstance()
				.checkSelectPiece(PieceType.parsePieceType(buttonClicked.getActionCommand()))) {
			if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType() == TeamType.SHARK) {
				checkAllowTransitToMovePieceAction(TeamType.SHARK);
			} else if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType() == TeamType.EAGLE) {
				checkAllowTransitToMovePieceAction(TeamType.EAGLE);
			}

		}
	}

}
