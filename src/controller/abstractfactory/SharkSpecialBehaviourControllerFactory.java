package controller.abstractfactory;

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
