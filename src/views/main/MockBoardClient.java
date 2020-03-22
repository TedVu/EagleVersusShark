package views.main;

import models.engine.Engine;
import models.engine.EngineImpl;

public class MockBoardClient {
	public static void main(String[] args) {
		Engine ge = EngineImpl.getSingletonInstance();
		ge.seedData();
		System.out.println(ge.getBoard());

		ge.movePiece(ge.getAllPiecesTed().get(1), 0, 2);
		System.out.println("\n");
		System.out.println(ge.getBoard());
		ge.movePiece(ge.getAllPiecesTed().get(1), 8, 7);
		System.out.println("\n");
		System.out.println(ge.getBoard());
	}
}
