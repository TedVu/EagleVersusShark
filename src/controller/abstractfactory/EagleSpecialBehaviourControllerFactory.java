package controller.abstractfactory;

import controller.abstractfactory.contract.AbilityControllerFactory;
import controller.abstractfactory.contract.ModeControllerFactory;
import controller.abstractfactory.factory.EagleAbilityControllerFactory;
import controller.abstractfactory.factory.EagleModeControllerFactory;

/**
 * @author Ted & Kevin
 * 
 *         A concrete eagle factory that produces all special behaviour
 *         controller for eagle
 *
 */
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
