package viewcontroller.interfaces;

import java.beans.PropertyChangeListener;
import java.util.EnumSet;
import java.util.Map;

import javax.swing.AbstractButton;

import asset.PieceType;
import controller.MovePieceController;

public interface ViewControllerInterface {

	void enableAvailableMove(AbstractButton buttonClicked);

	void addListenerOnValidMovesCell(AbstractButton buttonClicked, MovePieceController movePieceController);

	void updateBoardMovingPiece(AbstractButton buttonClicked, String pieceType);

	void updateBoardRollback();

	void updateBoardAfterChoosingPiece(String pieceType);

	void readdMovePieceController(String pieceType, MovePieceController movePieceController);

	void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos);

	void restoreButtonStateForNextTurn(EnumSet<PieceType> animalSet);

	void rollbackSelectedPiece(AbstractButton buttonClicked);

	void notifyNotStartGame();

	void notifySelectWrongTeam();

	void addPropertyChangeListener(PropertyChangeListener listener);

}