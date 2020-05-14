package model.enumtype;

/**
 * @author ted &#38; kevin
 *
 */
public enum TeamType {
	/**
	 * Include: VisionaryEagle, LeadershipEagle, AttackingEagle
	 */
	EAGLE,

	/**
	 * Include: AggressiveShark, DefensiveShark, HealingShark
	 */
	SHARK;

	public static TeamType parseTeamType(final String string) {

		TeamType teamType = null;
		if (string.equalsIgnoreCase(PieceType.AGGRESSIVESHARK.toString())
				|| string.equalsIgnoreCase(PieceType.HEALINGSHARK.toString())
				|| string.equalsIgnoreCase(PieceType.DEFENSIVESHARK.toString())) {
			teamType = SHARK;
		} else if (string.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())
				|| string.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())
				|| string.equalsIgnoreCase(PieceType.VISIONARYEAGLE.toString())) {
			teamType = EAGLE;
		} else {
			throw new IllegalArgumentException("No animal type found");
		}

		return teamType;
	}
}
