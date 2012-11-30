/**
 * Used in StronglyConnectedComponents
 */
package com.codemelon.graph.vertex;

import java.util.Comparator;

/**
 * @author Marshall Farrier
 * @version Nov 30, 2012
 *
 */
public class ReverseSearchOrderComparator implements Comparator<Vertex> {	
	public ReverseSearchOrderComparator() {}
	
	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v2.searchOrder - v1.searchOrder;
	}

}
