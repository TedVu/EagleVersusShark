package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.DiagonalMove;

/**
 * @author sefira
 *
 */
public class AttackingEagle extends AbstractPiece {

	private EngineInterface engine;

	public AttackingEagle(int x, int y, EngineInterface engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 1);
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, PieceInterface piece, PieceInterface affectedPiece) {
		if (pieceAbility.equals(PieceAbility.CAPTURE)) {
			capture(piece, affectedPiece);
			// move piece + update board occupation here
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void capture(PieceInterface piece, PieceInterface affectedPiece) {
		try {
			Cell currentPos = EngineImpl.getSingletonInstance().getBoard().getCell(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			Cell opponentPos = EngineImpl.getSingletonInstance().getBoard()
					.getCell(affectedPiece.getPosition().get("x"), affectedPiece.getPosition().get("y"));
			if (affectedPiece.isImmune())
				throw new IllegalArgumentException("The piece is immune");

			EngineImpl.getSingletonInstance().getBoard().removePiece(currentPos.getX(), currentPos.getY());

			movePiece(opponentPos.getX(), opponentPos.getY());
			affectedPiece.setActive(false);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {
		Set<Cell> enemyPositions = new HashSet<>();
		List<PieceInterface> activeSharks = engine.pieceOperator().getActiveSharks();
		int pieceX = getPosition().get("x");
		int pieceY = getPosition().get("y");
		int distance = captureDistance(pieceX, pieceY);

		for (PieceInterface activeShark : activeSharks) {
			int sharkX = activeShark.getPosition().get("x");
			int sharkY = activeShark.getPosition().get("y");

			if (isSurrounding(pieceX, sharkX, pieceY, sharkY, distance)) {
				enemyPositions.add(new Cell(sharkX, sharkY));
			}

		}
		return enemyPositions;
	}

	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}

	private int captureDistance(int pieceX, int pieceY) {

		List<PieceInterface> activeEagles = engine.pieceOperator().getActiveEagles();

		int distance = 1;

		for (PieceInterface activePiece : activeEagles) {
			if (activePiece instanceof LeadershipEagle) {
				int x = activePiece.getPosition().get("x");
				int y = activePiece.getPosition().get("y");

				if (isSurrounding(pieceX, x, pieceY, y, 1)) {
					distance = 2;
				}
			}
		}

		return distance;
	}

}
