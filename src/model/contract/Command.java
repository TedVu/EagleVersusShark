package model.contract;

/**
 * @author Sefira
 */
public interface Command {

	/**
	 *  call methods from PieceCommands to be executed
	 */
	public void execute();

	/**
	 *  do operation to undo the impact of execute()
	 */
	public void undo();

}
