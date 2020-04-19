package controller.abstractfactory;

public class EagleAbilityControllerFactory implements AbilityControllerFactory {
	@Override
	public AbilityController createAbilityController(String eagleType) {
		if (eagleType.equalsIgnoreCase("VisionaryEagle")) {
			return new VisionaryEagleAbilityController();
		} else if (eagleType.equalsIgnoreCase("AttackingEagle")) {

		}
		return null;
		// TODO Auto-generated method stub
	}
}
