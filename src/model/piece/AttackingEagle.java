package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;
import model.piece.movement.DiagonalDecorator;

/**
 * @author sefira
 *
 */
public class AttackingEagle extends AbstractPiece {

	private static final long serialVersionUID = -1967226729710111595L;

	private Engine engine;

	public AttackingEagle(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	public Set<Cell> abilityCells() {
		Set<Cell> enemyPositions = new HashSet<>();
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();
		int pieceX = getPosition().get("x");
		int pieceY = getPosition().get("y");
		int distance = captureDistance(pieceX, pieceY);

		for (Piece activeShark : activeSharks) {
			int sharkX = activeShark.getPosition().get("x");
			int sharkY = activeShark.getPosition().get("y");

			if (isSurrounding(pieceX, sharkX, pieceY, sharkY, distance)) {
				enemyPositions.add(new Cell(sharkX, sharkY));
			}

		}
		if (enemyPositions.size() == 0) {
			throw new RuntimeException("No enemy nearby to capture");
		}
		return enemyPositions;
	}

	@Requires({ "piece!=null", "affectedPiece!=null" })
	private void capture(Piece piece, Piece affectedPiece) {
		if (affectedPiece.isImmune())
			throw new IllegalArgumentException("The piece is immune");

		Cell currentPos = engine.gameBoard().getCell(piece.getPosition().get("x"), piece.getPosition().get("y"));
		Cell opponentPos = engine.gameBoard().getCell(affectedPiece.getPosition().get("x"),
				affectedPiece.getPosition().get("y"));

		engine.gameBoard().removePiece(currentPos.getX(), currentPos.getY());
		engine.gameBoard().addPiece(opponentPos.getX(), opponentPos.getY());
		movePiece(opponentPos.getX(), opponentPos.getY());

		affectedPiece.setActive(false);

	}

	@Requires({ "pieceX>=0", "pieceY>=0" })
	private int captureDistance(int pieceX, int pieceY) {

		List<Piece> activeEagles = engine.pieceOperator().getActiveEagles();

		int distance = 1;

		for (Piece activePiece : activeEagles) {
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

	@Requires({ "shark!=null" })
	private boolean existNearbyShark(Piece shark) {
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();
		for (Piece otherShark : activeSharks) {
			if (otherShark.getPosition().get("x") == shark.getPosition().get("x")
					&& otherShark.getPosition().get("y") == shark.getPosition().get("y")) {
				continue;
			} else {
				if (Math.abs(otherShark.getPosition().get("x") - shark.getPosition().get("x")) <= 1
						&& Math.abs(otherShark.getPosition().get("y") - shark.getPosition().get("y")) <= 1) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalDecorator(new BasicMove()).getValidMove(this, 1);
	}

	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}

	@Override
	@Requires("getPosition() != null")
	@Ensures("modeCells() != null")
	public Set<Cell> modeCells() {
		boolean ableToUseMode = false;
		Set<Cell> modePos = new HashSet<>();

		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();
		for (Piece shark : activeSharks) {
			int midRiver = engine.gameBoard().getSize() / 2;
			int eagleSidePart = midRiver - 2;

			if (shark.getPosition().get("y") <= eagleSidePart && !existNearbyShark(shark)) {
				ableToUseMode = true;
				modePos.add(engine.gameBoard().getCell(shark.getPosition().get("x"), shark.getPosition().get("y")));
			}
		}
		if (!ableToUseMode) {
			throw new RuntimeException("No alone shark in eagle side of the river to use mode");
		}

		return modePos;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);
		engine.pieceOperator().eagleCheckingHealingSharkAbility();
	}

	@Override
	public String toString() {
		return String.format("%s", "AttackingEagle");
	}

	@Override
	@Requires({ "pieceAbility!= null", "piece!= null", "affectedPiece!= null" })
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.CAPTURE)) {
			capture(piece, affectedPiece);
		}

		else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

}
