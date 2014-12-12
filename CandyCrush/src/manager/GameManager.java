package manager;

import graphics.Grid;
import graphics.window.Score;

import java.io.Serializable;

import scene.GameSceneObserver;
import algorithms.Algorithms;
import controler.GameControler;

public class GameManager implements Serializable{

	private Score score;
	private GameSceneObserver gameScene;
	private GameControler gameControler;
	private Algorithms algorithms;
	private static final long serialVersionUID = 1;
	private static GameManager INSTANCE = new GameManager();

	private GameManager() {
		score = new Score();
		try {
			gameControler = new GameControler();
			gameScene = new GameSceneObserver(gameControler.getAlgorithm());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static GameManager getInstance()
			throws InstantiationException, IllegalAccessException {
		return INSTANCE;
	}

	/** Sécurité anti-désérialisation */
	private Object readResolve() {
		return INSTANCE;
	}

	/**
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}

	/**
	 * @return the gameScene
	 */
	public GameSceneObserver getGameScene() {
		return gameScene;
	}

	/**
	 * @param gameScene the gameScene to set
	 */
	public void setGameScene(GameSceneObserver gameScene) {
		this.gameScene = gameScene;
	}

	/**
	 * @return the gameControler
	 */
	public GameControler getGameControler() {
		return gameControler;
	}

	/**
	 * @param gameControler the gameControler to set
	 */
	public void setGameControler(GameControler gameControler) {
		this.gameControler = gameControler;
	}

	/**
	 * @return the algorithms
	 */
	public Algorithms getAlgorithms() {
		return algorithms;
	}

	/**
	 * @param algorithms the algorithms to set
	 */
	public void setAlgorithms(Algorithms algorithms) {
		this.algorithms = algorithms;
	}
	
	
}
