package models.pieces;

import models.engine.EngineImpl;

import java.util.*;

public abstract class AbstractSimpleTwoMove extends AbstractPiece{
    public AbstractSimpleTwoMove(int x, int y) {
        super(x, y);
    }

    @Override
    public Set<List<Integer>> getValidMove() {
        Map<String, Integer> currentPosition = this.getPosition();
        int currentX = currentPosition.get("x");
        int currentY = currentPosition.get("y");
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        validMoves.addAll(validMovesSouth(currentX, currentY, 2));
        validMoves.addAll(validMovesNorth(currentX, currentY, 2));
        validMoves.addAll(validMovesEast(currentX, currentY, 2));
        validMoves.addAll(validMovesWest(currentX, currentY, 2));
        return validMoves;
    }

    public Set<List<Integer>> validMovesNorth(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (y - i >= 0) {
                validMove.add(x);
                validMove.add(y - i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validMovesSouth(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (y + i < EngineImpl.getSingletonInstance().getBoard().getRow()) {
                validMove.add(x);
                validMove.add(y + i);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validMovesEast(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol()) {
                validMove.add(x + i);
                validMove.add(y);
                validMoves.add(validMove);
            } else {
                break;
            }
        }
        return validMoves;
    }

    public Set<List<Integer>> validMovesWest(int x, int y, int cells) {
        Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
        for (int i = 1; i <= cells; i++) {
            List<Integer> validMove = new LinkedList<Integer>();
            if (x - i >= 0) {
                validMove.add(x - i);
                validMove.add(y);
                validMoves.add(validMove);
            } else {
                break;
            }
        }

        return validMoves;
    }
}
