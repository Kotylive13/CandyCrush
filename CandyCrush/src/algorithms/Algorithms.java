package algorithms;

import static org.junit.Assert.assertNotNull;
import factory.CandyFactory;
import graphics.Candy;
import graphics.Grid;
import graphics.Marble;

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
	private Candy[][] matrix;
	private int length = 8;
	private int height = 8;
	private List<Candy> candyList;
	private boolean modified = false;
	// pour marquer les cases non alignées
	boolean marked[][] = new boolean[height][length];

	public Algorithms() throws InstantiationException, IllegalAccessException {
		grid = new Grid(height, length);
		matrix = grid.getMatrix();
		candyList = new ArrayList<Candy>();
		CandyFactory candyFactory = new CandyFactory();
		candyList = candyFactory.createCandy(Marble.class);
		initMarked();
	}

	private void initMarked() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < length; j++)
				marked[i][j] = false;
	}

	// remplir les cases vides par gravit�, et g�n�rer des cases al�atoirement
	// par le haut
	public boolean fill() {
		matrix = grid.getMatrix();
		boolean modified = false;
		Candy candy;
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = grid.getLength() - 1; j >= 0; j--) {
				if (matrix[i][j] == null) {				
					if (j == 0) {
						candy =  new Candy();
						candy = getRandomCandy();
						candy.setPosX(i);
						candy.setPosY(j);
						matrix[i][j] = candy;
						
					} else {
						matrix[i][j] = matrix[i][j - 1];
						matrix[i][j-1] = null;
					}
					modified = true;
				}
			}
		}
		grid.setMatrix(matrix);
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
		boolean modified = false;
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (matrix[i][j] != null && horizontalAligned(i, j)) {
					modified = true;
					marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
				}
				if (matrix[i][j] != null && verticalAligned(i, j)) {
					modified = true;
					marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
				}
			}
		}
		if (modified == false)
			return modified;
		// passe 2 : supprimer les cases marqu�es
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (marked[i][j]) {
					System.out.println("i: " + i + " j : " + j);
					matrix[i][j] = null;
					marked[i][j] = false;
					modified = true;
				}
			}
		}
		if (!grid.getMatrix().equals(matrix))
			grid.setMatrix(matrix);
		return modified;
	}

	public synchronized Grid getGrid() {
		return grid;
	}
}
