package controler;

import algorithms.Algorithms;

public class GameControler {
	
	private Algorithms algorithm;
	public GameControler() throws InstantiationException, IllegalAccessException {
		algorithm = new Algorithms();
		algorithm.fill();
		algorithm.removeAlignments();
	}
	
	public Algorithms getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(Algorithms algorithm) {
		this.algorithm = algorithm;
	}
}
