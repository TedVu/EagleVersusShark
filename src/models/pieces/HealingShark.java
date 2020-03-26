package models.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HealingShark extends AbstractShark {
    private String type;
    public HealingShark(int x, int y) {
        super(x, y);
        this.type = "HEALING SHARK";
    }

    @Override
    public Map<String, Integer> move(int x, int y) {
        System.out.println("Moving the healing shark piece");

        setPosition(x, y);
        return getPosition();
    }

    public void healShark(HealingShark healingPiece){
        healingPiece.setActive(true);
        this.setActive(false);
    }

    @Override
    public boolean movePiece(int newX, int newY) {
        return false;
    }

    @Override
    public Set<List<Integer>> getValidMove() {
        return null;
    }

    @Override
    public boolean isImmune() {
        return false;
    }

    @Override
    public void setImmune(boolean isImmune) {

    }

    @Override
    public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
        return false;
    }
}
