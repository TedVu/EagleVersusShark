package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.operationview.BoardPanel;

public class MovePieceController implements PropertyChangeListener, ActionListener {

	BoardPanel boardPanel;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// call timer on Model
		// get valid move of specific piece from model & evt.getNewValue()
		// disable invalid cell move
		// remove select piece listener on these valid move button (instead of having a
		// boolean check)
		// register this controller to valid move cell
		// call relevant method on board/model to update board/model inside
		// actionPerform()
		boardPanel = (BoardPanel) evt.getNewValue();
		ActionListener[] listener = boardPanel.getButtonList().get(0).get(2).getActionListeners();
		boardPanel.getButtonList().get(0).get(2).removeActionListener(listener[0]);
		boardPanel.getButtonList().get(0).get(2).addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Update Model
		// Call method on view to update model
		Image attackingEagle = null;
		try {
			attackingEagle = ImageIO.read(getClass().getResource("/asset/AttackingEagle.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boardPanel.getButtonList().get(0).get(2).setIcon(new ImageIcon(attackingEagle));
		boardPanel.getButtonList().get(0).get(3).setIcon(null);

	}

}
