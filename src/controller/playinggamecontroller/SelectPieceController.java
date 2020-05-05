package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import controller.abstractfactory.AbilityController;
import controller.abstractfactory.AbilityControllerFactory;
import controller.abstractfactory.ModeController;
import controller.abstractfactory.ModeControllerFactory;
import controller.abstractfactory.SpecialBehaviourControllerFactory;
import model.contract.EngineInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.PlayerActionType;
import model.enumtype.TeamType;
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
	private EngineInterface engine = EngineImpl.getSingletonInstance();

	private ViewControllerInterface viewControllerFacade;

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
		this.buttonClicked = (AbstractButton) e.getSource();

		if (EngineImpl.getSingletonInstance().getGameCurrentlyRunning()) {
			if (!buttonClicked.getActionCommand().equalsIgnoreCase("NormalButton")) {
				viewControllerFacade.updateBoardSelectAnotherPiece(buttonClicked);
				checkCorrectPieceButtonClicked();
			}
		} else {
			viewControllerFacade.notifyGameNotRunning();
		}
	}

	@Requires({ "teamType != null", "viewControllerFacade != null" })
	private void checkChooseCorrectTeamTurn(TeamType teamType) {
		if (PieceType.parsePieceType(buttonClicked.getActionCommand()).team() == teamType) {

			viewControllerFacade.getPlayerAction(playerAction);
			PlayerActionType playerActionType = PlayerActionType.parsePlayerActionType(playerAction.getPlayerAction());

			routePlayerAction(playerActionType, teamType);
		} else {
			viewControllerFacade.notifySelectWrongTeam();
		}
	}

	private void routePlayerAction(PlayerActionType playerActionType, TeamType teamType) {
		if (playerActionType == PlayerActionType.MOVE) {
			movePieceController = new MovePieceController(PieceType.parsePieceType(buttonClicked.getActionCommand()),
					viewControllerFacade);
			viewControllerFacade.updateBoardBeforeMovePiece(buttonClicked, movePieceController);
		} else if (playerActionType == PlayerActionType.USEABILITY) {
			useAbilityViewController(teamType);

		} else if (playerActionType == PlayerActionType.SKYMODE) {
			useModeViewController(teamType);
		} else if (playerActionType == PlayerActionType.PROTECTIONMODE) {
			useModeViewController(teamType);
		}
	}

	private void useModeViewController(TeamType teamType) {
		ModeControllerFactory modeFactory = SpecialBehaviourControllerFactory
				.getSpecialBehaviourControllerFactory(teamType).createModeControllerFactory();

		ModeController modeController = modeFactory
				.createModeController(PieceType.parsePieceType(buttonClicked.getActionCommand()));
		modeController.setState(viewControllerFacade);
		modeController.setUpView();
	}

	private void useAbilityViewController(TeamType teamType) {
		AbilityControllerFactory abilityFactory = SpecialBehaviourControllerFactory
				.getSpecialBehaviourControllerFactory(teamType).createAbilityControllerFactory();
		AbilityController abilityController = abilityFactory
				.createAbilityController(PieceType.parsePieceType(buttonClicked.getActionCommand()));
		abilityController.setState(viewControllerFacade);
		abilityController.setUpView();
	}

	@Requires("buttonClicked != null")
	private void checkCorrectPieceButtonClicked() {
		if (engine.pieceOperator().checkSelectPiece(PieceType.parsePieceType(buttonClicked.getActionCommand()))) {
			TeamType currentTurn = EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType();
			checkChooseCorrectTeamTurn(currentTurn);

		}
	}

}
