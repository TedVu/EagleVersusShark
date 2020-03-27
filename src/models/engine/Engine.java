package models.engine;

import java.util.List;
import java.util.Map;

import models.board.Board;
import models.pieces.Piece;
import models.player.Player;

public interface Engine {

	public List<Piece> getAllPieces();

	public List<Piece> getActiveEagles();

	public List<Piece> getActiveSharks();

	public boolean setPieceActiveStatus(Piece piece, boolean isActive);

	public Player getCurrentActivePlayer();

	public Player getInitiaActivePlayer();

	public void setActivePlayer(String playerType);

	public void addNewPiece(List<Piece> newPiece, Piece type);

	public Board getBoard();

	public boolean movePiece(Piece piece, int newX, int newY);
	
	public void setTimerStatus(boolean timerIsOn);
	
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece);

	// Ted
	public Map<String, Piece> getAllPiecesTed();

	public boolean checkSelectPiece(String occupiedPiece);

	public void seedData();

	public void setActivePlayerTimer(String playerType);

	
	

}
