package view.operationview;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class OperationToolbar extends JMenuBar {

	private JMenu[] menus;

	public OperationToolbar() {
		String[] options = new String[] { "File", "Edit", "Help" };
		menus = new JMenu[options.length];

		for (int i = 0; i < options.length; i++) {
			menus[i] = new JMenu(options[i]);
			add(menus[i]);
		}
	}

}
