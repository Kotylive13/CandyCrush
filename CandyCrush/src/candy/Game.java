package candy;

import java.awt.MenuBar;

/**
 * 
 * @author Koty
 *
 */
public class Game {

	public static void main(String[] args) {

		SceneManager sceneManager = SceneManager.getInstance();
		EventManagerObservable eventManager = EventManagerObservable.getInstance();
		//GameManager gameManager = GameManager.getInstance();
		//NetworkManager networkManager = NetworkManager.getInstance();
		
		
		
		Title title = new Title();
		MenuBar menu = new MenuBar();
	}

}
