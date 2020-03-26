package models.pieces;

import models.engine.Engine;
import models.engine.EngineImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractShark implements Piece {

    private UUID id;
    private Map<String, Integer> position = new HashMap<>();
    private boolean isActive;

    /*
     *  TO BE implemented by concrete classes
     *  BECAUSE each concrete class exhibits their own type and movement behaviour
     */
    public abstract Map<String, Integer> move(int x, int y);
    public abstract String getType();

    public AbstractShark(int x, int y){
        this.id = UUID.randomUUID();
        position.put("x", x);
        position.put("y", y);
        isActive = true;
        //add piece's location onto the board
        EngineImpl.getSingletonInstance().getBoard().addPiece(x, y);
    }

    /*
     *  replace(String key, Integer value)
     *  add the given VALUE to the specified KEY
     */
    @Override
    public void setPosition(int x, int y) {
        this.position.replace("x",x);
        this.position.replace("y",y);
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    // Get the Board and doing some Validation here
    // @TED to check on this code!!
    @Override
    public boolean movePieceTed(int newX, int newY) {
        return true;
    }

    @Override
    public Map<String, Integer> getPosition() {
        return this.position;
    }
}
