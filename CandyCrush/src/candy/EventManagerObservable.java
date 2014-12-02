package candy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * 
 * Cette classe est un Singleton et un objet observable
 *
 */

public final class EventManagerObservable implements Observable, MouseListener, MouseMotionListener {

	private SoundEvent soundEvent;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private ArrayList<Observer> observers;
	
	/** pre-initialised unique instance */
	private static EventManagerObservable INSTANCE = new EventManagerObservable();
	
	private EventManagerObservable() {
		selectedX = -1;
		selectedY = -1;
		swappedX = -1;
		swappedY = -1;
	}
	
	public final static EventManagerObservable getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {		
		for (int i = 0; i < observers.size(); i++){
			Observer o = observers.get(i);
			o.update(this);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// on bouge la souris : récupérer les coordonnées de la deuxième case
        if(selectedX != -1 && selectedY != -1) {
            swappedX = e.getX() / 32;
            swappedY = e.getY() / 32;
            // si l'échange n'est pas valide, on cache la deuxième case
            if(!isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
                swappedX = swappedY = -1;
            }
        }
        repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de la première case
        selectedX = e.getX() / 32;
        selectedY = e.getY() / 32;
        repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// lorsque l'on relâche la souris il faut faire l'échange et cacher les cases
        if(selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
            swap(selectedX, selectedY, swappedX, swappedY);
        }
        selectedX = selectedY = swappedX = swappedY = -1;
        repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
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
}
