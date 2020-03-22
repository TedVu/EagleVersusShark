package models.engine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.board.Board;
import models.pieces.Piece;
import models.player.Player;

public interface Engine {
	
	public List<Piece> getAllPieces();
	
	public List<Piece> getActiveEagles();
	
	public List<Piece> getActiveSharks();
	
	public boolean setPieceActiveStatus(Piece piece, boolean isActive);
	
	public Player getCurrentActivePlayer();
	
	public Player getInitialPlayerActivePlayer();
	
	public void setActivePlayer(String playerType,  boolean turnOnTimer);
	
	public Board getBoard();

	public boolean movePiece(Piece piece, int newX, int newY);

	// Ted
	public Map<String, Piece> getAllPiecesTed();

	public boolean checkSelectPiece(String occupiedPiece);

	public void seedData();
	
	public void setActivePlayerTimer(String playerType);
	
}
