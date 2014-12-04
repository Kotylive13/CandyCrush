package manager;

import java.util.List;

import scene.IScene;

/**
 * 
 * Cette classe est un Singleton
 *
 */

public final class SceneManager {

	private List<IScene> listScenes;

	/** pre-initialised unique instance */
	private static SceneManager INSTANCE = new SceneManager();

	private SceneManager() {

	}

	public final static SceneManager getInstance() {
		return INSTANCE;
	}

	public void popScene() {

	}

	public void pushScene() {

	}

}
