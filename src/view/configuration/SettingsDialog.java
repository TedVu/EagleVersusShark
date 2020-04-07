package view.configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ApplyConfigurationController;

public class SettingsDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4920890146910758833L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 150;

	private JComboBox<String> boardSizeMenu;
	private JComboBox<String> numPieceMenu;

	private JButton applyButton;

	public SettingsDialog(JFrame startFrame) {
		String[] boardSize = { "9 x 9", "11 x 11", "13 x 13", "15 x 15" };
		String[] numPieces = { "   6   ", "   4   ", "   2   " };

		boardSizeMenu = new JComboBox<String>(boardSize);
		numPieceMenu = new JComboBox<String>(numPieces);
		applyButton = new JButton("APPLY");

		setTitle("Settings");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		
		applyButton.addActionListener(new ApplyConfigurationController(startFrame, this));

		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		JPanel boardSizePanel = new JPanel();
		boardSizePanel.add(new JLabel("Board Size:"));
		boardSizePanel.add(boardSizeMenu);
		selectionPanel.add(boardSizePanel);
		JPanel numPiecesPanel = new JPanel();
		numPiecesPanel.add(new JLabel("Board Size:"));
		numPiecesPanel.add(numPieceMenu);
		selectionPanel.add(numPiecesPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(applyButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(selectionPanel,BorderLayout.CENTER);
		
		setVisible(true);
	}

	public int getBoardSizeSelection() {
		int select = boardSizeMenu.getSelectedIndex();
		if (select == 0) {
			return 9;
		} else if (select == 1) {
			return 11;
		} else if (select == 2) {
			return 13;
		} else {
			return 15;
		}
	}

	public int getPieceNumberSelection() {
		int select = numPieceMenu.getSelectedIndex();
		if (select == 0) {
			return 6;
		} else if (select == 1) {
			return 4;
		} else {
			return 2;
		}
	}
}
