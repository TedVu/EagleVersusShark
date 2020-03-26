package models.pieces;

import java.util.Map;

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

    @Override
    public String getType() {
        return this.type;
    }

    public void healShark(HealingShark healingPiece){
        healingPiece.setActive(true);
        this.setActive(false);
    }
}
