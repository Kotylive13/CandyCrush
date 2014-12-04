package algorithms;

import factory.CandyFactory;
import graphics.Candy;
import graphics.Grid;
import graphics.Marble;

import java.awt.Color;
import java.util.ArrayList;
/**
 * 
 * @author Philippe & Marcel
 *
 */

import java.util.List;
import java.util.Random;

public class Algorithms {

	private Grid grid;
	private final int colorsLength = 7;
	private Candy[][] matrix;
	private int length = 8;
	private int height = 8;
	private List<Candy> candyList;
	private boolean takeGrid = false;
	// pour marquer les cases non alignées
	boolean marked[][] = new boolean[8][8];

	public Algorithms() throws InstantiationException, IllegalAccessException {
		grid = new Grid(length, height);
		matrix = new Candy[length][height];
		candyList = new ArrayList<Candy>();
		CandyFactory candyFactory = new CandyFactory();
		candyList = candyFactory.createCandy(Marble.class);
	}

	// remplir les cases vides par gravit�, et g�n�rer des cases al�atoirement
	// par le haut
	public boolean fill() {
		boolean modified = false;
		matrix = grid.getMatrix();
		Candy candy;
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = grid.getLength() - 1; j >= 0; j--) {
				/*if (matrix[j][i] == null) {
					if (i == 0) {
						Candy candy = new Candy();
						candy = getRandomCandy();
						candy.setPosX(j);
						candy.setPosY(i);
						matrix[j][i] = candy;
					} else {
						matrix[j][i] = matrix[j][i - 1];
						matrix[j][i - 1] = null;
					}
					modified = true;
				}*/
				candy = getRandomCandy();
				candy.setPosX(i);
				candy.setPosY(j);
				matrix[i][j] = candy;
			}
		}
		grid.setMatrix(matrix);
		for (int i = 0; i < grid.getHeight(); i++)
			for (int j = 0; j < grid.getLength(); j++)
				System.out.println(((Marble)(matrix[i][j])));
			
		return modified;
	}

	public boolean horizontalAligned(int i, int j) {
		matrix = grid.getMatrix();
		if (i < 0 || j < 0 || i >= 6 || j >= 8)
			return false;
		if (matrix[i][j].equals(matrix[i + 1][j])
				&& matrix[i][j].equals(matrix[i + 2][j]))
			return true;
		return false;
	}

	public boolean verticalAligned(int i, int j) {
		matrix = grid.getMatrix();
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if (matrix[i][j].equals(matrix[i][j + 1])
				&& matrix[i][j].equals(matrix[i][j + 2]))
			return true;
		return false;
	}

	private Candy getRandomCandy() {
		Random randomizer = new Random();
		return candyList.get(randomizer.nextInt(candyList.size()));
	}

	// d�termine si l'�change entre deux cases est valide
	public boolean isValidSwap(int x1, int y1, int x2, int y2) {
		matrix = grid.getMatrix();
		// il faut que les cases soient dans la grille
		if (x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1)
			return false;
		// que les cases soient � c�t� l'une de l'autre
		if (Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1)
			return false;
		// et que les couleurs soient diff�rentes
		if (matrix[x1][y1].equals(matrix[x2][y2]))
			return false;

		// alors on effectue l'�change
		swap(x1, y1, x2, y2);

		// et on vérifie que �a cr�� un nouvel alignement
		boolean newAlignment = false;
		for (int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(x1 - i, y1);
			newAlignment |= horizontalAligned(x2 - i, y2);
			newAlignment |= verticalAligned(x1, y1 - i);
			newAlignment |= verticalAligned(x2, y2 - i);
		}

		// puis on annule l'échange
		swap(x1, y1, x2, y2);
		return newAlignment;
	}

	// échanger le contenu de deux cases
	public void swap(int x1, int y1, int x2, int y2) {
		matrix = grid.getMatrix();
		Candy tmp = matrix[x1][y1];
		matrix[x1][y1] = matrix[x2][y2];
		matrix[x2][y2] = tmp;
	}

	// supprimer les alignements
	public boolean removeAlignments() {
		matrix = grid.getMatrix();
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (matrix[i][j] != null && horizontalAligned(i, j)) {
					marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
				}
				if (matrix[i][j] != null && verticalAligned(i, j)) {
					marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
				}
			}
		}
		// passe 2 : supprimer les cases marqu�es
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (marked[i][j]) {
					matrix[i][j] = null;
					marked[i][j] = false;
					modified = true;
				}
			}
		}
		return modified;
	}
	
	public synchronized Grid getGrid() {
		if (takeGrid == false) {
			takeGrid = true;
			return grid;
		} else {
			return null;
		}
	}
	
	public void releaseGrid() {
		takeGrid = false;
	}
}
