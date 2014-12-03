package graphics;

/**
 * 
 * @author Philippe & Koty
 *
 */
public class Grid {

	private Candy [][] matrix;
	private int length;
	private int height;
	
	public Grid() {}
	
	public Grid(int length, int height) {
		this.length = length;
		this.height = height;
		matrix = new Marble [length][height];
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
	
	
}
