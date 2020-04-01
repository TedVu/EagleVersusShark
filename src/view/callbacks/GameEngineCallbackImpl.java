package view.callbacks;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import view.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {

	PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void timerNextMove(String playerType, String currentPlayerTurn) {
		pcs.firePropertyChange("SwitchTurn", playerType, currentPlayerTurn);

	}

	@Override
	public void nextMove(String currentPlayerTurn) {
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
