package models.engine;

import java.util.List;
import java.util.Map;

import models.board.Board;
import models.pieces.Piece;
import models.player.Player;
import view.interfaces.GameEngineCallback;

public interface Engine {

	public void cancelTimer();

	public boolean checkSelectPiece(String occupiedPiece);

	// A2-Ted
	public void configBoardSize(int size);

	public void configNumPiece(int numPiece);

	public List<Piece> getActiveEagles();

	public List<Piece> getActiveSharks();

	public Map<String, Piece> getAllPieces();

	public Board getBoard();

	public Player getCurrentActivePlayer();

	public GameEngineCallback getGameEngineCallback();

	public Player getInitialPlayerActivePlayer();

	public boolean getLoadGame();

	public boolean getStartGame();

	public void initializePiece();

	public void loadBoard(int side);

	public void loadPiece(List<Piece> pieces);

	public void loadTurn(String currentTurn);

	public void movePiece(Piece piece, int newX, int newY);

	public void setActivePlayer(String playerType, boolean turnOnTimer);

	public void setActivePlayerTimer(String playerType);

	public boolean setPieceActiveStatus(Piece piece, boolean isActive);

	public void setStartGame();

	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece);

}
