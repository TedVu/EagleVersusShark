package model.piece.commands;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Board;
import model.contract.CommandInterface;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.piece.AttackingEagle;
import model.piece.LeadershipEagle;
import model.piece.PieceFactory;
import model.piece.VisionaryEagle;

/**
*
* @author Sefira
*
*/
public class PieceOperator {
	
	public PieceOperator(Board board, EngineInterface engine) {
		this.board = board;
		this.engine = engine;
		initializePiece();
	}
	private EngineInterface engine;
	
	private List<PieceInterface> activeEagles = new ArrayList<PieceInterface>();
	private List<PieceInterface> activeSharks = new ArrayList<PieceInterface>();
	
	private Board board;
	
	private Map<PieceType, PieceInterface> pieces = new EnumMap<PieceType, PieceInterface>(
			PieceType.class);
	
	private Stack<CommandInterface> commandHistory = new Stack<CommandInterface>(); 
	
	/**
	 * @param occupiedPieceType
	 * @return true if the piece is occupied, else false
	 */
	@Requires({"occupiedPieceType != null"})
	public boolean checkSelectPiece(PieceType occupiedPieceType) {
		if (!pieces.containsKey(occupiedPieceType)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return all pieces
	 */
	@Requires({"pieces.size()>0"})
	public Map<PieceType, PieceInterface> getAllPieces() {
		return pieces;
		
	}
	
	/**
	 * Generate the pieces and put them on the board
	 */
//	@Requires({"pieces.size() < 1"})
//	@Ensures({"pieces.size()>0"})
	public void initializePiece() {
		int boardSize = board.getSize();
		for (PieceType pt : PieceType.values()) {
			PieceInterface piece = PieceFactory.generatePiece(pt, boardSize, engine);
			board.addPiece(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			pieces.put(pt, piece);
		}
	}
	
	/**
	 * @param piece
	 *            - the piece to be moved
	 * @param newX
	 *            - new x position
	 * @param newY
	 *            - new y position Generate the pieces and put them on the board
	 */
	@Requires({"piece != null", "x>=0", "y>=0"})
	@Ensures({"piece.getPosition().get(\"x\") != null && piece.getPosition().get(\"y\") != null"})
	public void movePiece(PieceInterface piece, int x, int y) {
		board.removePiece(piece.getPosition().get("x"),
				piece.getPosition().get("y"));
		board.addPiece(x, y);
		piece.movePiece(x, y);
	}
	
	/*
	 * Set the selected piece status to active
	 * 
	 * @return boolean
	 */
	@Requires({"piece != null", " isActive  == true|| isActive == false"})
	public boolean setPieceActiveStatus(PieceInterface piece,
			boolean isActive) {
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
	public List<PieceInterface> getActiveSharks() {

		return activeSharks;
	}
	
	/*
	 * @return List<Piece> all active eagles
	 */
	public List<PieceInterface> getActiveEagles() {
		for (PieceInterface piece : pieces.values()) {
			if (piece != null && piece.isActive() && (piece instanceof AttackingEagle
					|| piece instanceof LeadershipEagle || piece instanceof VisionaryEagle)) {
				activeEagles.add(piece);
			}
		}
		return activeEagles;
	}
	
	protected void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		piece.useAbility(pieceAbility, piece, affectedPiece);
	}
	
	protected void undo() {
		
		if(commandHistory.empty())
			throw new RuntimeException("Nothing to undo");
		else {
			commandHistory.peek().undo();
			commandHistory.pop();
		}
	}
	
	protected void addEvt(CommandInterface command) {
		commandHistory.push(command);
	}
	
}
