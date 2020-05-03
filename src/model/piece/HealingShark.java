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
import model.enumtype.PieceType;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */

public class HealingShark extends AbstractPiece  {
	private final EngineInterface engine;

	public HealingShark(int x, int y, EngineInterface engine) {
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
		if (pieceAbility.equals(PieceAbility.HEAL)) {
			if(EngineImpl.getSingletonInstance().pieceOperator().getHealingAbilityCounter() != 0){
				throw new RuntimeException("You just used the ability last round!");
			} else {
				heal(affectedPiece);
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void heal(PieceInterface affectedPiece) {
		try {
			// Move selected shark piece to its original cell (upon initialization) and set it to active
			int initialX = 0;
			int initialY = 0;
			if(affectedPiece instanceof AggressiveShark){
				initialX = PieceType.AGGRESSIVESHARK.xCoordinate(
						EngineImpl.getSingletonInstance().getBoard().getSize());
				initialY = PieceType.AGGRESSIVESHARK.yCoordinate(
						EngineImpl.getSingletonInstance().getBoard().getSize());

			} else if (affectedPiece instanceof DefensiveShark){
				initialX = PieceType.DEFENSIVESHARK.xCoordinate(
						EngineImpl.getSingletonInstance().getBoard().getSize());
				initialY = PieceType.DEFENSIVESHARK.yCoordinate(
						EngineImpl.getSingletonInstance().getBoard().getSize());
			}

			System.out.printf("X: %d, Y: %d%n",initialX,initialY);
			Cell initialCell = engine.getBoard().getCell(initialX,initialY);
			System.out.println(initialCell.getOccupied());
			boolean cellOccupied = false;
			// Will keep changing initial cell until the cell is found != occupied
			while(!cellOccupied){
				if(initialCell.getOccupied()){
					// AggressiveShark will move 1 unit north-west
					if(affectedPiece instanceof AggressiveShark) {
						--initialX;
						--initialY;
					}
					// DefensiveShark will move 1 unit up
					if(affectedPiece instanceof DefensiveShark)
						--initialY;
					if(!engine.getBoard().getCell(initialX,initialY).getOccupied())
						cellOccupied = true;
				} else {
					cellOccupied = true;
				}
			}
			EngineImpl.getSingletonInstance().pieceOperator().movePiece(affectedPiece,initialX,initialY);
			engine.getBoard().getCell(initialX,initialY).setOccupied();
			EngineImpl.getSingletonInstance().pieceOperator().setPieceActiveStatus(affectedPiece,true);
			EngineImpl.getSingletonInstance().pieceOperator().incrementHealingAbilityCounter();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Set<Cell> abilityCells() {

		// refactor later on as not a good practice to return null
		return null;
	}

	@Override
	public String toString() {
		return String.format("%s", "HealingShark");
	}

}
