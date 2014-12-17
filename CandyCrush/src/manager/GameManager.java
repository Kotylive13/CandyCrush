package manager;

import graphics.window.Score;

import java.io.Serializable;

import scene.GameScene;
import algorithms.Algorithms;
import controler.GameControlerObserver;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class GameManager implements Serializable {

	private Score score;
	private GameScene gameScene;
	private GameControlerObserver gameControlerObserver;
	private Algorithms algorithms;
	private static final long serialVersionUID = 1;
	
	/** pre-initialised unique instance */
	private static GameManager INSTANCE = new GameManager();

	private GameManager() {
		score = new Score();
		gameControlerObserver = new GameControlerObserver();
		gameScene = new GameScene(gameControlerObserver.getAlgorithm());
		gameScene.addObserver(gameControlerObserver);
		gameScene.setGameManager(this);
	}

	public final static GameManager getInstance() throws InstantiationException,
			IllegalAccessException {
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
	 * @param score
	 *            the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}

	/**
	 * @return the gameScene
	 */
	public GameScene getGameScene() {
		return gameScene;
	}

	/**
	 * @param gameScene
	 *            the gameScene to set
	 */
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}

	/**
	 * @return the gameControler
	 */
	public GameControlerObserver getGameControler() {
		return gameControlerObserver;
	}

	/**
	 * @param gameControlerObserver
	 *            the gameControler to set
	 */
	public void setGameControler(GameControlerObserver gameControlerObserver) {
		this.gameControlerObserver = gameControlerObserver;
	}

	/**
	 * @return the algorithms
	 */
	public Algorithms getAlgorithms() {
		return algorithms;
	}

	/**
	 * @param algorithms
	 *            the algorithms to set
	 */
	public void setAlgorithms(Algorithms algorithms) {
		this.algorithms = algorithms;
	}

}
