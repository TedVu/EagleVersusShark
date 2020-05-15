package model.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.google.java.contract.Requires;

import model.board.GameBoard;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceType;

/**
 *
 * @author Sefira
 *
 */
public class GamePiece implements Serializable {

	private static final long serialVersionUID = 1385438527092098873L;

	private final int DEFAULT_HEALING_SHARK = 0;
	private final int EAGLE_TURN = 1;
	private final int SHARK_TURN = 2;

	private Engine engine;

	private List<Piece> activeEagles = new ArrayList<Piece>();
	private List<Piece> activeSharks = new ArrayList<Piece>();

	private GameBoard board;

	private Map<PieceType, Piece> pieces = new EnumMap<PieceType, Piece>(PieceType.class);

	private int healingAbilityCounter = 0;

	public GamePiece(Engine engine) {
		this.board = engine.gameBoard();
		this.engine = engine;
		initializeDefaultPiece();
	}

	/**
	 * @param occupiedPieceType
	 * @return true if the piece is occupied, else false
	 */
	@Requires({ "occupiedPieceType != null" })
	public boolean checkSelectPiece(PieceType occupiedPieceType) {
		if (!pieces.containsKey(occupiedPieceType)) {
			return false;
		}
		return true;
	}

	/**
	 * @return all pieces
	 */
	@Requires({ "pieces.size()>0" })
	public Map<PieceType, Piece> getAllPieces() {
		return pieces;

	}

	/**
	 * Generate the pieces and put them on the board
	 */
	// @Requires({"pieces.size() < 1"})
	// @Ensures({"pieces.size()>0"})
	public void initializeDefaultPiece() {
		int boardSize = board.getSize();
		for (PieceType pt : PieceType.values()) {
			Piece piece = PieceFactory.generatePiece(pt, boardSize, engine);
			board.addPiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
			pieces.put(pt, piece);
		}
	}

	public void initialize2Piece() {
		int boardSize = board.getSize();
		for (PieceType pt : PieceType.values()) {
			if (pt == PieceType.DEFENSIVESHARK || pt == PieceType.LEADERSHIPEAGLE) {
				Piece piece = PieceFactory.generatePiece(pt, boardSize, engine);
				board.addPiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
				pieces.put(pt, piece);
			}
		}
	}

	public void initialize4Piece() {
		int boardSize = board.getSize();
		for (PieceType pt : PieceType.values()) {
			if (pt != PieceType.DEFENSIVESHARK && pt != PieceType.LEADERSHIPEAGLE) {
				Piece piece = PieceFactory.generatePiece(pt, boardSize, engine);
				board.addPiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
				pieces.put(pt, piece);
			}
		}
	}

	/*
	 * Set the selected piece status to active
	 * 
	 * @return boolean
	 */
	@Requires({ "piece != null", " isActive  == true|| isActive == false" })
	public boolean setPieceActiveStatus(Piece piece, boolean isActive) {
		try {
			piece.setActive(isActive);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * @return List<Piece> - all active sharks
	 */
	public List<Piece> getActiveSharks() {
		activeSharks = new ArrayList<>();
		for (Piece piece : pieces.values()) {
			if (piece != null && piece.isActive() && (piece instanceof AggressiveShark || piece instanceof HealingShark
					|| piece instanceof DefensiveShark)) {
				activeSharks.add(piece);
			}
		}
		return activeSharks;
	}

	/*
	 * @return List<Piece> all active eagles
	 */
	public List<Piece> getActiveEagles() {
		activeEagles = new ArrayList<>();
		for (Piece piece : pieces.values()) {
			if (piece != null && piece.isActive() && (piece instanceof AttackingEagle
					|| piece instanceof LeadershipEagle || piece instanceof VisionaryEagle)) {
				activeEagles.add(piece);
			}
		}
		return activeEagles;
	}

	/**
	 * Intention: to keep track of whether the HealingShark can use healing ability
	 * or not 0 = HealingShark can perform healing ability 1 && 2 = HealingShark
	 * unable to perform healing ability
	 * 
	 * @return 0 || 1 || 2
	 * @author Chanboth Som
	 */
	public int getHealingAbilityCounter() {
		return this.healingAbilityCounter;
	}

	/**
	 * Intention: increment the counter when the HealingShark has performed a
	 * healing ability
	 * 
	 * @author Chanboth Som
	 */
	public void incrementHealingAbilityCounter() {
		this.healingAbilityCounter++;
	}

	/**
	 * Intention: reset the counter back to 0 to allow the HealingShark to perform
	 * the ability in the future
	 * 
	 * @author Chanboth Som
	 */
	public void resetHealingAbilityCounter() {
		this.healingAbilityCounter = DEFAULT_HEALING_SHARK;
	}

	/**
	 * Intention: to be used across all 3 eagles. To keep track when to give Healing
	 * Shark the ability to heal on its turn.
	 * 
	 * @author Chanboth Som
	 */
	public void eagleCheckingHealingSharkAbility() {
		if (this.getHealingAbilityCounter() == EAGLE_TURN)
			incrementHealingAbilityCounter();
		else if (this.getHealingAbilityCounter() == SHARK_TURN)
			resetHealingAbilityCounter();
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

}
