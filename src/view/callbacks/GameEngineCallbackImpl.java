package view.callbacks;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.SwingUtilities;

import asset.TeamType;
import view.interfaces.GameEngineCallback;

/**
 * @author ted &#38; kevin
 */
public class GameEngineCallbackImpl implements GameEngineCallback {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * @return
	 */
	@Override
	public void addProperytChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * @return
	 */
	@Override
	public PropertyChangeListener[] getPropertyChangeListener() {
		return pcs.getPropertyChangeListeners();
	}

	/**
	 * @return
	 */
	@Override
	public void nextMove(TeamType currentPlayerTurn) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange("MakingMove", null, currentPlayerTurn);
			}
		});
	}

	/**
	 * @return
	 */
	@Override
	public void timerNextMove(TeamType playerType, TeamType currentPlayerTurn) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange("SwitchTurn", null, currentPlayerTurn);
			}
		});
	}

}
