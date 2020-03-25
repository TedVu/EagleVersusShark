package views.main;

import models.engine.Engine;
import models.engine.EngineImpl;

public class MockBoardClient {
	public static void main(String[] args) {
		Engine ge = EngineImpl.getSingletonInstance();
		ge.seedData();
		System.out.println(ge.getAllPiecesTed().get("LeadershipEagle").getPosition());
		System.out.println(ge.getAllPiecesTed().get("LeadershipEagle").getValidMove());
	}
}
