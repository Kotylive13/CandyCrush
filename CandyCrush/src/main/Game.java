/**
 * 
 * @author Philippe & Marcel
 *
 */

package main;

import graphics.window.Title;
import graphics.window.Window;

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
        Title title = new Title("Miam, des bonbons !");
        Window window = new Window(title);
		gameManager = GameManager.getInstance();
		EventManagerObservable eventManager = EventManagerObservable.getInstance();
		window.addFrame();
        new Thread(new Run()).start();	
	}
	
}
