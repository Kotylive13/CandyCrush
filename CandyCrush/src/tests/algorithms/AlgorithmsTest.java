package tests.algorithms;

import static org.junit.Assert.*;

import java.awt.Color;

import graphics.Candy;
import graphics.Marble;

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
		algo.fill();
		for (int i = 0; i < algo.getGrid().getHeight(); i++) {
			for (int j = 0; j < algo.getGrid().getLength(); j++) {
				assertNotNull(algo.getGrid().getMatrix()[i][j]);
			}
		}
	}

	@Test
	public void testHorizontalAligned() {

		
		Marble[][] matrix = new Marble[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = new Marble(i, j, Color.BLACK);
			}
		}
		algo.getGrid().setMatrix(matrix);
		assertTrue(algo.horizontalAligned(1, 3));

	}

	@Test
	public void testVerticalAligned() {
		Marble[][] matrix = new Marble[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = new Marble(i, j, Color.BLACK);
			}
		}
		algo.getGrid().setMatrix(matrix);
		assertTrue(algo.verticalAligned(1, 3));
	}


	@Test
	public void testIsValidSwap() {
		Marble[][] matrix = new Marble[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = new Marble(i, j, Color.BLACK);
			}
		}
		algo.getGrid().setMatrix(matrix);
		assertTrue(algo.isValidSwap(1, 2, 2, 2));
	}

	@Test
	public void testRemoveAlignments() {
		Marble[][] matrix = new Marble[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = new Marble(i, j, Color.BLACK);
			}
		}
		algo.getGrid().setMatrix(matrix);
		algo.removeAlignments();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				assertEquals(((Marble)algo.getGrid().getMatrix()[i][j]).getColor(), Color.WHITE);
			}
		}
	}

}
