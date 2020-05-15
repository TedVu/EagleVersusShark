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

	public static TeamType parseTeamType(String animal) {
		TeamType team = null;
		if (animal.equalsIgnoreCase(PieceType.AGGRESSIVESHARK.toString())
				|| animal.equalsIgnoreCase(PieceType.DEFENSIVESHARK.toString())
				|| animal.equalsIgnoreCase(PieceType.HEALINGSHARK.toString())) {
			team = TeamType.SHARK;
		} else if (animal.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())
				|| animal.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())
				|| animal.equalsIgnoreCase(PieceType.VISIONARYEAGLE.toString())) {
			team = TeamType.EAGLE;
		}
		return team;
	}
}
