package view.operationview;

import java.awt.GridLayout;

import javax.swing.JPanel;

import viewcontroller.contract.ViewControllerInterface;

/**
 * @author ted &#38; kevin
 */
public class RightPanel extends JPanel {

	/**
	 * @return
	 */
	private static final long serialVersionUID = 2103383416089369712L;
	private StatusPanel statusPanel;
	private ModePanel modePanel;

	/**
	 * @see
	 */
	public RightPanel(ViewControllerInterface viewControllerFacade) {
		statusPanel = new StatusPanel();
		modePanel = new ModePanel(viewControllerFacade);

		setLayout(new GridLayout(2, 1));

		add(statusPanel);
		add(modePanel);
	}

	/**
	 * @return
	 */
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	public ModePanel getModePanel() {
		return modePanel;
	}
}
