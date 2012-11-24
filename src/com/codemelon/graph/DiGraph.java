/**
 * Graph class supporting insertion and removal of both vertices and
 * edges.
 * Integers are used as vertex identifiers.
 * The graph is implemented as a map of integer labels to vertices,
 * which themselves contain lists of their adjacencies.
 */
package com.codemelon.graph;

import java.util.concurrent.ConcurrentHashMap;

import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class DiGraph {
	private ConcurrentHashMap<Integer, Vertex> vertices;
	public DiGraph() {
		vertices = new ConcurrentHashMap<Integer, Vertex>();
	}
	public DiGraph(int initialVertices) {
		vertices = new ConcurrentHashMap<Integer, Vertex>(initialVertices);
	}
	/**
	 * Inserts v if there is no vertex associated with the given label.
	 * If there is already a vertex with this label, the method
	 * does not insert the new vertex but returns the vertex to which
	 * the label maps.
	 * @param label
	 * @param v
	 * @return
	 */
	public Vertex insert(int label, Vertex v) {
		return vertices.putIfAbsent(label, v);
	}
	public boolean insertEdge(int from, int to) {
		return vertices.get(from).addAdjacency(vertices.get(to));
	}
	public boolean removeEdge(int from, int to) {
		return vertices.get(from).removeAdjacency(vertices.get(to));
	}
}
