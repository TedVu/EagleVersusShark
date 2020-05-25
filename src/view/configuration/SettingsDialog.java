package view.configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.startgamecontroller.ApplyConfigurationController;
import view.mainframe.StartGameMainFrame;

/**
 * SettingsDialog initiated when user hits settings in the welcome menu
 * 
 * @author kevin & ted
 */
public class SettingsDialog extends JDialog {

	private static final long serialVersionUID = -4920890146910758833L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 180;

	private JComboBox<String> boardSizeMenu;
	private JComboBox<String> numPieceMenu;
	private JComboBox<String> obstaclesMenu;

	private JButton applyButton;

	public SettingsDialog(StartGameMainFrame startGameMainFrame) {
		String[] boardSize = { " 9 x 9 ", "11 x 11", "13 x 13", "15 x 15" };
		String[] numPieces = { "   6   ", "   4   ", "   2   " };
		String[] obstacles = { "NO", "YES" };

		boardSizeMenu = new JComboBox<String>(boardSize);
		numPieceMenu = new JComboBox<String>(numPieces);
		obstaclesMenu = new JComboBox<String>(obstacles);

		applyButton = new JButton("APPLY");

		setTitle("Settings");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		JPanel boardSizePanel = new JPanel();
		boardSizePanel.add(new JLabel("Board Size:"));
		boardSizePanel.add(boardSizeMenu);
		selectionPanel.add(boardSizePanel);
		JPanel numPiecesPanel = new JPanel();
		numPiecesPanel.add(new JLabel("Number of Pieces:"));
		numPiecesPanel.add(numPieceMenu);
		selectionPanel.add(numPiecesPanel);

		JPanel obstaclePanel = new JPanel();
		obstaclePanel.add(new JLabel("Obstacles:"));
		obstaclePanel.add(obstaclesMenu);
		selectionPanel.add(obstaclePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(applyButton);

		add(buttonPanel, BorderLayout.SOUTH);
		add(selectionPanel, BorderLayout.CENTER);

		applyButton.addActionListener(new ApplyConfigurationController(this, startGameMainFrame));

		setModal(true);
		setVisible(true);
	}

	/**
	 * Converting human UI to machine value of piece
	 * 
	 * @return
	 */
	public int getBoardSizeSelection() {
		int selectedIndex = boardSizeMenu.getSelectedIndex();
		if (selectedIndex == 0) {
			return 9;
		} else if (selectedIndex == 1) {
			return 11;
		} else if (selectedIndex == 2) {
			return 13;
		} else {
			return 15;
		}
	}

	public int getPieceNumberSelection() {
		int selectedIndex = numPieceMenu.getSelectedIndex();
		if (selectedIndex == 0) {
			return 6;
		} else if (selectedIndex == 1) {
			return 4;
		} else {
			return 2;
		}
	}

	public String getObstacleConfig() {
		return (String) obstaclesMenu.getSelectedItem();
	}
}