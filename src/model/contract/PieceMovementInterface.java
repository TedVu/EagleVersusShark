package model.contract;

import java.util.List;
import java.util.Set;

public interface PieceMovementInterface {
	public Set<List<Integer>> validDiaNorthEast(int x, int y, int cells);

	public Set<List<Integer>> validDiaNorthWest(int x, int y, int cells);

	public Set<List<Integer>> validDiaSouthEast(int x, int y, int cells);

	public Set<List<Integer>> validDiaSouthWest(int x, int y, int cells);

	public Set<List<Integer>> validMovesEast(int x, int y, int cells);

	public Set<List<Integer>> validMovesNorth(int x, int y, int cells);

	public Set<List<Integer>> validMovesSouth(int x, int y, int cells);

	public Set<List<Integer>> validMovesWest(int x, int y, int cells);
}
