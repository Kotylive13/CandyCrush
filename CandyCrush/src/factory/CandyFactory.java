package factory;

import graphics.Candy;
import graphics.Marble;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class CandyFactory {

	public CandyFactory() {
		
	}
	
	public List<Candy> createCandy(Class<Marble> cls){
		List<Candy> candyList = new ArrayList<Candy>();
		Color colors[] = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
				Color.GRAY, Color.PINK, Color.CYAN };
		for (int i = 0; i < colors.length; i++) {
			Marble candy = null;
			try {
				candy = cls.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			candy.setColor(colors[i]);
			candyList.add(candy);
		}
		return candyList;
	}
}
