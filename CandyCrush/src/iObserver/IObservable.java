package iObserver;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public interface IObservable {

	public void addObserver(IObserver o);
	public void removeObserver(IObserver o);
	public void notifyObserversMousePressed();
	public void notifyObserversMouseMoved();
	public void notifyObserversMouseReleased();
}
