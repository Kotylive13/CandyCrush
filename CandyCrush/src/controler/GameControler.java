package controler;

import algorithms.Algorithms;

public class GameControler {

	private Algorithms algorithm;

	public GameControler() throws InstantiationException,
			IllegalAccessException {
		algorithm = new Algorithms();
		algorithm.fill();
		while (algorithm.removeAlignments())
			while (algorithm.fillAfterDestroyMarbles()) {}
	}

	public Algorithms getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithms algorithm) {
		this.algorithm = algorithm;
	}
}
