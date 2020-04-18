package view.operationview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.PlayerAction;

/**
 * @author ted &#38; kevin
 */
public class ModePanel extends JPanel implements PropertyChangeListener {

	/**
	 * @serial 4010321472922982018L
	 */
	private static final long serialVersionUID = 4010321472922982018L;
	private JComboBox<String> modeBox;
	private JButton undoBtn;

	/**
	 * @see
	 */
	public ModePanel() {
		String[] modes = { "MOVE", "USEABILITY", "SKYMODE", "PROTECTIONMODE" };
		modeBox = new JComboBox<String>(modes);
		undoBtn = new JButton("Undo Move");

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mode Panel"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel compoBoxPannel = new JPanel();
		compoBoxPannel.add(modeBox);
		add(compoBoxPannel);

		JPanel undoBtnPanel = new JPanel();
		undoBtnPanel.add(undoBtn);
		add(undoBtnPanel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("GetMode")) {
			PlayerAction playerAction = (PlayerAction) evt.getOldValue();
			playerAction.setPlayerAction(modeBox.getSelectedItem().toString());
		}

	}
}
