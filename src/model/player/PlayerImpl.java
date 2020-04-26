package model.player;

import java.util.HashMap;
import java.util.Map;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.enumtype.TeamType;

/**
 * @author sefira
 * 
 *         class to control the activation of each team in the future this class
 *         will also control whether the player can undo
 *
 */
public class PlayerImpl implements Player {

	private boolean isActive;
	private TeamType playerType;
	private Map<Integer, Integer> undoDict = new HashMap<Integer, Integer>();
	private int undoCount = 0;
	private int prevUndoRound;

	public PlayerImpl(TeamType playerType) {
		this.playerType = playerType;
	}

	@Override
	@Requires({ "this.isActive ==true || this.isActive ==false" })
	public boolean getActive() {
		return isActive;
	}

	@Override
	@Requires({ "playerType != null" })
	public TeamType getPlayerType() {
		return playerType;
	}

	@Override
	@Requires({ "isActive ==true || isActive ==false" })
	@Ensures({ "this.isActive == isActive" })
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return String.format("%s", playerType.toString());
	}
	
	@Override
	public void undoCounter(int round) {
		System.out.println("-----in undocounter");
		System.out.println(undoDict);
		if(!undoDict.containsKey(round)) {
			undoCount++;
			prevUndoRound = round;
			undoDict.put(round, 1);
			
			System.out.println("------ overall undo Count undo counter = " + undoCount);
		}
		else {
			int newCount = undoDict.get(round) + 1;
			undoDict.replace(round, newCount) ;
		}
	}

	@Override
	public boolean ableToUndo(int round) {
		
		boolean keyExist = undoDict.containsKey(round);
		
		System.out.println("overall undo Countdww = " + undoCount);
		System.out.println("undo round " + round);
		System.out.println("prev undo round " + prevUndoRound);
		
		if(keyExist) {
			System.out.println("prev undo round");
			System.out.println("undo times this round = " + undoDict.get(round));
			
			
		}
		
		boolean roundCheck = (round != prevUndoRound && undoCount < 1) || (round == prevUndoRound);
		if(roundCheck &&  (keyExist && undoDict.get(round) < 3 || !keyExist) ) {
			
			return true;
			
		}
		
		return false;
	}
}
