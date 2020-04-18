package controller.abstractfactory;

public class EagleAbilityControllerFactory implements AbilityControllerFactory {
	@Override
	public AbilityController createAbilityController(String ability) {
		if (ability.equalsIgnoreCase("swap")) {
			return new VisionaryEagleAbilityController();
		} else if (ability.equalsIgnoreCase("capture")) {

		}
		return null;
		// TODO Auto-generated method stub
	}
}
