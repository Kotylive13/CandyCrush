/**
 * 
 *
 */

package main;

import graphics.window.Title;
import graphics.window.Window;
import manager.EventManagerObservable;
import manager.GameManager;

public class Game {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		
        Title title = new Title("Miam, des bonbons !");
        Window window = new Window(title);
        
		GameManager gameManager = GameManager.getInstance();
		EventManagerObservable eventManager = EventManagerObservable.getInstance();
		eventManager.addObserver(gameManager.getGameScene());	
		window.addFrame();
	}
	
}
