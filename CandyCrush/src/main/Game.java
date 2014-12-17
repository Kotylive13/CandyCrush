package main;

import graphics.window.Title;
import graphics.window.Window;
import manager.EventManagerObservable;
import manager.GameManager;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class Game {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		
        Window window = new Window(new Title("Miam, des bonbons !"));
        
		GameManager gameManager = GameManager.getInstance();
		EventManagerObservable eventManager = EventManagerObservable.getInstance();
		eventManager.addObserver(gameManager.getGameScene());	
		window.addFrame();
	}
}
