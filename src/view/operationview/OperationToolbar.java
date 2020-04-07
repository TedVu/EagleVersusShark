package view.operationview;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.InvokeSaveGameDialogController;
import view.mainframe.MainAppFrame;

public class OperationToolbar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3854607902557332468L;
	private JMenu[] menus;
	private JMenuItem save;
	private JMenuItem exit;

	public OperationToolbar(MainAppFrame mainFrame) {
		String[] options = new String[] { "File", "Edit", "Help" };
		menus = new JMenu[options.length];

		for (int i = 0; i < options.length; i++) {
			menus[i] = new JMenu(options[i]);
			add(menus[i]);
		}

		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		menus[0].add(save);
		menus[0].addSeparator();
		menus[0].add(exit);

		save.setMnemonic('S');
		exit.setMnemonic('E');

		save.addActionListener(new InvokeSaveGameDialogController(mainFrame));
	}

}
