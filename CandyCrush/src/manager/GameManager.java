package manager;

import graphics.Grid;
import graphics.window.Score;

import java.io.Serializable;

import scene.GameSceneObserver;
import algorithms.Algorithms;
import controler.GameControler;

public class GameManager implements Serializable, Runnable {

	private Grid grid;
	private Score score;
	private GameSceneObserver gameScene;
	private GameControler gameControler;
	private Algorithms algorithms;
	

	private GameManager() {
		grid = new Grid();
		score = new Score();
		gameScene = new GameSceneObserver();
		try {
			algorithms = new Algorithms();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static GameManager INSTANCE = new GameManager();

	public static GameManager getInstance()
			throws InstantiationException, IllegalAccessException {
		return INSTANCE;
	}

	/** Sécurité anti-désérialisation */
	private Object readResolve() {
		return INSTANCE;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
