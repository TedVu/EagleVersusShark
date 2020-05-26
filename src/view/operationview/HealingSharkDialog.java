package view.operationview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.abstractfactory.sharkability.HealingSharkAbilityController;
import model.contract.Piece;
import model.engine.EngineImpl;

public class HealingSharkDialog extends JDialog {

	private static final long serialVersionUID = -4258981534634398081L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 100;

	private JComboBox<Object> inactiveSharksBox;

	private JButton reviveBtn;

	public HealingSharkDialog(HealingSharkAbilityController healingController) {

		healingController.injectHealingDialog(this);

		setTitle("Revive Shark");

		reviveBtn = new JButton("Revive");
		reviveBtn.addActionListener(healingController);

		List<Piece> activeSharks = EngineImpl.getSingletonInstance().pieceOperator().getActiveSharks();
		Set<String> inactiveSharks = new HashSet<>();
		inactiveSharks.add("DefensiveShark");
		inactiveSharks.add("AggressiveShark");

		for (Piece piece : activeSharks) {
			if (inactiveSharks.contains(piece.toString())) {
				inactiveSharks.remove(piece.toString());
			}
		}
		inactiveSharksBox = new JComboBox<>(inactiveSharks.toArray());

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.add(new JLabel("Inactive Sharks:"));
		textFieldPanel.add(inactiveSharksBox);
		add(textFieldPanel, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();
		btnPanel.add(reviveBtn);
		add(btnPanel, BorderLayout.SOUTH);

		setModal(true);
		setVisible(true);
	}

	public String getSharkRevived() {
		return (String) inactiveSharksBox.getSelectedItem();
	}
}
