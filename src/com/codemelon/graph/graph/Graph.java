package com.codemelon.graph.graph;

import java.util.Collection;

import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * General Graph class in which edges are undirected.
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public class Graph<T extends Vertex> extends DiGraph<T> {
	/**
	 * Initialize graph to a default initial capacity of 16 vertices
	 */
	public Graph() { super(); }
	/**
	 * Initialize graph to have capacity for the given number of vertices
	 * @param initialCapacity expected number of vertices
	 */
	public Graph(int initialCapacity) { super(initialCapacity); }
	/**
	 * Use the vertices in a collection as the initial vertices in the graph
	 * @param vertices collection of vertices that will make up the initial vertices
	 * of the graph
	 */
	public Graph(Collection<T> vertices) { super(vertices); }
	/**
	 * After this call both the edge (from, to) and the edge
	 * (to, from) are included in the graph
	 * @return true iff the edge was not found in the graph
	 * @throws IllegalArgumentException if either vertex is not present in the graph
	 */
	@Override
	public boolean addEdge(T from, T to) {
		if (from == to) { return false; }
		if (!super.addEdge(from, to)) { return false; }
		return super.addEdge(to, from);
	}
	/* (non-Javadoc)
	 * @see com.codemelon.graph.graph.DiGraph#removeEdge(com.codemelon.graph.vertex.interfaces.Vertex, 
	 * com.codemelon.graph.vertex.interfaces.Vertex)
	 */
	@Override
	public boolean removeEdge(T from, T to) {
		if (!super.removeEdge(from, to)) { return false; }
		return super.removeEdge(to, from);
	}
	@Override
	public int edgeCount() { return super.edgeCount() / 2; }
}
