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
	private int prevUndoRound = -1;

	private boolean ableToUndo = true;

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
		// System.out.println("-----in undocounter");
		// System.out.println(undoDict);
		if (!undoDict.containsKey(round)) {
			undoCount++;
			prevUndoRound = round;
			undoDict.put(round, 1);

			// System.out.println("------ overall undo Count undo counter = " + undoCount);
		} else {
			int newCount = undoDict.get(round) + 1;
			undoDict.replace(round, newCount);
		}
	}

	@Override
	public boolean ableToUndo(int round) {

		return ableToUndo;
	}

	@Override
	public void setAlreadyUndo() {
		ableToUndo = false;
	}
}