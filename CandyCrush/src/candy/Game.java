/**
 * 
 * @author Philippe & Marcel
 *
 */

package candy;

import java.awt.MenuBar;

public class Game {

	public static void main(String[] args) {

		SceneManager sceneManager = new SceneManager();
		GameManager gameManager = new GameManager();
		EventManagerObservable eventManager = new EventManagerObservable();
		NetworkManager networkManager = new NetworkManager();
		Title title = new Title();
		MenuBar menu = new MenuBar();
	}

}
