package scene;

import graphics.Grid;
import graphics.Marble;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;

import manager.EventManagerObservable;
import algorithms.Algorithms;

public class GameSceneObserver extends Panel implements IScene {

	private Algorithms algo;

	private Image buffer;
	private int selectedX = -1, selectedY = -1;
	private int swappedX = -1, swappedY = -1;
	private Color colors[] = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
			Color.GRAY, Color.PINK, Color.CYAN };
	private Grid grid;

	public GameSceneObserver() {
	}

	public GameSceneObserver(Algorithms algo, Grid grid) {
		this.algo = algo;
		this.grid = grid;
		addMouseListener(EventManagerObservable.getInstance());
        addMouseMotionListener(EventManagerObservable.getInstance());
	}

	@Override
	public void paint(Graphics g) {
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

		// afficher la premi�re case s�lectionn�e
		if (selectedX != -1 && selectedY != -1) {
			g2.setColor(Color.ORANGE);
			g2.fillRect(selectedX * 32 + 1, selectedY * 32 + 1, 31, 31);
		}

		// afficher la deuxi�me case s�lectionn�e
		if (swappedX != -1 && swappedY != -1) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(swappedX * 32 + 1, swappedY * 32 + 1, 31, 31);
		}

		// afficher le contenu de la grille
		Marble[][] gridMarble = (Marble[][]) grid.getMatrix();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.println(i+ ": i "+j+ " j");
				//System.err.println(gridMarble[i][j].getColor());
				g2.setColor(gridMarble[i][j].getColor());
				g2.fillOval(32 * i + 3, 32 * j + 3, 27, 27);
			}
		}

		// copier l'image � l'�cran
		g.drawImage(buffer, 0, 0, null);
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
}
