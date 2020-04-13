package models.engine;

import java.util.Map;

import asset.PieceType;
import asset.TeamType;
import models.board.Board;
import models.pieces.Piece;
import models.player.Player;
import view.interfaces.GameEngineCallback;

/**
 * @author sefira
 *
 */
public interface Engine {

	/**
	 * 
	 */
	public void cancelTimer();

	/**
	 * @param occupiedPieceType
	 * @return
	 */
	public boolean checkSelectPiece(PieceType occupiedPieceType);

	/**
	 * @return
	 */
	public Map<PieceType, Piece> getAllPieces();

	/**
	 * @return
	 */
	public Board getBoard();

	/**
	 * @return
	 */
	public Player getCurrentActivePlayer();

	/**
	 * @return
	 */
	public GameEngineCallback getGameEngineCallback();

	/**
	 * @return
	 */
	public Player getInitialPlayerActivePlayer();

	/*
	 * schedule timer to call setActivePlayer(String playerType, boolean
	 * turnOnTimer)
	 * 
	 * @return
	 */
	public boolean getStartGame();

	/**
	 * @param piece
	 * @param newX
	 * @param newY
	 */
	public void movePiece(Piece piece, int newX, int newY);

	/*
	 * set active player
	 * 
	 * @param playerType String playerType
	 * 
	 * @param turnOnTimer turnOnTimer - set interval to change player every n
	 * seconds or not
	 */
	public void setActivePlayer(TeamType playerType, boolean turnOnTimer);

	/**
	 * @param playerType
	 */
	public void setActivePlayerTimer(TeamType playerType);

	/*
	 * contructor for initial eagle creation
	 * 
	 * @param piece Piece - the piece that the active status will change
	 * 
	 * @param isActive boolean - want to set to active ? true : false
	 * 
	 * @return
	 */
	public boolean setPieceActiveStatus(Piece piece, boolean isActive);

	/**
	 * 
	 */
	public void setStartGame();
}
