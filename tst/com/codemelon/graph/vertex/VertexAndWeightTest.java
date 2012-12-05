package com.codemelon.graph.vertex;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class VertexAndWeightTest {
	private static final double FIRST_WEIGHT = 1000.0;
	private static final int TEST_ARRAY_SIZE = 20;

	@Test
	public void testCompareTo() {
		ArrayList<VertexAndWeight> weightedVertices = new ArrayList<VertexAndWeight>(TEST_ARRAY_SIZE);
		// elements are in descending order of weight
		for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
			weightedVertices.add(new VertexAndWeight(new Vertex(), FIRST_WEIGHT - i * 10.0));
		}
		Collections.sort(weightedVertices);
		for (int i = 1; i < TEST_ARRAY_SIZE; i++) {
			assertTrue("Items in correct order", weightedVertices.get(i - 1).getWeight() 
					<= weightedVertices.get(i).getWeight());
		}
	}

}
