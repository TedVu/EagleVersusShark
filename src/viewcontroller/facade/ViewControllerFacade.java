package viewcontroller.facade;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumSet;
import java.util.Map;

import javax.swing.AbstractButton;

import asset.PieceType;
import controller.MovePieceController;
import viewcontroller.interfaces.ViewControllerInterface;

public class ViewControllerFacade implements ViewControllerInterface {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void enableAvailableMove(AbstractButton buttonClicked) {
		pcs.firePropertyChange("EnableAvailableMove", null, buttonClicked);
	}

	@Override
	public void addListenerOnValidMovesCell(AbstractButton buttonClicked, MovePieceController movePieceController) {
		pcs.firePropertyChange("AddListenerValidMoveCell", movePieceController, buttonClicked);
	}

	@Override
	public void updateBoardMovingPiece(AbstractButton buttonClicked, String pieceType) {

		pcs.firePropertyChange("UpdateBoardMovingPiece", pieceType, buttonClicked);
	}

	@Override
	public void updateBoardRollback() {
		pcs.firePropertyChange("UpdateBoardRollback", null, null);
	}

	@Override
	public void updateBoardAfterChoosingPiece(String pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterChoosingPiece", null, pieceType);
	}

	@Override
	public void readdMovePieceController(String pieceType, MovePieceController movePieceController) {
		pcs.firePropertyChange("ReaddMovePieceController", movePieceController, pieceType);
	}

	@Override
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos) {
		pcs.firePropertyChange("LocateNewPosition", buttonClicked, newPos);
	}

	@Override
	public void restoreButtonStateForNextTurn(EnumSet<PieceType> animalSet) {
		pcs.firePropertyChange("RestoreButtonStateForNextTurn", null, animalSet);
	}

	@Override
	public void rollbackSelectedPiece(AbstractButton buttonClicked) {
		pcs.firePropertyChange("RollbackSelectedPiece", null, buttonClicked);
	}

	@Override
	public void notifyNotStartGame() {
		pcs.firePropertyChange("NotifyNotStartGame", null, null);
	}

	@Override
	public void notifySelectWrongTeam() {
		pcs.firePropertyChange("NotifySelectWrongTeam", null, null);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

}
