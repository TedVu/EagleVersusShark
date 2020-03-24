package views.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.engine.Engine;
import models.engine.EngineImpl;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.VisionaryEagle;

public class MockClient {

	public static void main(String[] args) {

		Engine engine = EngineImpl.getSingletonInstance();
		Map<String, Piece> pieces = new HashMap<String, Piece>();
		engine.seedData();

		pieces = engine.getAllPiecesTed();
//		System.out.println(engine.getInitialPlayerActivePlayer().getPlayerType());
		
		Piece attackerPiece = null;
		Piece visionPiece = null;
		Piece leaderPiece = null;

		for (Piece piece : pieces.values()) {

//			System.out.println("piece class type : " + piece.getClass());
//			System.out.println("position : " + piece.getPosition());
////			System.out.println("after move : " + piece.moveP(1,2));

//			System.out.println(engine.setPieceActiveStatus(piece.getId(), false));

			if (piece instanceof VisionaryEagle) {
				System.out.println("after move vision : " + engine.movePiece(piece, 4, 5));
				visionPiece = piece;
			}
			if (piece instanceof LeadershipEagle) {
				System.out.println("after move leader: " + engine.movePiece(piece, 1, 2));
				leaderPiece = piece;
			}
			if (piece instanceof AttackerEagle) {
				System.out.println("after move attacker: " + engine.movePiece(piece, 2, 3));
				attackerPiece = piece;
			}
		}
//		engine.setActivePlayer("eagle", true);
//		System.out.println(engine.getActiveEagles());
//		
//		System.out.println(engine.useAbility("swap", visionPiece, attackerPiece));
//		
//		System.out.println(visionPiece.getPosition());
//		System.out.println(attackerPiece.getPosition());
		
		System.out.println(engine.useAbility("protect", leaderPiece, attackerPiece));
		
		System.out.println(attackerPiece.isImmune());

	}

}
