/**
 * 
 * @author Philippe & Marcel
 *
 */

package main;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import manager.EventManagerObservable;
import manager.GameManager;

public class Game {
	private static GameManager gameManager;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException{

		/*SceneManager sceneManager = SceneManager.getInstance();
		
		//GameManager gameManager = GameManager.getInstance();
		//NetworkManager networkManager = NetworkManager.getInstance();
		
		
		
		Title title = new Title();
		MenuBar menu = new MenuBar();*/
        	
		Frame frame = new Frame("Miam, des bonbons !");
		gameManager = GameManager.getInstance();
		EventManagerObservable eventManager = EventManagerObservable.getInstance();
        new Thread(new Run()).start();	
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(gameManager.getGameScene());
        frame.pack();
        frame.setVisible(true);
	}
	
}
