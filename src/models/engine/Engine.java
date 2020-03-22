package models.engine;

import java.util.Map;
import java.util.UUID;

import model.board.Board;
import models.pieces.Piece;

public interface Engine {

	public Map<UUID, Piece> getAllPieces();
  public void addNewPiece(Map<UUID, Piece> newPiece, Piece type);
  
	public Board getBoard();

	public boolean movePiece(Piece piece, int newX, int newY);

	// Ted
	public Map<String, Piece> getAllPiecesTed();

	public boolean checkSelectPiece(String occupiedPiece);

	public void seedData();

}