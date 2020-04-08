package views.main;

import java.util.HashMap;
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
		engine.pieceOperator().initializePiece();

		pieces = engine.pieceOperator().getAllPieces();
		// System.out.println(engine.getInitialPlayerActivePlayer().getPlayerType());

		Piece attackerPiece = null;
		Piece visionPiece = null;
		Piece leaderPiece = null;

		for (Piece piece : pieces.values()) {

			// System.out.println("piece class type : " + piece.getClass());
			// System.out.println("position : " + piece.getPosition());
			//// System.out.println("after move : " + piece.moveP(1,2));

			// System.out.println(engine.setPieceActiveStatus(piece.getId(), false));

		}
		// engine.setActivePlayer("eagle", true);
		// System.out.println(engine.getActiveEagles());
		//
		// System.out.println(engine.useAbility("swap", visionPiece, attackerPiece));
		//
		// System.out.println(visionPiece.getPosition());
		// System.out.println(attackerPiece.getPosition());

		// System.out.println(engine.useAbility("protect", leaderPiece, attackerPiece));
		//
		// System.out.println(attackerPiece.isImmune());

//		System.out.println(engine.useAbility("capture", attackerPiece, visionPiece));

	}

}
