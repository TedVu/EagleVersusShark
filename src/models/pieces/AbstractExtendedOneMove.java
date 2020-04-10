package models.pieces;

import models.engine.EngineImpl;

import java.util.*;

public abstract class AbstractExtendedOneMove extends AbstractSimpleOneMove{
    public AbstractExtendedOneMove(int x, int y) {
        super(x, y);
    }

    @Override
    public Set<List<Integer>> getValidMove() {
        Map<String, Integer> currentPosition = this.getPosition();
        int currentX = currentPosition.get("x");
        int currentY = currentPosition.get("y");
        Set<List<Integer>> validMoves = super.getValidMove();
        validMoves.addAll(validDiaNorthEast(currentX, currentY, 1));
        validMoves.addAll(validDiaSouthWest(currentX, currentY, 1));
        validMoves.addAll(validDiaSouthEast(currentX, currentY, 1));
        validMoves.addAll(validDiaNorthWest(currentX, currentY, 1));
        return validMoves;
    }

    public Set<List<Integer>> validDiaNorthEast(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol() && y - i >= 0) {
                validMove.add(x + i);
                validMove.add(y - i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validDiaSouthEast(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol()
                    && y + i < EngineImpl.getSingletonInstance().getBoard().getRow()) {
                validMove.add(x + i);
                validMove.add(y + i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validDiaSouthWest(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (y + i < EngineImpl.getSingletonInstance().getBoard().getRow() && x - i >= 0) {
                validMove.add(x - i);
                validMove.add(y + i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validDiaNorthWest(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (x - i >= 0 && y - i >= 0) {
                validMove.add(x - i);
                validMove.add(y - i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }
}
