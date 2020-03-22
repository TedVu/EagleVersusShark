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

		return null;

	}

}
