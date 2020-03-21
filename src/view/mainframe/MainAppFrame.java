package view.mainframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.operationview.BoardPanel;
import view.operationview.OperationToolbar;
import view.operationview.StatusPanel;

public class MainAppFrame extends JFrame {
	private static final int X_COORDINATE_OF_FRAME = 0;
	private static final int Y_COORDINATE_OF_FRAME = 0;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;

	private OperationToolbar operationToolbar;
	private StatusPanel statusPanel;
	private BoardPanel boardPanel;

	public MainAppFrame() {
		super("Eagle And Shark Game");
		
		boardPanel = new BoardPanel();
		operationToolbar = new OperationToolbar();
		statusPanel = new StatusPanel();
		
		this.setLayout(new BorderLayout());
		this.setBounds(X_COORDINATE_OF_FRAME, Y_COORDINATE_OF_FRAME, FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(operationToolbar, BorderLayout.NORTH);
		this.add(statusPanel, BorderLayout.EAST);
	}
}
