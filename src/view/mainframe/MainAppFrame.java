package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.operationview.BoardPanel;
import view.operationview.OperationToolbar;
import view.operationview.StatusPanel;

public class MainAppFrame extends JFrame {
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;

	private OperationToolbar operationToolbar;
	private StatusPanel statusPanel;
	private BoardPanel boardPanel;

	public MainAppFrame() {
		boardPanel = new BoardPanel();
		operationToolbar = new OperationToolbar();
		statusPanel = new StatusPanel();

		setTitle("Eagle versus Shark");
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().add(boardPanel, BorderLayout.CENTER);
		getContentPane().add(operationToolbar, BorderLayout.NORTH);
		getContentPane().add(statusPanel, BorderLayout.EAST);
	}
}
