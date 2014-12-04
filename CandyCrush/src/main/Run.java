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
         // s'il n'y a pas de case vide, chercher des alignements
            if(gameManager.getAlgorithms()!= null && !gameManager.getAlgorithms().fill()) {
            	System.out.println("coucou");
            	System.out.println(gameManager.getAlgorithms().removeAlignments());
            }
            // redessiner
            gameManager.getGameScene().repaint();
        }
    }
}
