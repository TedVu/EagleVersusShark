package model.piece.commands;

import model.contract.Command;

/**
 *
 * @author Sefira
 *
 */
public class CommandExecutor {

	public void executeCommand(Command command) {
		command.execute();
	}
}
