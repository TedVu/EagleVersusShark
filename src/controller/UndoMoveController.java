package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.operationview.UndoMovePanel;

public class UndoMoveController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new UndoMovePanel();

	}

}
