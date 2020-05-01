package controller.abstractfactory;

import model.enumtype.TeamType;

public interface ModeControllerFactory {
	public ModeController createModeController(TeamType team);

}
