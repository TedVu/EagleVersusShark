package view.operationview;

import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.sun.glass.events.KeyEvent;

public class OperationToolbar extends JMenuBar {
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;
	private JMenuBar fileMenuBar;
	private JMenuBar editMenuBar;
	private JMenuBar helpMenuBar;

	public OperationToolbar() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenuBar = new JMenuBar();
		fileMenuBar.add(fileMenu);

		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		editMenuBar = new JMenuBar();
		editMenuBar.add(editMenu);

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenuBar = new JMenuBar();
		helpMenuBar.add(helpMenu);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.add(fileMenuBar);
		this.add(editMenuBar);
		this.add(helpMenuBar);

	}

}
