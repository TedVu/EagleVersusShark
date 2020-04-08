package models.pieces.commands;

public class CommandExecutor {
	
	public CommandExecutor() {
		
	}
	
	public void executeOperation(Command command) {
		System.out.println("EXECUTING COMMAND....");
        command.execute();
    }

}
