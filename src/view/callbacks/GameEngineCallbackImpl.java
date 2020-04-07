package view.callbacks;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.SwingUtilities;

import view.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void addProperytChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);

	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListener() {
		return pcs.getPropertyChangeListeners();
	}

	@Override
	public void nextMove(String currentPlayerTurn) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				pcs.firePropertyChange("MakingMove", null, currentPlayerTurn);

			}

		});

	}

	@Override
	public void timerNextMove(String playerType, String currentPlayerTurn) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				pcs.firePropertyChange("SwitchTurn", playerType, currentPlayerTurn);
			}

		});
	}

}
