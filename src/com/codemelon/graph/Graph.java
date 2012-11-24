/**
 * 
 */
package com.codemelon.graph;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class Graph extends DiGraph{
	public Graph() { super(); }
	public Graph(int initialVertices) { super(initialVertices); }
	@Override
	public boolean insertEdge(int from, int to) {
		if (!insertEdge(from, to)) { return false; }
		return insertEdge(to, from);
	}
	@Override
	public boolean removeEdge(int from, int to) {
		if (!removeEdge(from, to)) { return false; }
		return removeEdge(to, from);
	}
	@Override
	public long edges() { return super.edges() / 2; }
}
