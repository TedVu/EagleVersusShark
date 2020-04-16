package model.contract;

import java.util.Map;

import com.google.java.contract.Requires;

import model.board.Board;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.player.Player;
import view.contract.GameEngineCallbackInterface;

/**
 * @author sefira
 *
 */
public interface EngineInterface {

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
	public Map<PieceType, PieceInterface> getAllPieces();

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
	public GameEngineCallbackInterface getGameEngineCallback();

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
	public void movePiece(PieceInterface piece, int newX, int newY);

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
	public boolean setPieceActiveStatus(PieceInterface piece, boolean isActive);

	/**
	 * 
	 */
	public void setStartGame();
}
