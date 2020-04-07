package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.operationview.BoardPanel;
import view.operationview.OperationToolbar;
import view.operationview.RightPanel;
import view.operationview.StatusPanel;

public class MainAppFrame extends JFrame {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;

	private OperationToolbar operationToolbar;
	private BoardPanel boardPanel;
	private RightPanel rightPanel;

	public MainAppFrame() {
		boardPanel = new BoardPanel();
		operationToolbar = new OperationToolbar(this);
		rightPanel = new RightPanel();

		setTitle("Eagle vs Shark");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - FRAME_WIDTH / 2, screenDimension.height / 2 - FRAME_HEIGHT / 2);

		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().add(boardPanel, BorderLayout.CENTER);
		getContentPane().add(operationToolbar, BorderLayout.NORTH);
		getContentPane().add(rightPanel, BorderLayout.EAST);
	}

	public void loadGame() {
		boardPanel.updateLoadPanel();
	}
}
