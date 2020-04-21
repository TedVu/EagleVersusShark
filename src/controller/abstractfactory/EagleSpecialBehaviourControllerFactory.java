package controller.abstractfactory;

public class EagleSpecialBehaviourControllerFactory extends SpecialBehaviourControllerFactory {

	@Override
	public AbilityControllerFactory createAbilityControllerFactory() {
		return new EagleAbilityControllerFactory();
	}

	@Override
	public ModeControllerFactory createModeControllerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
