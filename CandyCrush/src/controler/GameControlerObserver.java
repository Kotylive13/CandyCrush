package controler;

import iObserver.IObserver;

import java.util.Timer;
import java.util.TimerTask;

import manager.EventManagerObservable;
import algorithms.Algorithms;

public class GameControlerObserver implements IObserver {

	private Algorithms algorithm;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private EventManagerObservable eventManager = EventManagerObservable
			.getInstance();

	public GameControlerObserver() {
		algorithm = new Algorithms();
		algorithm.fill();
		selectedX = selectedY = swappedX = swappedY = -1;
		while (algorithm.removeAlignments())
			while (algorithm.fillAfterDestroyMarbles()) {
			}
	}

	public Algorithms getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithms algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void mousePressed() {
		selectedX = eventManager.getMouseEvent().getX() / 32;
		selectedY = eventManager.getMouseEvent().getY() / 32;

	}

	@Override
	public void mouseMoved() {
		if (selectedX != -1 && selectedY != -1) {
			swappedX = eventManager.getMouseEvent().getX() / 32;
			swappedY = eventManager.getMouseEvent().getY() / 32;
			// si l'échange n'est pas valide, on cache la deuxième case
			if (!algorithm
					.isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
				swappedX = swappedY = -1;

			}
		}

	}

	@Override
	public void mouseReleased() {

		// lorsque l'on relâche la souris il faut faire l'échange et cacher les
		// cases
		if (selectedX != -1 && selectedY != -1 && swappedX != -1
				&& swappedY != -1) {
			algorithm.swap(selectedX, selectedY, swappedX, swappedY);
			while (algorithm.removeAlignments()) {
				while (algorithm.fillAfterDestroyMarbles()) {
				}
			}
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

	/**
	 * @return the eventManager
	 */
	public EventManagerObservable getEventManager() {
		return eventManager;
	}

	/**
	 * @param eventManager
	 *            the eventManager to set
	 */
	public void setEventManager(EventManagerObservable eventManager) {
		this.eventManager = eventManager;
	}
}
