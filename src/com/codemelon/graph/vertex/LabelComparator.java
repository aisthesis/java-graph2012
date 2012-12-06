 package com.codemelon.graph.vertex;

import java.util.Comparator;

/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 *
 */
public class LabelComparator implements Comparator<Vertex> {
	
	public LabelComparator() {}
	
	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v1.label - v2.label;
	}
}
