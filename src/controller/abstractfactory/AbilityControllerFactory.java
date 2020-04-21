package controller.abstractfactory;

import model.enumtype.PieceType;

public interface AbilityControllerFactory {
	public AbilityController createAbilityController(PieceType pieceType);

}
