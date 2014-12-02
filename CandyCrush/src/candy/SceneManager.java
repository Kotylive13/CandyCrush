package candy;

import java.util.List;

/**
 * 
 * Cette classe est un Singleton
 *
 */

public final class SceneManager implements Scene {

	private List<Scene> listScenes;
	
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
	
	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
