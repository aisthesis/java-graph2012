 package com.codemelon.graph.vertex.comparators;

import java.util.Comparator;

import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 *
 */
public class LabelComparator implements Comparator<CompleteVertex> {
	
	public LabelComparator() {}
	
	@Override
	public int compare(CompleteVertex v1, CompleteVertex v2) {
		return v1.label - v2.label;
	}
}
