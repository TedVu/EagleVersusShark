package view.operationview;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ModePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4010321472922982018L;
	private JComboBox<String> modesMenu;
	private JButton undoBtn;
	
	public ModePanel() {	
		String[] modes = { "Please select mode", "Normal Mode", "Sky Mode", "Protection Mode" };
		modesMenu = new JComboBox<String>(modes);
		undoBtn = new JButton("Undo Move");
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mode Panel"));
		setLayout(new BorderLayout());
		
		add(modesMenu, BorderLayout.NORTH);
		add(undoBtn, BorderLayout.SOUTH);
	}
}
