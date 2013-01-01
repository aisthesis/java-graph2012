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
public class SearchOrderComparator implements Comparator<Vertex> {
	
	public SearchOrderComparator() {}
	
	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v1.searchOrder - v2.searchOrder;
	}
}
