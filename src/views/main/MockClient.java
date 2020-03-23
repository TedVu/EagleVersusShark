package views.main;

import java.util.ArrayList;
import java.util.List;

import models.engine.Engine;
import models.engine.EngineImpl;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.VisionaryEagle;

public class MockClient {

	public static void main(String[] args) {

		Engine engine = EngineImpl.getSingletonInstance();
		List<Piece> pieces = new ArrayList<Piece>();
		engine.seedData();

		pieces = engine.getAllPieces();
//		System.out.println(engine.getInitialPlayerActivePlayer().getPlayerType());

		for (Piece piece : pieces) {

//			System.out.println("piece class type : " + piece.getClass());
//			System.out.println("position : " + piece.getPosition());
////			System.out.println("after move : " + piece.moveP(1,2));

//			System.out.println(engine.setPieceActiveStatus(piece.getId(), false));

			if (piece instanceof VisionaryEagle) {
				System.out.println("after move vision : " + engine.movePiece(piece, 2, 7));
			}
			if (piece instanceof LeadershipEagle) {
				System.out.println("after move leader: " + engine.movePiece(piece, 2, 4));
			}
			if (piece instanceof AttackerEagle) {
				System.out.println("after move attacker: " + engine.movePiece(piece, 1, 4));
			}
		}
//		engine.setActivePlayer("eagle", true);
		System.out.println(engine.getActiveEagles());

	}

}
