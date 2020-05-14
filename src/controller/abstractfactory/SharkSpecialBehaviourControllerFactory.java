package controller.abstractfactory;

import controller.abstractfactory.factory.AbilityControllerFactory;
import controller.abstractfactory.factory.ModeControllerFactory;
import controller.abstractfactory.factory.SharkAbilityControllerFactory;
import controller.abstractfactory.factory.SharkModeControllerFactory;

public class SharkSpecialBehaviourControllerFactory extends SpecialBehaviourControllerFactory {
	@Override
	public AbilityControllerFactory createAbilityControllerFactory() {
		return new SharkAbilityControllerFactory();
	}

	@Override
	public ModeControllerFactory createModeControllerFactory() {
		return new SharkModeControllerFactory();
	}

}
