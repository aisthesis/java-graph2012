package com.codemelon.graph.vertex.comparators;

import java.util.Comparator;

import com.codemelon.graph.vertex.interfaces.OrderedSearchVertex;

/**
 * Orders vertices according to the value returned by the getSearchOrder() method
 * @author Marshall Farrier
 * @version Nov 27, 2012
 */
public class SearchOrderComparator<T extends OrderedSearchVertex> implements Comparator<T> {
	
	public SearchOrderComparator() {}
	
	@Override
	public int compare(T v1, T v2) {
		return v1.getSearchOrder() - v2.getSearchOrder();
	}
}
