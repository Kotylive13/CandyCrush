package graphics;

import graphics.power.IPower;

import java.awt.Color;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class MarbleWithPowerPicture extends Marble{

	private int posX;
	private int posY;
	private Color color;
	private IPower iPower;
	private Picture picture;
	
	public MarbleWithPowerPicture() {}
	
	public MarbleWithPowerPicture(int posX, int posY, Color color, IPower iPower) {
		super(posX, posY, color);
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.iPower = iPower;
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

	/**
	 * @return the power
	 */
	public IPower getPower() {
		return iPower;
	}

	/**
	 * @param iPower the power to set
	 */
	public void setPower(IPower iPower) {
		this.iPower = iPower;
	}
}
