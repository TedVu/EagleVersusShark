package controller.abstractfactory;

import model.enumtype.TeamType;

public abstract class SpecialBehaviourControllerFactory {
	public static SpecialBehaviourControllerFactory getSpecialBehaviourControllerFactory(TeamType teamType) {

		if (teamType == TeamType.EAGLE) {
			return new EagleSpecialBehaviourControllerFactory();
		} else if (teamType == TeamType.SHARK) {
			return new SharkSpecialBehaviourControllerFactory();
		} else {
			throw new IllegalArgumentException("");
		}
	}

	public abstract AbilityControllerFactory createAbilityControllerFactory();

	public abstract ModeControllerFactory createModeControllerFactory();
}
