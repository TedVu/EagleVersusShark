package view.callback;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.swing.SwingUtilities;

import model.enumtype.TeamType;
import view.contract.GameEngineCallbackInterface;

/**
 * @author ted &#38; kevin
 */
public class GameEngineCallbackImpl implements GameEngineCallbackInterface, Serializable {

	private static final long serialVersionUID = 9007240281001385424L;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void addProperytChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void endGame(TeamType teamWin) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange("EndGame", null, teamWin);
			}
		});
	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListener() {
		return pcs.getPropertyChangeListeners();
	}

	@Override
	public void nextMove(TeamType currentPlayerTurn) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange("MakingMove", null, currentPlayerTurn);
			}
		});
	}

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
