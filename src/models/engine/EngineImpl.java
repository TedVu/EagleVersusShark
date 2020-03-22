package models.engine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import models.pieces.AbstractPiece;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.player.Player;
import models.player.PlayerImpl;
import java.util.Random; 

public class EngineImpl implements Engine{
	
	private static Engine engine = null;
	private Map<UUID, Piece> pieces= new HashMap<UUID, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();
	private Map<UUID, Piece> activeEagles = new HashMap<UUID, Piece>();
	private Player eaglePlayer = new PlayerImpl("eaglePlayer");
	private Player sharkPlayer = new PlayerImpl("sharkPlayer");

	/*
	 * default constructor
	 */
	public EngineImpl() {
	}  
	
	/*
	 * 
	 */
    public static Engine getSingletonInstance() {
        if (engine == null) {
        	engine = new EngineImpl();
        	
          
        }
        return engine;
    }

    /*
     * 
     */
	@Override
	public Map<UUID, Piece> getAllPieces() {
		
		Piece piece = pieceFactory.generatePiece("attackerEagle", 0, 0);
		Piece piece2 = pieceFactory.generatePiece("visionaryEagle", 0, 0);
		
		pieces.put(piece.getId(), piece);
		pieces.put(piece2.getId(), piece2);
		
		
		
		return this.pieces;
	}

	@Override
	public Map<UUID, Piece> getActiveEagles() {
		System.out.println(pieces);
		for(Piece piece : pieces.values()) {
			if(piece != null && piece instanceof AbstractPiece && piece.isActive()) {
				activeEagles.put(piece.getId(), piece);
			}
		}
		return activeEagles;
	}

	@Override
	public Map<UUID, Piece> getActiveSharks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPieceActiveStatus(UUID pieceId, boolean isActive) {
		try {
			pieces.get(pieceId).setActive(isActive);
		} 
		catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Player getCurrentActivePlayer() {
		
		
		if(eaglePlayer.getActive())
			return  eaglePlayer;
		else 
			return  sharkPlayer;

	}
	
	

	@Override
	public Player getInitialPlayerActivePlayer() {
		
		Player activePlayer;
		Random rand = new Random();
    	int randomValue = rand.nextInt() % 2;
    	
    	if(randomValue == 0) {
    		this.eaglePlayer.setActive(true);
    		this.sharkPlayer.setActive(false);
    		activePlayer= eaglePlayer;
    	}
    	else {
    		this.eaglePlayer.setActive(false);
    		this.sharkPlayer.setActive(true);
    		activePlayer =  sharkPlayer;
    	}
    	return activePlayer;
	}

	@Override
	public void setActivePlayer(String playerType) {
		if(playerType == "eagle") {
			this.eaglePlayer.setActive(true);
    		this.sharkPlayer.setActive(false);
		}
		if(playerType == "shark") {
			this.eaglePlayer.setActive(false);
    		this.sharkPlayer.setActive(true);
		}
		else {
			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
		}
		
	}
	
	//make move method

	
}
