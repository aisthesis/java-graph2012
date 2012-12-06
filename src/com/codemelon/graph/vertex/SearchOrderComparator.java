/**
 * 
 */
package com.codemelon.graph.vertex;

import java.util.Comparator;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class SearchOrderComparator implements Comparator<CompleteVertex> {
	
	public SearchOrderComparator() {}
	
	@Override
	public int compare(CompleteVertex v1, CompleteVertex v2) {
		return v1.searchOrder - v2.searchOrder;
	}
}
