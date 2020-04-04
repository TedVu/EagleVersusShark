package view.configuration;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ApplyConfigurationController;

public class SettingsDialog extends JDialog {
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 200;

	private String[] boardSize = { "9 x 9", "11 x 11", "13 x 13", "15 x 15" };
	private String[] numPieces = { "   6   ", "   4   ", "   2   " };

	private JComboBox<String> boardSizeMenu = new JComboBox<String>(boardSize);
	private JComboBox<String> numPieceMenu = new JComboBox<String>(numPieces);

	private JButton applyButton = new JButton("APPLY");
	private JFrame startFrame;

	public SettingsDialog(JFrame startFrame) {
		this.startFrame = startFrame;
		applyButton.addActionListener(new ApplyConfigurationController(startFrame, this));

		setTitle("Settings");

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(new JLabel("Board Size:"), constraints);

		constraints.gridx = 1;
		this.add(boardSizeMenu, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add(new JLabel("Number of Pieces:"), constraints);

		constraints.gridx = 1;
		this.add(numPieceMenu, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(applyButton, constraints);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setResizable(false);
		this.setVisible(true);
	}
}
