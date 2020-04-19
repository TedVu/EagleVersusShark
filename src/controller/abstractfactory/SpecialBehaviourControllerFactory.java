package controller.abstractfactory;

public abstract class SpecialBehaviourControllerFactory {
	public static SpecialBehaviourControllerFactory getSpecialBehaviourControllerFactory(String animal) {
		if (animal.equalsIgnoreCase("eagle")) {
			return new EagleSpecialBehaviourControllerFactory();
		} else {
		}
		return null;
	}

	public abstract AbilityControllerFactory createAbilityControllerFactory();

	public abstract ModeControllerFactory createModeControllerFactory();
}
