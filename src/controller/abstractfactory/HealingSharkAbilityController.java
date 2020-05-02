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
			PieceType affectedPieceEnum = PieceType.parsePieceType(healingDialog.getSharkRevived());
			super.controllerModelFacade.updateModelStateHealingSharkRevive(affectedPieceEnum);
			super.viewControllerFacade.updateBoardReviveSharkSuccessful(affectedPieceEnum);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
		} else {
			healingDialog.dispose();
			super.viewControllerFacade.updateBoardNotCorrectTurnToRevive();
		}
	}

	@Override
	public void setUpView() {
		List<PieceInterface> activeSharks = EngineImpl.getSingletonInstance().pieceOperator().getActiveSharks();

		if (activeSharks.size() == 3) {
			super.viewControllerFacade.updateBoardNoSharkToRevive();
		} else {
			healingDialog = new HealingSharkDialog(this);
		}
	}

	public void injectHealingDialog(HealingSharkDialog healingDialog) {
		this.healingDialog=healingDialog;
	}
}
