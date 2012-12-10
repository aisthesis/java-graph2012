package com.codemelon.graph.vertex.comparators;

import java.util.Comparator;

import com.codemelon.graph.vertex.interfaces.OrderedSearchVertex;

/**
 * Used in StronglyConnectedComponents
 * @author Marshall Farrier
 * @version Nov 30, 2012
 *
 */
public class ReverseSearchOrderComparator implements Comparator<OrderedSearchVertex> {	
	public ReverseSearchOrderComparator() {}
	
	@Override
	public int compare(OrderedSearchVertex v1, OrderedSearchVertex v2) {
		return v2.getSearchOrder() - v1.getSearchOrder();
	}

}
