package viewcontroller.facade;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import controller.abstractfactory.AbilityController;
import controller.abstractfactory.ModeController;
import controller.playinggamecontroller.MovePieceController;
import controller.playinggamecontroller.PlayerAction;
import model.board.Cell;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author kevin & ted
 *
 */
public class ViewControllerFacade implements ViewControllerInterface {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	@Requires("listener != null")
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	@Requires({ "buttonClicked != null", "newPos != null" })
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos) {
		pcs.firePropertyChange("LocateNewPosition", buttonClicked, newPos);
	}

	@Override
	public void notifyGameNotRunning() {
		pcs.firePropertyChange("NotifyGameNotRunning", null, null);
	}

	@Override
	public void notifySelectWrongTeam() {
		pcs.firePropertyChange("NotifySelectWrongTeam", null, null);
	}

	@Override
	@Requires({ "buttonClicked !=null", "pieceType != null" })
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterMovingPiece", pieceType.toString(), buttonClicked);
	}

	@Override
	public void updateBoardBeforeMovePiece(AbstractButton buttonClicked, MovePieceController movePieceController) {
		pcs.firePropertyChange("UpdateBoardBeforeMovingPiece", buttonClicked, movePieceController);

	}

	@Override
	@Requires("buttonClicked != null")
	public void updateBoardSelectAnotherPiece(AbstractButton buttonClicked) {
		pcs.firePropertyChange("RollbackSelectedPiece", null, buttonClicked);
	}

	@Override
	public void getPlayerAction(PlayerAction playerAction) {
		pcs.firePropertyChange("GetMode", playerAction, null);

	}

	@Override
	public void updateBoardBeforeSwap(AbilityController visionController) {
		pcs.firePropertyChange("UpdateBoardBeforeSwap", null, visionController);
	}

	@Override
	public void updateBoardAfterSwap(AbstractButton buttonClicked) {
		pcs.firePropertyChange("UpdateBoardAfterSwap", null, buttonClicked);
	}

	@Override
	public void updateBoardChangeAction() {
		pcs.firePropertyChange("UpdateBoardChangeAction", null, null);

	}

	@Override
	public void updateBoardBeforeLeadershipProtect(AbilityController leadershipController) {
		pcs.firePropertyChange("UpdateBoardBeforeLeadershipProtect", null, leadershipController);
	}

	@Override
	public void updateBoardAfterLeadershipProtect() {
		pcs.firePropertyChange("UpdateBoardAfterLeadershipProtect", null, null);
	}

	@Override
	public void updateBoardBeforeAttackingEagleCapture(AbilityController attackingController) {
		pcs.firePropertyChange("UpdateBoardBeforeAttackingEagleCapture", null, attackingController);
	}

	@Override
	public void updateBoardAfterAttackingEagleCapture(AbstractButton btnClicked) {
		pcs.firePropertyChange("UpdateBoardAfterAttackingEagleCapture", null, btnClicked);
	}

	@Override
	public void updateBoardFailToCaptureImmunity() {
		pcs.firePropertyChange("UpdateBoardFailToCaptureImmunity", null, null);
	}

	@Override
	public void undoMoveCancelTimer() {
		pcs.firePropertyChange("UndoCancelTimer", null, null);
	}

	@Override
	public void resumeGame(TeamType currentTeam) {
		pcs.firePropertyChange("ResumeGame", null, currentTeam);
	}

	@Override
	public void confirmUndoSuccessful() {
		pcs.firePropertyChange("UndoSuccessful", null, null);

	}

	@Override
	public void updateBoardBeforeAggressiveSharkCapture(AbilityController aggressiveController) {
		pcs.firePropertyChange("UpdateBoardBeforeAggressiveSharkCapture", null, aggressiveController);
	}

	@Override
	public void updateBoardAfterAggressiveSharkCapture(AbstractButton btnClicked) {
		pcs.firePropertyChange("UpdateBoardAfterAggressiveSharkCapture", null, btnClicked);

	}

	@Override
	public void undoFail(String failMsg) {
		pcs.firePropertyChange("UndoFail", null, failMsg);
	}

	@Override
	public void updateBoardBeforeDefensiveSharkAbility(AbilityController defensiveController) {
		pcs.firePropertyChange("UpdateBoardBeforeDefensiveSharkAbility", null, defensiveController);
	}

	@Override
	public void updateBoardAfterDefensiveSharkMoveAbility(AbstractButton btnClicked, Cell newPos) {
		pcs.firePropertyChange("UpdateBoardAfterDefensiveSharkMoveAbility", newPos, btnClicked);
	}

	@Override
	public void updateBoardAfterDefensiveSharkProtectAbility() {
		pcs.firePropertyChange("updateBoardAfterDefensiveSharkProtectAbility", null, null);
	}

	@Override
	public void updateBoardNoSharkToRevive() {
		pcs.firePropertyChange("UpdateBoardNoSharkToRevive", null, null);
	}

	@Override
	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece) {
		pcs.firePropertyChange("UpdateBoardReviveSharkSuccessful", null, revivedPiece);
	}

	@Override
	public void updateBoardNotCorrectTurnToRevive() {
		pcs.firePropertyChange("UpdateBoardNotCorrectTurnToRevive", null, null);
	}

	@Override
	public void updateBoardAlreadyUseReviveLastRound(String msg) {
		pcs.firePropertyChange("UpdateBoardAlreadyUseReviveLastRound", null, msg);
	}

	@Override
	public void updateBoardBeforeLeadershipUseMode(Set<Cell> cell, ModeController leadershipModeController) {
		pcs.firePropertyChange("UpdateBoardBeforeLeadershipUseMode", leadershipModeController, cell);
	}

	@Override
	public void updateBoardFailToUseLeadershipMode(String errMsg) {
		pcs.firePropertyChange("UpdateBoardFailToUseLeadershipMode", null, errMsg);
	}

	@Override
	public void updateBoardAfterLeadershipUseMode(Set<Cell> cell) {
		pcs.firePropertyChange("UpdateBoardAfterLeadershipUseMode", null, cell);
	}

	@Override
	public void updateBoardBeforeVisionaryUseMode(ModeController visionaryController) {
		pcs.firePropertyChange("updateBoardBeforeVisionaryUseMode", null, visionaryController);
	}

	@Override
	public void updateBoardBeforeAttackingEagleUseMode(ModeController attackingController) {
		pcs.firePropertyChange("UpdateBoardBeforeAttackingEagleUseMode", null, attackingController);
	}

	@Override
	public void updateBoardFailAttackingEagleUseMode(String errMsg) {
		pcs.firePropertyChange("UpdateBoardFailAttackingEagleUseMode", null, errMsg);
	}

}
