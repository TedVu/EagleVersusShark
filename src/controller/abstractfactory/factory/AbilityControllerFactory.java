package controller.abstractfactory.factory;

import controller.abstractfactory.AbilityController;
import model.enumtype.PieceType;

public interface AbilityControllerFactory {

	public AbilityController createAbilityController(PieceType pieceType);

}
