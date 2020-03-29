package controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractButton;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

public class TimerPropertyChangeListener implements PropertyChangeListener {

	private BoardPanel boardView;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("switchturn")) {
			int nRow = EngineImpl.getSingletonInstance().getBoard().getRow();
			List<List<AbstractButton>> buttons = boardView.getButtonList();
			for (int row = 0; row < nRow; ++row) {
				int nCol = EngineImpl.getSingletonInstance().getBoard().getCol();
				for (int col = 0; col < nCol; ++col) {
					buttons.get(row).get(col).setBackground(Color.WHITE);
					ActionListener[] listeners = buttons.get(row).get(col).getActionListeners();
					for (ActionListener l : listeners) {
						buttons.get(row).get(col).removeActionListener(l);
					}
					buttons.get(row).get(col)
							.addActionListener(new SelectPieceController(buttons.get(row).get(col), boardView));
				}
			}
		}

	}

	public void injectBoard(BoardPanel boardView) {
		this.boardView = boardView;
	}

}
