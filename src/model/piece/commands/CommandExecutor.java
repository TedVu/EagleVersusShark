package model.piece.commands;

import com.google.java.contract.Requires;

import model.contract.Command;

/**
 *
 * @author Sefira
 *
 */
public class CommandExecutor {
	
	@Requires({ "command!=null" })
	public void executeCommand(Command command) {
		command.execute();
	}

}
