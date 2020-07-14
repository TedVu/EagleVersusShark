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
public class ViewControllerFacadeImpl implements ViewControllerInterface {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	@Requires("listener != null")
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void confirmUndoSuccessful() {
		pcs.firePropertyChange("UndoSuccessful", null, null);
	}

	@Override
	@Requires({ "playerAction != null" })
	public void getPlayerAction(PlayerAction playerAction) {
		pcs.firePropertyChange("GetMode", playerAction, null);

	}

	@Override
	@Requires({ "buttonClicked != null", "newPos != null" })
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos) {
		pcs.firePropertyChange("LocateNewPosition", buttonClicked, newPos);
	}

	@Override
	public void refreshBoard() {
		pcs.firePropertyChange("RefreshBoard", null, null);
	}

	@Override
	@Requires({ "currentTeam != null" })
	public void resumeGame(TeamType currentTeam) {
		pcs.firePropertyChange("ResumeGame", null, currentTeam);
	}

	@Override
	@Requires({ "btnClicked != null", "pieceCapture != null" })
	public void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceCapture) {
		pcs.firePropertyChange("UpdateBoardAfterCapture", pieceCapture, btnClicked);
	}

	@Override
	public void updateBoardAfterHealingSharkUseMode() {
		pcs.firePropertyChange("UpdateBoardAfterHealingSharkUseMode", null, null);
	}

	@Override
	@Requires({ "buttonClicked !=null", "pieceType != null" })
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterMovingPiece", pieceType.toString(), buttonClicked);
	}

	@Override
	public void updateBoardAfterProtect() {
		pcs.firePropertyChange("UpdateBoardAfterProtect", null, null);
	}

	@Override
	@Requires({ "buttonClicked != null" })
	public void updateBoardAfterSwap(AbstractButton buttonClicked) {
		pcs.firePropertyChange("UpdateBoardAfterSwap", null, buttonClicked);
	}

	@Override
	@Requires({ "abilityController != null", "animalType != null" })
	public void updateBoardBeforeCommitAction(ActionListener abilityController, PieceType animalType) {
		pcs.firePropertyChange("UpdateBoardBeforeCommitAction", animalType, abilityController);
	}

	@Override
	@Requires({ "errMsg != null" })
	public void updateBoardNotiDialog(String errMsg) {
		pcs.firePropertyChange("UpdateBoardNotification", null, errMsg);
	}

	@Override
	public void updateBoardPauseGame() {
		pcs.firePropertyChange("UpdateBoardPauseGame", null, null);
	}

	@Override
	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece) {
		pcs.firePropertyChange("UpdateBoardReviveSharkSuccessful", null, revivedPiece);
	}

}
