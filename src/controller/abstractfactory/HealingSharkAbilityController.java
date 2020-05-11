package controller.abstractfactory;

import java.awt.event.ActionEvent;
import java.util.List;

import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import view.operationview.HealingSharkDialog;

public class HealingSharkAbilityController extends AbstractAbilityController {

	private HealingSharkDialog healingDialog;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EngineImpl.getSingletonInstance().getCurrentActivePlayer().getPlayerType() == TeamType.SHARK) {

			healingDialog.dispose();

			boolean reviveSuccess = true;
			PieceType affectedPieceEnum = PieceType.parsePieceType(healingDialog.getSharkRevived());

			try {
				super.controllerModelFacade.updateModelStateHealingSharkRevive(affectedPieceEnum);
			} catch (RuntimeException ex) {
				reviveSuccess = false;
				super.viewControllerFacade.updateBoardAlreadyUseReviveLastRound(ex.getMessage());
			}
			if (reviveSuccess) {
				super.viewControllerFacade.updateBoardReviveSharkSuccessful(affectedPieceEnum);
				super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
			}
		} else {
			healingDialog.dispose();
			super.viewControllerFacade.updateBoardNotCorrectTurnToRevive();
		}
	}

	@Override
	public void setUpViewForAbility() {
		List<PieceInterface> activeSharks = EngineImpl.getSingletonInstance().pieceOperator().getActiveSharks();

		if (activeSharks.size() == EngineImpl.getSingletonInstance().getTotalNumPiece() / 2) {
			super.viewControllerFacade.updateBoardNoSharkToRevive();
		} else {
			healingDialog = new HealingSharkDialog(this);
		}
	}

	public void injectHealingDialog(HealingSharkDialog healingDialog) {
		this.healingDialog = healingDialog;
	}
}
