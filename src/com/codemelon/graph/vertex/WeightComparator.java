package com.codemelon.graph.vertex;

import java.util.Comparator;

/**
 * @author Marshall Farrier
 * @version Dec 5, 2012
 */
public class WeightComparator implements Comparator<CompleteVertex> {
	public WeightComparator() {}

	@Override
	public int compare(CompleteVertex v1, CompleteVertex v2) {
		if (v1.weight < v2.weight) { return -1; }
		if (v1.weight > v2.weight) { return 1; }
		return 0;
	}
}