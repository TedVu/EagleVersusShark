package model.enumtype;

public enum PlayerActionType {
	MOVE {

	},
	USEABILITY {

	},
	EAGLEMODE {

	},
	SHARKMODE {

	};

	public static PlayerActionType parsePlayerActionType(final String playerActionString) {
		PlayerActionType actionType = null;

		if (playerActionString.equalsIgnoreCase(MOVE.toString())) {
			actionType = PlayerActionType.MOVE;
		} else if (playerActionString.equalsIgnoreCase(USEABILITY.toString())) {
			actionType = PlayerActionType.USEABILITY;
		} else if (playerActionString.equalsIgnoreCase(EAGLEMODE.toString())) {
			actionType = PlayerActionType.EAGLEMODE;
		} else if (playerActionString.equalsIgnoreCase(SHARKMODE.toString())) {
			actionType = PlayerActionType.SHARKMODE;
		}

		return actionType;
	}

}
