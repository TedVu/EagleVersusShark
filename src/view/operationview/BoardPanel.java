package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

//Board Panel is an observer of model
public class BoardPanel extends JPanel {

	private AbstractButton[] buttons;

	public BoardPanel() {
		buttons = new AbstractButton[81];
		ButtonGroup group = new ButtonGroup();

		setLayout(new GridLayout(9, 9));

		for (int i = 0; i < 81; i++) {
			buttons[i] = new JToggleButton();
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setBorder(BorderFactory.createEtchedBorder());
			add(buttons[i]);
			group.add(buttons[i]);
		}
	}
}
