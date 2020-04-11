package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import asset.PieceType;
import models.engine.EngineImpl;
import view.operationview.BoardPanel;
import viewcontroller.interfaces.ViewControllerInterface;

/**
 * @author Ted
 * @implNote Invoked when user choose a piece before making a move.<br>
 *           NOTE: we separate into two actions Select a Piece and Make a Move
 * 
 * @see documents on ggDrive for flow of events
 */
public class SelectPieceController implements ActionListener {

	private AbstractButton buttonClicked;
	private MovePieceController movePieceController;

	private ViewControllerInterface viewControllerFacade;

	public SelectPieceController(ViewControllerInterface facade, BoardPanel boardView) {
		this.viewControllerFacade = facade;
		movePieceController = new MovePieceController();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// only allow button clicked after start game
		this.buttonClicked = (AbstractButton) e.getSource();

		if (EngineImpl.getSingletonInstance().getStartGame()) {
			viewControllerFacade.rollbackSelectedPiece(buttonClicked);
			checkPieceSelectedTurn();

		} else {
			viewControllerFacade.notifyNotStartGame();
		}
	}

	private void checkAllowTransitToMovePieceAction(String pieceName1, String pieceName2, String pieceName3) {
		if (buttonClicked.getActionCommand().equalsIgnoreCase(pieceName1)
				|| buttonClicked.getActionCommand().equalsIgnoreCase(pieceName2)
				|| buttonClicked.getActionCommand().equalsIgnoreCase(pieceName3)) {

			movePieceController.setUpControllerState(buttonClicked.getActionCommand(), viewControllerFacade);
			viewControllerFacade.enableAvailableMove(buttonClicked);
			viewControllerFacade.addListenerOnValidMovesCell(buttonClicked, movePieceController);
		} else {
			viewControllerFacade.notifySelectWrongTeam();
		}
	}

	private void checkPieceSelectedTurn() {
		if (EngineImpl.getSingletonInstance().checkSelectPiece(buttonClicked.getActionCommand())) {

			if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType()
					.equalsIgnoreCase(PieceType.getSharkTeamName())) {
				checkAllowTransitToMovePieceAction(PieceType.AGGRESSIVESHARK.toString(),
						PieceType.DEFENSIVESHARK.toString(), PieceType.HEALINGSHARK.toString());

			} else {
				checkAllowTransitToMovePieceAction(PieceType.ATTACKINGEAGLE.toString(),
						PieceType.LEADERSHIPEAGLE.toString(), PieceType.VISIONARYEAGLE.toString());
			}
		}
	}

}
