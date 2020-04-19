package model.enumtype;

public enum PlayerActionType {
	MOVE {

	},
	USEABILITY {

	},
	SKYMODE {

	},
	PROTECTIONMODE {

	};

	public static PlayerActionType parsePlayerActionType(final String playerActionString) {
		PlayerActionType actionType = null;

		if (playerActionString.equalsIgnoreCase(MOVE.toString())) {
			actionType = PlayerActionType.MOVE;
		} else if (playerActionString.equalsIgnoreCase(USEABILITY.toString())) {
			actionType = PlayerActionType.USEABILITY;
		} else if (playerActionString.equalsIgnoreCase(SKYMODE.toString())) {
			actionType = PlayerActionType.SKYMODE;
		} else if (playerActionString.equalsIgnoreCase(PROTECTIONMODE.toString())) {
			actionType = PlayerActionType.PROTECTIONMODE;
		}

		return actionType;
	}

}
