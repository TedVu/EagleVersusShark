package view.operationview;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModePanel extends JPanel {

	private static final int WIDTH_OF_PANEL = 230;
	private static final int HEIGHT_OF_PANEL = 0;
	private JLabel modeLabel;
	private JComboBox<String> modesMenu;
	private String[] modes = { "Please select mode", "Normal Mode", "Sky Mode", "Protection Mode" };

	public ModePanel() {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mode Panel"));
		setPreferredSize(new Dimension(WIDTH_OF_PANEL, HEIGHT_OF_PANEL));

		modeLabel = new JLabel("Mode:");
		modesMenu = new JComboBox<String>(modes);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;

		// first row
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		add(modeLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		add(modesMenu, gridBagConstraints);
	}
}
