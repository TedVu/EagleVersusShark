package models.pieces.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import javax.management.RuntimeErrorException;

import controller.AssetHelper;
import models.board.Board;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.pieces.VisionaryEagle;
import models.player.Player;
import models.player.PlayerImpl;

public class PieceOperator {
	
	private Map<String, Piece> pieces = new HashMap<String, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();

	private List<Piece> activeEagles = new ArrayList<Piece>();
	private List<Piece> activeSharks = new ArrayList<Piece>();
	private Player eaglePlayer = new PlayerImpl("eaglePlayer");
	private Player sharkPlayer = new PlayerImpl("sharkPlayer");

	
	private Stack<Command> commandHistory = new Stack<Command>(); 

	private Board board;
	
	public PieceOperator(Board board) {
		this.board = board;
		initializePiece();
		
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void initializePiece() {
		AssetHelper.populate();
		Piece eaglePiece1 = pieceFactory.generatePiece("AttackingEagle", AssetHelper.initialPosAttackingEagle.get("x"),
				AssetHelper.initialPosAttackingEagle.get("y"));
		Piece eaglePiece2 = pieceFactory.generatePiece("LeadershipEagle", AssetHelper.initialPosLeadershipEagle.get("x"),
				AssetHelper.initialPosLeadershipEagle.get("y"));
		Piece eaglePiece3 = pieceFactory.generatePiece("VisionaryEagle", AssetHelper.initialPosVisionaryEagle.get("x"),
				AssetHelper.initialPosVisionaryEagle.get("y"));

		Piece sharkPiece1 = pieceFactory.generatePiece("AggressiveShark", AssetHelper.initialPosAggressiveShark.get("x"),
				AssetHelper.initialPosAggressiveShark.get("y"));
		Piece sharkPiece2 = pieceFactory.generatePiece("DefensiveShark", AssetHelper.initialPosDefensiveShark.get("x"),
				AssetHelper.initialPosDefensiveShark.get("y"));
		Piece sharkPiece3 = pieceFactory.generatePiece("HealingShark", AssetHelper.initialPosHealingShark.get("x"),
				AssetHelper.initialPosHealingShark.get("y"));

		board.addPiece(0, 3);
		board.addPiece(1, 4);
		board.addPiece(0, 5);

		board.addPiece(8, 3);
		board.addPiece(7, 4);
		board.addPiece(8, 5);

		pieces.put("AttackingEagle", eaglePiece1);
		pieces.put("LeadershipEagle", eaglePiece2);
		pieces.put("VisionaryEagle", eaglePiece3);

		pieces.put("AggressiveShark", sharkPiece1);
		pieces.put("DefensiveShark", sharkPiece2);
		pieces.put("HealingShark", sharkPiece3);

	}
	
	/*
	 * @return List<Piece> all active eagles
	 */
	public List<Piece> getActiveEagles() {
		for (Piece piece : pieces.values()) {
			if (piece != null && piece.isActive() && (piece instanceof AttackerEagle || piece instanceof LeadershipEagle
					|| piece instanceof VisionaryEagle)) {
				activeEagles.add(piece);
			}
		}
		return activeEagles;
	}

	/*
	 * @return List<Piece> - all active sharks
	 */
	public List<Piece> getActiveSharks() {

		return activeSharks;
	}
	
	/*
	 * contructor for initial eagle creation
	 * 
	 * @param Piece piece - the piece that the active status will change
	 * 
	 * @param bool isActive - want to set to active ? true : false
	 */
	public boolean setPieceActiveStatus(Piece piece, boolean isActive) {
		try {
			piece.setActive(isActive);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Player getCurrentActivePlayer() {

		if (eaglePlayer.getActive())
			return eaglePlayer;
		else
			return sharkPlayer;

	}
	
	public Map<String, Piece> getAllPieces() {
		return pieces;
	}

	public boolean checkSelectPiece(String occupiedPiece) {
		if (!pieces.containsKey(occupiedPiece)) {
			return false;
		}
		return true;
	}
	
	protected void useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		piece.useAbility(abilityName, piece, affectedPiece);
	}
	
	protected void movePiece(Piece piece, int newX, int newY) {
		
		board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		board.addPiece(newX, newY);

		piece.movePiece(newX, newY);
	}

	protected void undo() {
		
		if(commandHistory.empty())
			throw new RuntimeException("Nothing to undo");
		else {
			commandHistory.peek().undo();
			commandHistory.pop();
		}
	}

	protected void addEvt(Command command) {
		commandHistory.push(command);
	}
	
	


}
