package tests.algorithms;

import static org.junit.Assert.*;

import javax.annotation.PostConstruct;

import junit.framework.Assert;

import org.junit.Test;

import algorithms.Algorithms;

public class AlgorithmsTest {
	private Algorithms algo;

	public AlgorithmsTest() throws InstantiationException,
			IllegalAccessException {
		algo = new Algorithms();
		assertNotNull(algo);
	}

	@Test
	public void testFill() {
		assertTrue(algo.fillAfterDestroyMarbles());
		for (int i = 0; i < algo.getGrid().getHeight(); i++) {
			for (int j = 0; j < algo.getGrid().getLength(); j++) {
				assertNotNull(algo.getGrid().getMatrix()[i][j]);
			}
		}
	}

	@Test
	public void testHorizontalAligned() {

	}

	@Test
	public void testVerticalAligned() {

	}

	@Test
	public void testGetRandomCandy() {

	}

	@Test
	public void testIsValidSwap() {

	}

	@Test
	public void testSwap() {

	}

	@Test
	public void testRemoveAlignments() {

	}

}
