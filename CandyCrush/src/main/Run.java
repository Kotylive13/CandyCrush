package main;

import manager.GameManager;

public class Run implements Runnable {
	public Run() {
	}

	public void setGameManager() {

	}

	@Override
	public void run() {
		while (true) {
			// un pas de simulation toutes les 100ms
			try {
				Thread.currentThread();
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			// s'il n'y a pas de case vide, chercher des alignements
			try {
				if (!GameManager.getInstance().getGameControler()
						.getAlgorithm().fill()) {
					//System.out.println("boubou");
					GameManager.getInstance().getGameControler().getAlgorithm()
							.removeAlignments();
				}
				// redessiner
				GameManager.getInstance().getGameScene().repaint();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
