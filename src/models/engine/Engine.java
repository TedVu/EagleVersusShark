package models.engine;

import java.util.List;
import java.util.Map;

import models.board.Board;
import models.pieces.Piece;
import models.enumeration.Player;
import view.interfaces.GameEngineCallback;

public interface Engine {

	public List<Piece> getActiveEagles();

	public List<Piece> getActiveSharks();

	public boolean setPieceActiveStatus(Piece piece, boolean isActive);

	public Player getCurrentActivePlayer();

	public Player getInitialPlayerActivePlayer();

	public void setActivePlayer(Player playerType, boolean turnOnTimer);

	public Board getBoard();

	public void movePiece(Piece piece, int newX, int newY);

	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece);

	public Map<String, Piece> getAllPieces();

	public boolean checkSelectPiece(String occupiedPiece);

	public void initializePiece();

	public void setActivePlayerTimer(Player playerType);

	public boolean getStartGame();

	public void cancelTimer();

	public GameEngineCallback getGameEngineCallback();

}
