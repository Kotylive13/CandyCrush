package graphics;

import java.awt.Color;

public class Marble extends Candy{
	private int posX;
	private int posY;
	private Color color;
	
	public Marble() {}
	
	public Marble(int posX, int posY, Color color) {
		super (posX, posY);
		this.posX = posX;
		this.posY = posY;
		this.color = color;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
