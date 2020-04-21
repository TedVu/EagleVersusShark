package view.operationview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.ItemChangeController;
import controller.PlayerAction;
import controller.UndoMoveController;
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
	private JButton undoBtn;
	private JButton resumeBtn;
	private ViewControllerInterface viewControllerFacade;

	/**
	 * @see
	 */
	public ModePanel(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;

		String[] modes = { "MOVE", "USEABILITY", "SKYMODE", "PROTECTIONMODE" };
		modeBox = new JComboBox<String>(modes);
		modeBox.addItemListener(new ItemChangeController(viewControllerFacade));
		undoBtn = new JButton("UNDO");
		undoBtn.addActionListener(new UndoMoveController());
		resumeBtn = new JButton("RESUME");

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mode Panel"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel compoBoxPannel = new JPanel();
		compoBoxPannel.add(modeBox);
		add(compoBoxPannel);

		JPanel undoBtnPanel = new JPanel();
		undoBtnPanel.add(undoBtn);
		undoBtnPanel.add(resumeBtn);
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
