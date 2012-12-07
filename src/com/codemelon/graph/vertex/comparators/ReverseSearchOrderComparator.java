/**
 * Used in StronglyConnectedComponents
 */
package com.codemelon.graph.vertex.comparators;

import java.util.Comparator;

import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 30, 2012
 *
 */
public class ReverseSearchOrderComparator implements Comparator<CompleteVertex> {	
	public ReverseSearchOrderComparator() {}
	
	@Override
	public int compare(CompleteVertex v1, CompleteVertex v2) {
		return v2.searchOrder - v1.searchOrder;
	}

}
