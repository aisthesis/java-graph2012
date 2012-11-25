/**
 * Undirected graph with no self edges.
 */
package com.codemelon.graph;

import java.util.Collection;

import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class Graph extends DiGraph{
	public Graph() { super(); }
	public Graph(int initialCapacity) { super(initialCapacity); }
	public Graph(Collection<Vertex> initialVertices) { super(initialVertices); }
	/**
	 * Inserts the given Edge if not already present unless
	 * from and to are the same.
	 * @param from one end of the edge to be inserted
	 * @param to the other end of the edge to be inserted
	 * @return true if the edge can be inserted
	 */
	@Override
	public boolean addEdge(Vertex from, Vertex to) {
		if (from == to) { return false; }
		if (!super.addEdge(from, to)) { return false; }
		return super.addEdge(to, from);
	}
	@Override
	public boolean removeEdge(Vertex from, Vertex to) {
		if (!super.removeEdge(from, to)) { return false; }
		return super.removeEdge(to, from);
	}
	@Override
	public long edges() { return super.edges() / 2L; }
}
