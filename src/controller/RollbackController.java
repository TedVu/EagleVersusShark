package controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractButton;

import models.engine.EngineImpl;
import view.operationview.BoardPanel;

public class RollbackController implements PropertyChangeListener {

	private BoardPanel boardView;
	private AbstractButton button;

	private void findColoredCellsForRollback(List<List<AbstractButton>> buttons) {
		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {
				if (EngineImpl.getSingletonInstance().checkSelectPiece(button.getActionCommand())
						&& (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
								|| buttons.get(row).get(col).getBackground().equals(Color.BLUE))) {
					rollbackSelectedPieceStatus();
				}
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equalsIgnoreCase("rollbackselectedpiece")) {
			boardView = (BoardPanel) evt.getOldValue();
			button = (AbstractButton) evt.getNewValue();
			findColoredCellsForRollback(boardView.getButtonList());
		}

	}

	private void rollbackSelectedPieceStatus() {
		List<List<AbstractButton>> buttons = boardView.getButtonList();

		for (int row = 0; row < buttons.size(); ++row) {
			for (int col = 0; col < buttons.get(0).size(); ++col) {

				if (buttons.get(row).get(col).getBackground().equals(Color.YELLOW)
						|| buttons.get(row).get(col).getBackground().equals(Color.BLUE)) {
					buttons.get(row).get(col).setBackground(Color.WHITE);

					ActionListener[] listenersAttached = buttons.get(row).get(col).getActionListeners();
					for (ActionListener listener : listenersAttached) {
						buttons.get(row).get(col).removeActionListener(listener);
					}
					buttons.get(row).get(col)
							.addActionListener(new SelectPieceController(buttons.get(row).get(col), boardView));
				}
			}
		}
	}

}
