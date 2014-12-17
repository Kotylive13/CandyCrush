package controler;

import iObserver.IObserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import manager.EventManagerObservable;
import algorithms.Algorithms;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class GameControlerObserver implements IObserver {

	private Algorithms algorithms;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private Timer timer;
	private EventManagerObservable eventManager = EventManagerObservable
			.getInstance();

	public GameControlerObserver(int height, int length) {
		algorithms = new Algorithms(height, length);
		algorithms.fill();
		createTimer(100);
		selectedX = selectedY = swappedX = swappedY = -1;
		while (algorithms.removeAlignments()) {
			while (algorithms.fillAfterDestroyMarbles()) {
			}
		}
	}
	
	private void createTimer(int time) {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!algorithms.fillAfterDestroyMarbles() && !algorithms.removeAlignments()) {
					timer.stop();
				}
				
			}
		};
		timer = new Timer(time, actionListener);
	}

	public Algorithms getAlgorithm() {
		return algorithms;
	}

	public void setAlgorithm(Algorithms algorithm) {
		this.algorithms = algorithm;
	}

	@Override
	public void mousePressed() {
		selectedX = eventManager.getMouseEvent().getX() / 32;
		selectedY = eventManager.getMouseEvent().getY() / 32;
	}

	@Override
	public void mouseMoved() {
		if (selectedX != -1 && selectedY != -1 && selectedX < 8
				&& selectedY < 8) {
			swappedX = eventManager.getMouseEvent().getX() / 32;
			swappedY = eventManager.getMouseEvent().getY() / 32;
			// si l'échange n'est pas valide, on cache la deuxième case
			if (!algorithms.isValidSwap(selectedX, selectedY, swappedX,
					swappedY)) {
				swappedX = swappedY = -1;
			}
		}
	}

	@Override
	public void mouseReleased() {

		// lorsque l'on relâche la souris il faut faire l'échange et cacher les
		// cases
		if (selectedX != -1 && selectedY != -1 && swappedX != -1
				&& swappedY != -1 && selectedX < 8 && selectedY < 8
				&& swappedX < 8 && swappedY < 8) {
			algorithms.swap(selectedX, selectedY, swappedX, swappedY);
			timer.start();
		}
		selectedX = selectedY = swappedX = swappedY = -1;
	}

	/**
	 * @return the selectedX
	 */
	public int getSelectedX() {
		return selectedX;
	}

	/**
	 * @param selectedX
	 *            the selectedX to set
	 */
	public void setSelectedX(int selectedX) {
		this.selectedX = selectedX;
	}

	/**
	 * @return the selectedY
	 */
	public int getSelectedY() {
		return selectedY;
	}

	/**
	 * @param selectedY
	 *            the selectedY to set
	 */
	public void setSelectedY(int selectedY) {
		this.selectedY = selectedY;
	}

	/**
	 * @return the swappedX
	 */
	public int getSwappedX() {
		return swappedX;
	}

	/**
	 * @param swappedX
	 *            the swappedX to set
	 */
	public void setSwappedX(int swappedX) {
		this.swappedX = swappedX;
	}

	/**
	 * @return the swappedY
	 */
	public int getSwappedY() {
		return swappedY;
	}

	/**
	 * @param swappedY
	 *            the swappedY to set
	 */
	public void setSwappedY(int swappedY) {
		this.swappedY = swappedY;
	}
}
