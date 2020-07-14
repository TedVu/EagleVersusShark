package controller.abstractfactory;

import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.contract.ModeControllerFactory;
import model.enumtype.TeamType;

public abstract class SpecialBehaviourControllerFactory {

	public static SpecialBehaviourControllerFactory getSpecialBehaviourControllerFactory(TeamType teamType) {
		if (teamType == TeamType.EAGLE) {
			return new EagleSpecialBehaviourControllerFactory();
		} else if (teamType == TeamType.SHARK) {
			return new SharkSpecialBehaviourControllerFactory();
		} else {
			throw new IllegalArgumentException("Invalid team type");
		}
	}

	public abstract AbilityControllerFactory createAbilityControllerFactory();

	public abstract ModeControllerFactory createModeControllerFactory();

}
