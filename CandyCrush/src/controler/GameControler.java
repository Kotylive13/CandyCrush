package controler;

import algorithms.Algorithms;

public class GameControler {
	
	private Algorithms algorithm;

	public GameControler() throws InstantiationException, IllegalAccessException {
		algorithm = new Algorithms();
		algorithm.fill();
	}
	
	public Algorithms getAlgorithm() {
		return algorithm;
	}
}
