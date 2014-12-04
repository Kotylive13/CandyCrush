package main;

import manager.GameManager;

public class Run implements Runnable{
	
	private GameManager gameManager;
	
	public Run() {
		try {
			this.gameManager = GameManager.getInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setGameManager(){
		
	}
	@Override
	public void run() {
        while(true) {
            // un pas de simulation toutes les 100ms
            try { Thread.currentThread().sleep(100); } catch(InterruptedException e) { }
            
            // redessiner
            gameManager.getGameScene().repaint();
        }
    }
}
