package controller.abstractfactory;

import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.contract.ModeControllerFactory;
import controller.abstractfactory.factory.SharkAbilityControllerFactory;
import controller.abstractfactory.factory.SharkModeControllerFactory;

/**
 * @author Ted & Kevin
 * 
 *         A concrete shark factory that produces all special behaviour
 *         controller for shark
 *
 */
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
