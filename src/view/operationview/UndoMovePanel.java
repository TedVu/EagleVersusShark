package view.operationview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UndoMovePanel extends JDialog {
	private static final long serialVersionUID = -4258981534634398081L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 100;

	private JComboBox<String> undoBox;

	private JButton confirmBtn;

	public UndoMovePanel() {

		setTitle("Undo Panel");

		confirmBtn = new JButton("APPLY");

		String[] numUndo = { "1", "2", "3" };
		undoBox = new JComboBox<>(numUndo);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.add(new JLabel("Undo Moves:"));
		textFieldPanel.add(undoBox);
		add(textFieldPanel, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();
		btnPanel.add(confirmBtn);
		add(btnPanel, BorderLayout.SOUTH);

		setVisible(true);
	}
}
