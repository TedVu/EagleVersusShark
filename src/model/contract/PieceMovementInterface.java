package model.contract;

import java.util.Set;

import model.board.Cell;

public interface PieceMovementInterface {
	public Set<Cell> validDiaNorthEast(int x, int y, int step);

	public Set<Cell> validDiaNorthWest(int x, int y, int step);

	public Set<Cell> validDiaSouthEast(int x, int y, int step);

	public Set<Cell> validDiaSouthWest(int x, int y, int step);

	public Set<Cell> validMovesEast(int x, int y, int step);

	public Set<Cell> validMovesNorth(int x, int y, int step);

	public Set<Cell> validMovesSouth(int x, int y, int step);

	public Set<Cell> validMovesWest(int x, int y, int step);
}
