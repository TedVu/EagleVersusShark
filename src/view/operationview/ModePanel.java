package view.operationview;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ModePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4010321472922982018L;
	private JComboBox<String> modeBox;
	private JButton undoBtn;

	public ModePanel() {
		String[] modes = { "Please select mode", "Normal Mode", "Sky Mode", "Protection Mode" };
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
}
