package iObserver;

public interface IObservable {

	public void addObserver(IObserver o);
	public void removeObserver(IObserver o);
	public void notifyObserversMouseMoved();
	public void notifyObserversMousePressed();
	public void notifyObserversMouseReleased();
}
