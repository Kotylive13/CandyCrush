package candy;

import java.awt.Color;

public class MarblePower extends Marble{

	private int posX;
	private int posY;
	private Color color;
	private Power power;
	
	public MarblePower() {}
	
	public MarblePower(int posX, int posY, Color color, Power power) {
		super(posX, posY, color);
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.power = power;
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
	public Power getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(Power power) {
		this.power = power;
	}
	
	
}
