package view.operationview;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.playinggamecontroller.ItemChangeController;
import controller.playinggamecontroller.MakingMovePropertyChangeListener;
import controller.playinggamecontroller.PlayerAction;
import controller.playinggamecontroller.ResumeGameController;
import controller.playinggamecontroller.UndoMoveController;
import model.engine.EngineImpl;
import model.enumtype.TeamType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author ted &#38; kevin
 */
public class ModePanel extends JPanel implements PropertyChangeListener {

	/**
	 * @serial 4010321472922982018L
	 */

	private static final long serialVersionUID = 4010321472922982018L;

	private JComboBox<String> modeBox;
	String[] allModes = { "MOVE", "USEABILITY", "EAGLEMODE", "SHARKMODE" };
	private String[] eagleActions = { "MOVE", "USEABILITY", "EAGLEMODE" };
	private String[] sharkActions = { "MOVE", "USEABILITY", "SHARKMODE" };

	private JButton undoBtn;
	private JButton resumeBtn;

	/**
	 * @see
	 */
	public ModePanel(ViewControllerInterface viewControllerFacade) {

		modeBox = new JComboBox<String>(allModes);
		modeBox.setPreferredSize(new Dimension(130, 30));

		modeBox.addItemListener(new ItemChangeController(viewControllerFacade));
		resumeBtn = new JButton("RESUME");
		undoBtn = new JButton("UNDO");

		resumeBtn.addActionListener(new ResumeGameController(viewControllerFacade));
		resumeBtn.setEnabled(false);
		undoBtn.addActionListener(new UndoMoveController(viewControllerFacade, resumeBtn));

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mode Panel"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel compoBoxPannel = new JPanel();
		compoBoxPannel.add(modeBox);
		add(compoBoxPannel);

		JPanel undoBtnPanel = new JPanel();
		undoBtnPanel.add(undoBtn);
		undoBtnPanel.add(resumeBtn);
		add(undoBtnPanel);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().getGameEngineCallback()
				.getPropertyChangeListener();
		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof MakingMovePropertyChangeListener) {
				((MakingMovePropertyChangeListener) listener).injectModePanel(this);
			}
		}

	}

	public void updateAvailableMode(TeamType team) {
		if (team == TeamType.EAGLE) {
			modeBox.removeAllItems();
			for (int i = 0; i < eagleActions.length; ++i) {
				modeBox.insertItemAt(eagleActions[i], i);
			}
			modeBox.setSelectedIndex(0);

		} else if (team == TeamType.SHARK) {
			modeBox.removeAllItems();
			for (int i = 0; i < sharkActions.length; ++i) {
				modeBox.insertItemAt(sharkActions[i], i);
			}
			modeBox.setSelectedIndex(0);

		} else {
			throw new IllegalArgumentException("Invalid Argument");
		}

	}

	/*
	 * Listen to ViewControllerFacade attachment is done in MainAppFrame
	 * 
	 * @see MainAppFrame
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("GetMode")) {
			PlayerAction playerAction = (PlayerAction) evt.getOldValue();
			playerAction.setPlayerAction(modeBox.getSelectedItem().toString());
		}
	}

	public AbstractButton getResumeButton() {
		return resumeBtn;
	}
}
