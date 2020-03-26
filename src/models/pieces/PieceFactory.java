package models.pieces;

public class PieceFactory {

	public PieceFactory() {
	}

	public Piece generatePiece(String type, int x, int y) {
		switch (type.toLowerCase()){
			case "AttackingEagle":
				return new AttackerEagle(x, y);
			case "VisionaryEagle":
				return new VisionaryEagle(x, y);
			case "LeadershipEagle":
				return new LeadershipEagle(x, y);
			case "HealingShark":
				return new HealingShark(x, y);
			case "DefensiveShark":
				return new DefensiveShark(x, y);
			case "AggressiveShark":
				return new AggressiveShark(x, y);
			default:
				System.out.println("Unable to generate a new piece");
				return null;
		}
	}
}
