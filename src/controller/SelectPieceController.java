package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import view.operationview.BoardPanel;
import viewcontroller.contract.ViewControllerInterface;

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
			viewControllerFacade.updateBoardSelectAnotherPiece(buttonClicked);
			checkPieceSelectedTurn();
		} else {
			viewControllerFacade.notifyNotStartGame();
		}
	}

	@Requires({ "buttonClicked != null", "teamType != null", "movePieceController != null",
			"viewControllerFacade != null" })
	private void checkAllowTransitToMovePieceAction(TeamType teamType) {
		if (PieceType.parsePieceType(buttonClicked.getActionCommand()).team() == teamType) {
			movePieceController.setUpControllerState(PieceType.parsePieceType(buttonClicked.getActionCommand()),
					viewControllerFacade);

			viewControllerFacade.updateBoardBeforeMovePiece(buttonClicked, movePieceController);
		} else {
			viewControllerFacade.notifySelectWrongTeam();
		}
	}

	@Requires({ "buttonClicked != null" })
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
