package controller.abstractfactory;

import controller.abstractfactory.factory.AbilityControllerFactory;
import controller.abstractfactory.factory.EagleAbilityControllerFactory;
import controller.abstractfactory.factory.EagleModeControllerFactory;
import controller.abstractfactory.factory.ModeControllerFactory;

public class EagleSpecialBehaviourControllerFactory extends SpecialBehaviourControllerFactory {

	@Override
	public AbilityControllerFactory createAbilityControllerFactory() {
		return new EagleAbilityControllerFactory();
	}

	@Override
	public ModeControllerFactory createModeControllerFactory() {
		return new EagleModeControllerFactory(); 
	}

}
