package view.operationview;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.InvokeSaveGameDialogController;
import view.mainframe.MainAppFrame;

public class OperationToolbar extends JMenuBar {

	private JMenu[] menus;
	private JMenuItem saveMenu = new JMenuItem("Save");
	private JMenuItem exit = new JMenuItem("Exit");
	private MainAppFrame mainFrame;

	public OperationToolbar(MainAppFrame mainFrame) {
		this.mainFrame = mainFrame;

		String[] options = new String[] { "File", "Edit", "Help" };
		menus = new JMenu[options.length];

		for (int i = 0; i < options.length; i++) {
			menus[i] = new JMenu(options[i]);
			add(menus[i]);
		}

		menus[0].add(saveMenu);
		saveMenu.setMnemonic('S');
		saveMenu.addActionListener(new InvokeSaveGameDialogController(mainFrame));
		menus[0].addSeparator();
		menus[0].add(exit);
		exit.setMnemonic('E');
	}

}
