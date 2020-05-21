package viewcontroller.facade;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import controller.playinggamecontroller.PlayerAction;
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
	@Requires({ "buttonClicked !=null", "pieceType != null" })
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterMovingPiece", pieceType.toString(), buttonClicked);
	}

	@Override
	public void getPlayerAction(PlayerAction playerAction) {
		pcs.firePropertyChange("GetMode", playerAction, null);

	}

	@Override
	public void updateBoardAfterSwap(AbstractButton buttonClicked) {
		pcs.firePropertyChange("UpdateBoardAfterSwap", null, buttonClicked);
	}

	@Override
	public void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceCapture) {
		pcs.firePropertyChange("UpdateBoardAfterCapture", pieceCapture, btnClicked);
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
	public void updateBoardAfterProtect() {
		pcs.firePropertyChange("UpdateBoardAfterProtect", null, null);
	}

	@Override
	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece) {
		pcs.firePropertyChange("UpdateBoardReviveSharkSuccessful", null, revivedPiece);
	}

	@Override
	public void updateBoardAfterHealingSharkUseMode() {
		pcs.firePropertyChange("UpdateBoardAfterHealingSharkUseMode", null, null);

	}

	@Override
	public void updateBoardBeforeCommitAction(ActionListener abilityController, PieceType animalType) {
		pcs.firePropertyChange("UpdateBoardBeforeCommitAction", animalType, abilityController);
	}

	@Override
	public void updateBoardNotiDialog(String errMsg) {
		pcs.firePropertyChange("UpdateBoardNotification", null, errMsg);
	}

	@Override
	public void refreshBoard() {
		pcs.firePropertyChange("RefreshBoard", null, null);
	}

	@Override
	public void updateBoardPauseGame() {
		pcs.firePropertyChange("UpdateBoardPauseGame", null, null);
	}

}
