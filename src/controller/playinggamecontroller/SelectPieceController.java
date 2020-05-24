package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import controller.abstractfactory.SpecialBehaviourControllerFactory;
import controller.abstractfactory.contract.AbilityController;
import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.contract.ModeController;
import controller.abstractfactory.contract.ModeControllerFactory;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.PlayerActionType;
import model.enumtype.TeamType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import viewcontroller.contract.ViewControllerInterface;

/**
 * Invoked when user choose a piece before making a move.<br>
 * NOTE: we separate into two actions Select a Piece and Make a Move
 * 
 * @author ted &#38; kevin
 * 
 */
public class SelectPieceController implements ActionListener {

	private AbstractButton btnClicked;
	private MovePieceController movePieceController;

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

	private PlayerAction playerAction;

	/**
	 * @param facade
	 * @param boardView
	 */
	public SelectPieceController(ViewControllerInterface facade) {
		this.viewControllerFacade = facade;
		playerAction = new PlayerAction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.btnClicked = (AbstractButton) e.getSource();

		if (EngineImpl.getSingletonInstance().gameTurn().getGameCurrentlyRunning()) {
			if (!btnClicked.getActionCommand().equalsIgnoreCase("NormalButton")) {
				viewControllerFacade.refreshBoard();
				checkCorrectPieceButtonClicked();
			}
		} else {
			viewControllerFacade.updateBoardNotiDialog("Game not running");
		}
	}

	@Requires({ "teamType != null", "viewControllerFacade != null" })
	private void checkChooseCorrectTeamTurn(TeamType teamType) {
		if (PieceType.parsePieceType(btnClicked.getActionCommand()).team() == teamType) {

			viewControllerFacade.getPlayerAction(playerAction);
			PlayerActionType playerActionType = PlayerActionType.parsePlayerActionType(playerAction.getPlayerAction());

			routePlayerAction(playerActionType, teamType);
		} else {
			viewControllerFacade.updateBoardNotiDialog("Select wrong team");
		}
	}

	private void routePlayerAction(PlayerActionType playerActionType, TeamType teamType) {
		if (playerActionType == PlayerActionType.MOVE) {
			movePieceController = new MovePieceController(PieceType.parsePieceType(btnClicked.getActionCommand()),
					viewControllerFacade);
			viewControllerFacade.updateBoardBeforeCommitAction(movePieceController,
					PieceType.parsePieceType(btnClicked.getActionCommand()));
		} else if (playerActionType == PlayerActionType.USEABILITY) {
			useAbilityViewController(teamType);

		} else if (playerActionType == PlayerActionType.EAGLEMODE) {
			useModeViewController(TeamType.EAGLE);
		} else if (playerActionType == PlayerActionType.SHARKMODE) {
			useModeViewController(TeamType.SHARK);
		}
	}

	private void useModeViewController(TeamType teamType) {
		ModeControllerFactory modeFactory = SpecialBehaviourControllerFactory
				.getSpecialBehaviourControllerFactory(teamType).createModeControllerFactory();

		ModeController modeController = modeFactory
				.createModeController(PieceType.parsePieceType(btnClicked.getActionCommand()));
		modeController.setModeState(viewControllerFacade);
		modeController.setUpViewForMode(PieceType.parsePieceType(btnClicked.getActionCommand()));
	}

	private void useAbilityViewController(TeamType teamType) {
		AbilityControllerFactory abilityFactory = SpecialBehaviourControllerFactory
				.getSpecialBehaviourControllerFactory(teamType).createAbilityControllerFactory();
		AbilityController abilityController = abilityFactory
				.createAbilityController(PieceType.parsePieceType(btnClicked.getActionCommand()));
		abilityController.setAbilityState(viewControllerFacade);
		abilityController.setUpViewForAbility(PieceType.parsePieceType(btnClicked.getActionCommand()));
	}

	@Requires({ "btnClicked != null", "controllerModelFacade != null" })
	private void checkCorrectPieceButtonClicked() {
		if (controllerModelFacade
				.checkCorrectTurnOfSelectedPiece(PieceType.parsePieceType(btnClicked.getActionCommand()))) {
			TeamType currentTurn = EngineImpl.getSingletonInstance().gameTurn().getCurrentActivePlayer()
					.getPlayerType();
			checkChooseCorrectTeamTurn(currentTurn);

		}
	}

}
