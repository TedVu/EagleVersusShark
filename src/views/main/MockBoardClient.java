package views.main;

import models.engine.Engine;
import models.engine.EngineImpl;

public class MockBoardClient {
	public static void main(String[] args) {
		Engine ge = EngineImpl.getSingletonInstance();
		ge.pieceOperator().initializePiece();
		System.out.println(ge.pieceOperator().getAllPieces().get("LeadershipEagle").getPosition());
		System.out.println(ge.pieceOperator().getAllPieces().get("LeadershipEagle").getValidMove());
	}
}
