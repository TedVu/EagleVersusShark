package model.piece.commands;

import model.contract.CommandInterface;

/**
 *
 * @author Sefira
 *
 */
public class CommandExecutor {

	public void executeCommand(CommandInterface command) {
		command.execute();
	}
}
