package scene;

import graphics.Grid;
import graphics.Marble;
import iObserver.IObservable;
import iObserver.IObserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.util.Observer;

import manager.EventManagerObservable;
import manager.GameManager;
import algorithms.Algorithms;

/**
 * The gameScene observe the EventManager and notify the gameControllerObserver
 * 
 * @author Philippe & Marcel
 *
 */
public class GameScene extends Panel implements IObserver, IScene, IObservable {

	private static final long serialVersionUID = 1L;

	private Algorithms algorithms;
	private Image buffer;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private Grid grid;
	private IObserver observer;
	private Marble[][] gridMarble;

	private EventManagerObservable eventManager = EventManagerObservable.getInstance();
	private GameManager gameManager = null;

	public GameScene() {
		selectedX = selectedY = swappedX = swappedY = -1;
	}

	public GameScene(Algorithms algo) {
		this.algorithms = algo;
		this.grid = algo.getGrid();
		selectedX = selectedY = swappedX = swappedY = -1;
		gridMarble = (Marble[][]) grid.getMatrix();
		addMouseListener(eventManager);
		addMouseMotionListener(eventManager);
	}
	
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void paint(Graphics g) {
		try {
		if (buffer == null)
			buffer = createImage(800, 600);
		Graphics2D g2 = (Graphics2D) buffer.getGraphics();

		// fond
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// afficher la grille vide
		g2.setColor(Color.BLACK);
		for (int i = 0; i < 9; i++) {
			g2.drawLine(32 * i, 0, 32 * i,  grid.getLength()* 32 + 1);
			g2.drawLine(0, 32 * i, grid.getHeight() * 32 + 1, 32 * i);
		}

		// afficher la première case sélectionnée
		if (selectedX != -1 && selectedY != -1 && selectedX < grid.getLength() && selectedY < grid.getHeight() ) {
			g2.setColor(Color.ORANGE);
			g2.fillRect(selectedX * 32 + 1, selectedY * 32 + 1, 31, 31);
		}

		// afficher la deuxième case sélectionnée
		if (swappedX != -1 && swappedY != -1 && swappedX < grid.getLength() && swappedY < grid.getHeight()) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(swappedX * 32 + 1, swappedY * 32 + 1, 31, 31);
		}

		// afficher le contenu de la grille
		
		for (int i = 0; i < grid.getLength(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				g2.setColor(gridMarble[i][j].getColor());
				g2.fillOval(32 * i + 3, 32 * j + 3, 27, 27);
			}
		}
		// copier l'image à l'écran
		g.drawImage(buffer, 0, 0, null);
		} catch (Exception e) {
		}
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * @return the algo
	 */
	public Algorithms getAlgo() {
		return algorithms;
	}

	/**
	 * @param algo
	 *            the algo to set
	 */
	public void setAlgo(Algorithms algo) {
		this.algorithms = algo;
	}

	@Override
	public void mousePressed() {
		notifyObserversMousePressed();
		selectedX = gameManager.getGameControler().getSelectedX();	
		selectedY = gameManager.getGameControler().getSelectedY();
		repaint();

	}

	@Override
	public void mouseMoved() {
		notifyObserversMouseMoved();
		swappedX = gameManager.getGameControler().getSwappedX();
		swappedY = gameManager.getGameControler().getSwappedY();
		repaint();
	}

	@Override
	public void mouseReleased() {
		notifyObserversMouseReleased();
		selectedX = gameManager.getGameControler().getSelectedX();	
		selectedY = gameManager.getGameControler().getSelectedY();
		swappedX = gameManager.getGameControler().getSwappedX();
		swappedY = gameManager.getGameControler().getSwappedY();
		gridMarble = (Marble[][]) grid.getMatrix();
		repaint();
	}

	@Override
	public void addObserver(IObserver o) {
		if (o == null) {
			throw new NullPointerException("Null Observer");
		}
		this.observer = o;
	}

	@Override
	public void removeObserver(IObserver o) {
		this.observer = null;

	}

	@Override
	public void notifyObserversMouseMoved() {
		observer.mouseMoved();

	}

	@Override
	public void notifyObserversMousePressed() {
		observer.mousePressed();

	}

	@Override
	public void notifyObserversMouseReleased() {
		observer.mouseReleased();

	}

}
