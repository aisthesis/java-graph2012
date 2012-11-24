/**
 * Vertex class containing a map of other vertices to edge data.
 * 
 */
package com.codemelon.graph.vertex;
import java.util.concurrent.ConcurrentHashMap;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeData;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class Vertex {
	private ConcurrentHashMap<Vertex, EdgeData> adjacencies;
	public Color color;
	public Vertex parent;
	public int distance;	// for BFS, etc.
	// for DFS
	public int discoveryTime;
	public int finishTime;
	public int treeNumber;	// to identify components after DFS
	
	public Vertex() {
		this(Color.WHITE);
	}
	
	public Vertex(Color color) {
		adjacencies = new ConcurrentHashMap<Vertex, EdgeData>();
		this.color = color;
		parent = null;
		distance = -1;
		discoveryTime = -1;
		finishTime = -1;
		treeNumber = -1;
	}
	
	/**
	 * Adds the specified vertex to the set of adjacencies
	 * @param v vertex to be added
	 * @return true if the adjacency list did not already contain v
	 */
	public boolean addAdjacency(Vertex v) {
		if (adjacencies.containsKey(v)) { return false; }
		adjacencies.put(v, new EdgeData());
		return true;
	}
	
	/**
	 * Removes the specified vertex from the adjacency set
	 * @param v vertex to be removed
	 * @return true if the vertex was found in the adjacency set
	 */
	public boolean removeAdjacency(Vertex v) {
		if (!adjacencies.containsKey(v)) { return false; }
		adjacencies.remove(v);
		return true;
	}
	
	/**
	 * Removes all vertices from the adjacency set. The adjacency set will be
	 * empty after this call returns.
	 */
	public void clearAdjacencies() {
		adjacencies.clear();
	}
	 
	/**
	 * Returns true if the adjacency set contains the specified vertex
	 * @param v vertex whose presence is to be tested
	 * @return true if v is found in the adjacency set
	 */
	public boolean containsAdjacency(Vertex v) {
		return adjacencies.containsKey(v);
	}
	
	/**
	 * Returns the number of vertices in the adjacency set
	 * @return the number of vertices in this adjacency set (its cardinality)
	 */
	public int adjacencies() {
		return adjacencies.size();
	}

	/**
	 * Returns true if the adjacency is non-empty.
	 * @return true if the adjacency is non-empty.
	 */
	public boolean hasAdjacencies() {
		return !adjacencies.isEmpty();
	}
}
