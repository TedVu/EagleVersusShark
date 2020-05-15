package model.contract;

/**
 *
 * @author Sefira
 *
 */
public interface Command {
	public void execute();

	public void undo();
}
