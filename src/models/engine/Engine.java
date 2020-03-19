package models.engine;

import java.util.Map;
import java.util.UUID;

import models.pieces.Piece;

public interface Engine {
	
	public Map<UUID, Piece> getAllPieces();
	public void addNewPiece(Map<UUID, Piece> newPiece, Piece type);

}