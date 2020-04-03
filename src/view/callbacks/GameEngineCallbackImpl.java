package view.callbacks;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import models.enumeration.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void timerNextMove(Player playerType, Player currentPlayerTurn) {
		pcs.firePropertyChange("SwitchTurn", playerType, currentPlayerTurn);
	}

	@Override
	public void nextMove(Player currentPlayerTurn) {
		pcs.firePropertyChange("MakingMove", null, currentPlayerTurn);

	}

	@Override
	public void addProperytChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);

	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListener() {
		return pcs.getPropertyChangeListeners();
	}

}
