package main;

import manager.GameManager;

public class Run implements Runnable{
	
	private GameManager gameManager;
	
	public Run() {}
	public Run(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public void setGameManager(){
		
	}
	@Override
	public void run() {
        while(true) {
            // un pas de simulation toutes les 100ms
            try { Thread.currentThread().sleep(100); } catch(InterruptedException e) { }
            if(!gameManager.getGameControler().getAlgorithm().fill()) {
            	gameManager.getGameControler().getAlgorithm().removeAlignments();
            }
            // redessiner
            gameManager.getGameScene().repaint();
        }
    }
}
