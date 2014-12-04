package controler;

import algorithms.Algorithms;

public class GameControler {
	
	private Algorithms algorithm;

	public GameControler() {}
	
	public GameControler(Algorithms algo) {
		this.algorithm = algo;
		algorithm.fill();
	}
	
	public Algorithms getAlgorithm() {
		return algorithm;
	}
}
