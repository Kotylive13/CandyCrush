package manager;

import iObserver.IObservable;
import iObserver.IObserver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import event.SoundEvent;

/**
 * 
 * @author Philippe & Marcel
 *
 */

/**
 * 
 * Cette classe est un Singleton et un objet observable
 *
 */

public final class EventManagerObservable implements IObservable, MouseListener, MouseMotionListener {

	private MouseEvent mouseEvent;
	private List<IObserver> observers;
	private final Object Mutex = new Object();
	private SoundEvent soundEvent;

	/** pre-initialised unique instance */
	private static EventManagerObservable INSTANCE = new EventManagerObservable();

	private EventManagerObservable() {
		this.observers = new ArrayList<IObserver>();
	}

	public final static EventManagerObservable getInstance() {
		return INSTANCE;
	}

	@Override
	public void addObserver(IObserver o) {
		if (o == null) {
			throw new NullPointerException("Null Observer");
		}
		synchronized (Mutex) {
			if (!observers.contains(o))
				observers.add(o);
		}
	}

	@Override
	public void removeObserver(IObserver o) {
		synchronized (Mutex) {
			observers.remove(o);
		}
	}
	
	@Override
	public void notifyObserversMousePressed() {
		
		for (IObserver observer : observers) {
			observer.mousePressed();
		}
	}

	@Override
	public void notifyObserversMouseMoved() {
		
		for (IObserver observer : observers) {
			observer.mouseMoved();
		}
	}
	
	@Override
	public void notifyObserversMouseReleased() {
		
		for (IObserver observer : observers) {
			observer.mouseReleased();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		
		this.mouseEvent = mouseEvent;
		notifyObserversMousePressed();
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
		
		this.mouseEvent = mouseEvent;
		notifyObserversMouseMoved();
	}

	
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		
		notifyObserversMouseReleased();
	}
	 
	
	public MouseEvent getMouseEvent() {
		return mouseEvent;
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
	
}
