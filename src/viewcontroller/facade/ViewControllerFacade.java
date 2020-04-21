package viewcontroller.facade;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.AbstractButton;

import com.google.java.contract.Requires;

import controller.MovePieceController;
import controller.PlayerAction;
import controller.abstractfactory.AbilityController;
import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author kevin 7 ted
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
	public void notifyNotStartGame() {
		pcs.firePropertyChange("NotifyNotStartGame", null, null);
	}

	@Override
	public void notifySelectWrongTeam() {
		pcs.firePropertyChange("NotifySelectWrongTeam", null, null);
	}

	@Override
	@Requires("buttonClicked != null")
	public void updateBoardSelectAnotherPiece(AbstractButton buttonClicked) {
		pcs.firePropertyChange("RollbackSelectedPiece", null, buttonClicked);
	}

	@Override
	@Requires({ "buttonClicked !=null", "pieceType != null" })
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterMovingPiece", pieceType.toString(), buttonClicked);
	}

	@Override
	public void updateBoardBeforeMovePiece(AbstractButton buttonClicked, MovePieceController movePieceController) {
		// TODO Auto-generated method stub
		pcs.firePropertyChange("UpdateBoardBeforeMovingPiece", buttonClicked, movePieceController);

	}

	@Override
	public void getPlayerAction(PlayerAction playerAction) {
		// TODO Auto-generated method stub
		pcs.firePropertyChange("GetMode", playerAction, null);

	}

	@Override
	public void updateBoardBeforeSwap(AbilityController swapController) {
		// TODO Auto-generated method stub
		pcs.firePropertyChange("UpdateBoardBeforeSwap", null, swapController);
	}

	@Override
	public void updateBoardAfterSwap(AbstractButton buttonClicked) {
		pcs.firePropertyChange("UpdateBoardAfterSwap", null, buttonClicked);
	}

	@Override
	public void updateBoardChangeAction() {
		// TODO Auto-generated method stub
		pcs.firePropertyChange("UpdateBoardChangeAction", null, null);

	}

}
