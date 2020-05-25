package view.operationview;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.playinggamecontroller.SaveGameController;
import view.mainframe.AppMainFrame;
import viewcontroller.contract.ViewControllerInterface;

/**
 * An operational toolbar on top of the board frame
 * 
 * @author ted &#38; kevin
 */
public class OperationToolbar extends JMenuBar {

	private static final long serialVersionUID = -3854607902557332468L;
	
	private JMenu[] menus;
	private JMenuItem save;
	private JMenuItem exit;

	public OperationToolbar(AppMainFrame mainFrame, ViewControllerInterface viewControllerFacade) {
		String[] options = new String[] { "File" };
		menus = new JMenu[options.length];

		for (int i = 0; i < options.length; i++) {
			menus[i] = new JMenu(options[i]);
			add(menus[i]);
		}

		save = new JMenuItem("Save");

		save.addActionListener(new SaveGameController(viewControllerFacade,
				mainFrame.getRightPanel().getModePanel().getResumeButton()));
		exit = new JMenuItem("Exit");
		menus[0].add(save);
		menus[0].addSeparator();
		menus[0].add(exit);

		save.setMnemonic('S');
		exit.setMnemonic('E');
	}
}
