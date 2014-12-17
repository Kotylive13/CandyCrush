package graphics;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class Picture {

	private BufferedImage image;
	private int posX;
	private int posY;
	private int length;
	private int height;
	
	public Picture() {}
	
	public Picture(BufferedImage image, int posX, int posY, int length, int height){
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.length = length;
		this.height = height;
	}
}
