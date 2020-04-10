package views.main;

import models.engine.Engine;
import models.engine.EngineImpl;

public class MockBoardClient {
	public static void main(String[] args) {
		Engine ge = EngineImpl.getSingletonInstance();
		ge.initializePiece();
		System.out.println(ge.getAllPieces().get("LeadershipEagle").getPosition());
//		System.out.println(ge.getAllPieces().get("LeadershipEagle").getValidMove());
	}
}
