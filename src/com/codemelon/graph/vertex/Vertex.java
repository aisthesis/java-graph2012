/**
 * 
 */
package com.codemelon.graph.vertex;

import java.util.Collection;

/**
 * @author Marshall Farrier
 * @version 11/24/2012
 */
public interface Vertex {
	/**
	 * Adds the specified vertex to the set of adjacencies
	 * @param v vertex to be added
	 * @return true if the adjacency list did not already contain v
	 */
	public boolean addAdjacency(Vertex v);
	/**
	 * Adds all of the vertices in the specified Collection to the adjacency set
	 * of the calling vertex.
	 * @param c Collection containing vertices to be added to the adjacency set
	 * @return true if the adjacency list changed as a result of the operation
	 */
	public boolean addAllAdjacencies(Collection<Vertex> c);
	/**
	 * Removes the specified vertex from the adjacency set
	 * @param v vertex to be removed
	 * @return true if the vertex was found in the adjacency set
	 */
	public boolean removeAdjacency(Vertex v);
	/**
	 * Removes all vertices from the adjacency set. The adjacency set will be
	 * empty after this call returns.
	 */
	public void clearAdjacencies(); 
	/**
	 * Returns true if the adjacency set contains the specified vertex
	 * @param v vertex whose presence is to be tested
	 * @return true if v is found in the adjacency set
	 */
	public boolean containsAdjacency(Vertex v);
	/**
	 * Returns the number of vertices in the adjacency set
	 * @return the number of vertices in this adjacency set (its cardinality)
	 */
	public int adjacencies();
	/**
	 * Returns true if the adjacency is non-empty.
	 * @return true if the adjacency is non-empty.
	 */
	public boolean hasAdjacencies();
}
