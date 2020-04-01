package models.pieces;

public class PieceFactory {

	public PieceFactory() {
	}

	public Piece generatePiece(String type, int x, int y) {
		if (type.equalsIgnoreCase("AttackingEagle")) {
			return new AttackerEagle(x, y);
		}
		if (type.equalsIgnoreCase("VisionaryEagle")) {
			return new VisionaryEagle(x, y);
		}
		if (type.equalsIgnoreCase("LeadershipEagle")) {
			return new LeadershipEagle(x, y);
		}
		if (type.equalsIgnoreCase("AggressiveShark")) {
			return new AggressiveShark(x, y);
		}
		if (type.equalsIgnoreCase("DefensiveShark")) {
			return new DefensiveShark(x, y);
		}
		if (type.equalsIgnoreCase("HealingShark")) {
			return new HealingShark(x, y);
		}

		return null;
	}
}
