package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
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
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new DiagonalDecorator(new BasicMove()).getValidMove(this, 1);
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getPosition().get(\"x\") == x && getPosition().get(\"y\") == y")
	public void movePiece(int x, int y) {
		setPosition(x, y);

		// Chanboth (Remove these comments upon submission)
		// Integrate HealingShark's healing ability tracker
		EngineImpl.getSingletonInstance().pieceOperator().eagleCheckingHealingSharkAbility();
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.CAPTURE)) {
			capture(piece, affectedPiece);
		}

		else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void capture(Piece piece, Piece affectedPiece) {
		try {
			if (affectedPiece.isImmune())
				throw new IllegalArgumentException("The piece is immune");

			Cell currentPos = EngineImpl.getSingletonInstance().gameBoard().getCell(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			Cell opponentPos = EngineImpl.getSingletonInstance().gameBoard()
					.getCell(affectedPiece.getPosition().get("x"), affectedPiece.getPosition().get("y"));

			EngineImpl.getSingletonInstance().gameBoard().removePiece(currentPos.getX(), currentPos.getY());
			EngineImpl.getSingletonInstance().gameBoard().addPiece(opponentPos.getX(), opponentPos.getY());
			movePiece(opponentPos.getX(), opponentPos.getY());

			affectedPiece.setActive(false);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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
		return enemyPositions;
	}

	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}

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

	@Override
	public String toString() {
		return String.format("%s", "AttackingEagle");
	}

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
	public Set<Cell> modeCells() {
		boolean ableToUseMode = false;
		engine = EngineImpl.getSingletonInstance();
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
		try {
			if (!ableToUseMode) {
				throw new IllegalArgumentException("No alone shark in eagle side of the river to use mode");
			}
		} catch (Error e) {
			throw new RuntimeErrorException(e);
		}
		return modePos;
	}

}
