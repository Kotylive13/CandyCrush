package factory;

import graphics.Candy;
import graphics.Marble;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CandyFactory {

	public CandyFactory() {
		
	}
	
	public List<Candy> createCandy(Class<Marble> cls) throws InstantiationException, IllegalAccessException {
		List<Candy> candyList = new ArrayList<Candy>();
		Color colors[] = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
				Color.GRAY, Color.PINK, Color.CYAN };
		for (int i = 0; i < colors.length; i++) {
			Marble candy = cls.newInstance();
			candy.setColor(colors[i]);
			candyList.add(candy);
		}
		return candyList;
	}
}
