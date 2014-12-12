package manager;

import iObserver.IObservable;
import iObserver.IObserver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import scene.GameSceneObserver;
import event.SoundEvent;

/**
 * 
 * Cette classe est un Singleton et un objet observable
 *
 */

public final class EventManagerObservable implements IObservable,
		MouseListener, MouseMotionListener {

	private SoundEvent soundEvent;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private ArrayList<IObserver> observers;
	private final Object MUTEX = new Object();

	/** pre-initialised unique instance */
	private static EventManagerObservable INSTANCE = new EventManagerObservable();

	private EventManagerObservable() {
		selectedX = selectedY = swappedX = swappedY = -1;
		this.observers = new ArrayList<>();
	}

	public final static EventManagerObservable getInstance() {
		return INSTANCE;
	}

	@Override
	public void addObserver(IObserver o) {
		if (o == null) {
			throw new NullPointerException("Null Observer");
		}
		synchronized (MUTEX) {
			if (!observers.contains(o))
				observers.add(o);
		}
	}

	@Override
	public void removeObserver(IObserver o) {
		synchronized (MUTEX) {
			observers.remove(o);
		}
	}

	@Override
	public void notifyObservers() {
		
		for (IObserver observer : observers) {
			observer.update(this);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// on bouge la souris : récupérer les coordonnées de la deuxième case
//		if (selectedX != -1 && selectedY != -1) {
//			swappedX = e.getX() / 32;
//			swappedY = e.getY() / 32;
//			// si l'échange n'est pas valide, on cache la deuxième case
//			if (!gameScene.getAlgo().isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
//				swappedX = swappedY = -1;
//			}
//		}
//		gameScene.repaint();
//		notifyObservers();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de la première case
		selectedX = e.getX() / 32;
		selectedY = e.getY() / 32;
		notifyObservers();
	}

	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// lorsque l'on relâche la souris il faut faire l'échange et cacher les cases
//		if (selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
//			gameScene.getAlgo().swap(selectedX, selectedY, swappedX, swappedY);
//		}
//	  selectedX = selectedY = swappedX = swappedY = -1;
//	  gameScene.repaint();
//	}
	 
	
	public int getSelectedX() {
		return selectedX;
	}
	
	public int getSelectedY() {
		return selectedY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
