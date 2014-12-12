package controler;

import algorithms.Algorithms;

public class GameControler {
	
	private Algorithms algorithm;
	private boolean fill = false;
	public GameControler() throws InstantiationException, IllegalAccessException {
		algorithm = new Algorithms();
		fill = algorithm.fill();
	}
	
	public Algorithms getAlgorithm() {
		return algorithm;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public void setAlgorithm(Algorithms algorithm) {
		this.algorithm = algorithm;
	}
}
