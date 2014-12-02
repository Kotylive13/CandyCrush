package candy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CandyFactory {

	public List<Candy> createCandy(Class<Marble> cls) throws InstantiationException, IllegalAccessException {
		List<Candy> candyList = new ArrayList<Candy>();
		Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE,
				Color.GRAY, Color.PINK, Color.CYAN };
		for (int i = 0; i < colors.length; i++) {
			Marble candy = cls.newInstance();
			candy.setColor(colors[i]);
			candyList.add(candy);
		}
		return candyList;
	}
}
