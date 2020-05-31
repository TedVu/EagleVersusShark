package model.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Requires;

import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceType;
import model.piece.AggressiveShark;
import model.piece.AttackingEagle;
import model.piece.DefensiveShark;
import model.piece.HealingShark;
import model.piece.LeadershipEagle;
import model.piece.PieceFactory;
import model.piece.VisionaryEagle;

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

	private Map<PieceType, Piece> pieces = new EnumMap<PieceType, Piece>(PieceType.class);

	private int healingAbilityCounter = 0;

	public GamePiece(Engine engine) {
		this.engine = engine;
		initializePiece(6);
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

	/**
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

	/**
	 * @return all pieces
	 */
	@Requires({ "pieces.size()>0" })
	public Map<PieceType, Piece> getAllPieces() {
		return pieces;

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
	 * The configuration is done here 4 pieces - 2 side pieces of each team 2 pieces
	 * - only middle piece of each team
	 * 
	 * @param numPiece
	 */
	public void initializePiece(int numPiece) {
		Set<PieceType> pieceAvailable = new HashSet<PieceType>();
		if (numPiece == 6) {
			for (PieceType p : PieceType.values()) {
				pieceAvailable.add(p);
			}
		} else if (numPiece == 4) {
			for (PieceType p : PieceType.values()) {
				if (p != PieceType.DEFENSIVESHARK && p != PieceType.LEADERSHIPEAGLE) {
					pieceAvailable.add(p);
				}
			}
		} else {
			for (PieceType p : PieceType.values()) {
				if (p == PieceType.DEFENSIVESHARK || p == PieceType.LEADERSHIPEAGLE) {
					pieceAvailable.add(p);
				}
			}
		}
		int boardSize = engine.gameBoard().getSize();

		// remove all the piece before adding - when applying configuration
		for (PieceType pt : PieceType.values()) {
			Piece piece = PieceFactory.generatePiece(pt, boardSize, engine);
			engine.gameBoard().removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		}

		pieces = new EnumMap<PieceType, Piece>(PieceType.class);

		for (PieceType pt : pieceAvailable) {
			Piece piece = PieceFactory.generatePiece(pt, boardSize, engine);
			engine.gameBoard().addPiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
			pieces.put(pt, piece);
		}

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

}
