package algorithms;

import factory.CandyFactory;
import graphics.Candy;
import graphics.Grid;
import graphics.Marble;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class Algorithms {

	private Grid grid;
	private Candy[][] matrix;
	private int length = 8;
	private int height = 8;
	private List<Candy> candyList;
	private boolean marked[][] = new boolean[length][height]; 	// To mark the unaligned boxes

	public Algorithms() {
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

	public void fill() {
		Candy candy;
		for (int i = 0; i < grid.getLength(); i++) {
			for (int j = grid.getHeight() - 1; j >= 0; j--) {
				if (matrix[i][j] == null) {
					candy = new Marble();
					candy = getRandomCandy();
					candy.setPosX(i);
					candy.setPosY(j);
					matrix[i][j] = candy;
				}
			}
		}
		grid.setMatrix(matrix);
	}

	// remplir les cases vides par gravit�, et g�n�rer des cases al�atoirement
	// par le haut
	public boolean fillAfterDestroyMarbles() {
		matrix = grid.getMatrix();
		boolean modified = false;
		Candy candy;
		Marble marble = new Marble(0, 0, Color.WHITE);
		for (int i = 0; i < grid.getLength(); i++) {
			for (int j = grid.getHeight() - 1; j >= 0; j--) {
				marble.setPosX(i);
				marble.setPosY(j);
				if (((Marble)matrix[i][j]).getColor().equals(Color.WHITE)) {
					if (j == 0) {
						candy = new Marble();
						candy = getRandomCandy();
						candy.setPosX(i);
						candy.setPosY(j);
						matrix[i][j] = candy;

					} else {
						Marble marble2 = new Marble(i, j-1, Color.WHITE);
						matrix[i][j] = matrix[i][j - 1];
						matrix[i][j - 1] = marble2;
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
		if (((Marble)matrix[i][j]).getColor().equals(((Marble)matrix[i+1][j]).getColor())
				&& ((Marble)matrix[i][j]).getColor().equals(((Marble)matrix[i+2][j]).getColor()))
			return true;
		return false;
	}

	public boolean verticalAligned(int i, int j) {
		matrix = grid.getMatrix();
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if (((Marble)matrix[i][j]).getColor().equals(((Marble)matrix[i][j+1]).getColor())
				&& ((Marble)matrix[i][j]).getColor().equals(((Marble)matrix[i][j+2]).getColor()))
			return true;
		return false;
	}

	private Candy getRandomCandy() {
		Random randomizer = new Random();
		return candyList.get(randomizer.nextInt(candyList.size()));
	}

	// determine if the exchange between two bowes is valid
	public boolean isValidSwap(int x1, int y1, int x2, int y2) {
		matrix = (Marble[][])grid.getMatrix();
		// il faut que les cases soient dans la grille
		if (x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1)
			return false;
		// que les cases soient � c�t� l'une de l'autre
		if (Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1)
			return false;
		// et que les couleurs soient diff�rentes
		if (matrix[x1][y1].equals(matrix[x2][y2]))
			return false;

		// alors on effectue l'échange
		swap(x1, y1, x2, y2);

		// et on vérifie que ça crée un nouvel alignement
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
		grid.setMatrix(matrix);
	}

	// supprimer les alignements
	public boolean removeAlignments() {
		matrix = grid.getMatrix();
		boolean modified = false;
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < grid.getLength(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
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
		Marble marble2;
		for (int i = 0; i < grid.getLength(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				if (marked[i][j]) {
					marble2 = new Marble(i, j, Color.WHITE);
					matrix[i][j] = marble2;
					marked[i][j] = false;
					modified = true;
				}
			}
		}
		grid.setMatrix(matrix);
		return modified;
	}

	public synchronized Grid getGrid() {
		return grid;
	}

	/**
	 * @return the matrix
	 */
	public Candy[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix the matrix to set
	 */
	public void setMatrix(Candy[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the candyList
	 */
	public List<Candy> getCandyList() {
		return candyList;
	}

	/**
	 * @param candyList the candyList to set
	 */
	public void setCandyList(List<Candy> candyList) {
		this.candyList = candyList;
	}

	/**
	 * @return the marked
	 */
	public boolean[][] getMarked() {
		return marked;
	}

	/**
	 * @param marked the marked to set
	 */
	public void setMarked(boolean[][] marked) {
		this.marked = marked;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
