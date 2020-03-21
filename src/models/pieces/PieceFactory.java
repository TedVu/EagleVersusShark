package models.pieces;

public class PieceFactory {

	public PieceFactory() {
	}

	public Piece generatePiece(String type, int x, int y) {

		if (type.equalsIgnoreCase("attackerEagle")) {
			return new AttackerEagle(x, y);
		}
		if (type.equalsIgnoreCase("visionaryEagle")) {
			return new VisionaryEagle(x, y);
		}

		return null;

	}

}
