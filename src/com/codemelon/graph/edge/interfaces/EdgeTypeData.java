package com.codemelon.graph.edge.interfaces;

import com.codemelon.graph.common.EdgeType;

/**
 * Requires that an EdgeData object maintain an EdgeType (TREE, BACK, FORWARD, CROSS, UNKNOWN)
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface EdgeTypeData {
	/**
	 * Set the EdgeType contained in this EdgeData object
	 * @param edgeType value to which to set the edge type
	 */
	public void setEdgeType(EdgeType edgeType);
	/**
	 * Retrieve the type of this EdgeData object
	 * @return the edge type stored in this EdgeData object
	 */
	public EdgeType getEdgeType();
}
