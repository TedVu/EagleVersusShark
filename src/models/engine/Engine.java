package models.engine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.pieces.Piece;
import models.player.Player;

public interface Engine {
	
	public Map<UUID, Piece> getAllPieces();
	public Map<UUID, Piece> getActiveEagles();
	public Map<UUID, Piece> getActiveSharks();
	public boolean setPieceActiveStatus(UUID pieceId, boolean isActive);
	public Player getCurrentActivePlayer();
	public Player getInitialPlayerActivePlayer();
	public void setActivePlayer(String playerType);
	
}
