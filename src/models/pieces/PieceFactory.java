package models.pieces;

public class PieceFactory {

	public PieceFactory() {
	}

	public Piece generatePiece(String type, int x, int y) {
		switch (type.toUpperCase()){
			case "ATTACKINGEAGLE":
				return new AttackerEagle(x, y);
			case "VISIONARYEAGLE":
				return new VisionaryEagle(x, y);
			case "LEADERSHIPEAGLE":
				return new LeadershipEagle(x, y);
			case "HEALINGSHARK":
				return new HealingShark(x, y);
			case "DEFENSIVESHARK":
				return new DefensiveShark(x, y);
			case "AGGRESSIVESHARK":
				return new AggressiveShark(x, y);
			default:
				System.out.println("Unable to generate a new piece");
				return null;
		}
	}
}
