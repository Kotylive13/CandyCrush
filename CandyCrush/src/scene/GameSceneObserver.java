package scene;

import graphics.Grid;
import graphics.Marble;
import iObserver.IObserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;

import manager.EventManagerObservable;
import algorithms.Algorithms;

public class GameSceneObserver extends Panel implements IObserver, IScene {

	private static final long serialVersionUID = 1L;

	private Algorithms algo;
	private Image buffer;
	private int selectedX;
	private int selectedY;
	private int swappedX;
	private int swappedY;
	private Grid grid;

	EventManagerObservable eventManager = EventManagerObservable.getInstance();

	public GameSceneObserver() {
		selectedX = selectedY = swappedX = swappedY = -1;
	}

	public GameSceneObserver(Algorithms algo) {
		this.algo = algo;
		this.grid = algo.getGrid();
		selectedX = selectedY = swappedX = swappedY = -1;
		addMouseListener(eventManager);
		addMouseMotionListener(eventManager);
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
			g2.drawLine(32 * i, 0, 32 * i, 8 * 32 + 1);
			g2.drawLine(0, 32 * i, 8 * 32 + 1, 32 * i);
		}

		// afficher la première case sélectionnée
		if (selectedX != -1 && selectedY != -1) {
			g2.setColor(Color.ORANGE);
			g2.fillRect(selectedX * 32 + 1, selectedY * 32 + 1, 31, 31);
		}

		// afficher la deuxième case sélectionnée
		if (swappedX != -1 && swappedY != -1) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(swappedX * 32 + 1, swappedY * 32 + 1, 31, 31);
		}

		// afficher le contenu de la grille
		Marble[][] gridMarble = (Marble[][]) grid.getMatrix();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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
		return algo;
	}

	/**
	 * @param algo
	 *            the algo to set
	 */
	public void setAlgo(Algorithms algo) {
		this.algo = algo;
	}

	// taille de la fen�tre
	public Dimension getPreferredSize() {
		return new Dimension(32 * 8 + 1, 32 * 8 + 1);
	}

	@Override
	public void mousePressed() {

		// on appuie sur le bouton de la souris : récupérer les coordonnées de
		// la première case
		selectedX = eventManager.getMouseEvent().getX() / 32;
		selectedY = eventManager.getMouseEvent().getY() / 32;

	}

	@Override
	public void mouseMoved() {

		// on bouge la souris : récupérer les coordonnées de la deuxième case
		if (selectedX != -1 && selectedY != -1) {
			swappedX = eventManager.getMouseEvent().getX() / 32;
			swappedY = eventManager.getMouseEvent().getY() / 32;
			// si l'échange n'est pas valide, on cache la deuxième case
			if (!algo.isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
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
			algo.swap(selectedX, selectedY, swappedX, swappedY);
			algo.removeAlignments();
			System.out.println("coucou");
			algo.fillAfterDestroyMarbles();
		}
		selectedX = selectedY = swappedX = swappedY = -1;
	}

}
