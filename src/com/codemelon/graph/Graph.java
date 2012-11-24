/**
 * 
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
	@Override
	public boolean insertEdge(Vertex from, Vertex to) {
		if (!insertEdge(from, to)) { return false; }
		return insertEdge(to, from);
	}
	@Override
	public boolean removeEdge(Vertex from, Vertex to) {
		if (!removeEdge(from, to)) { return false; }
		return removeEdge(to, from);
	}
	@Override
	public long edges() { return super.edges() / 2L; }
}
