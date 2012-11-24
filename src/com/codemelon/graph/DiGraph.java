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
	private long edges;
	
	public DiGraph() {
		vertices = new ConcurrentHashMap<Integer, Vertex>();
		edges = 0L;
	}
	public DiGraph(int initialVertices) {
		vertices = new ConcurrentHashMap<Integer, Vertex>(initialVertices);
		for (int i = 0; i < initialVertices; i++) {
			vertices.put(i, new Vertex());
		}
	}
	/**
	 * Inserts v if there is no vertex associated with the given label.
	 * If there is already a vertex with this label, the method
	 * does not insert the new vertex but returns the vertex to which
	 * the label maps prior to the call.
	 * @param label
	 * @param v
	 * @return
	 */
	public Vertex insert(int label, Vertex v) {
		return vertices.putIfAbsent(label, v);
	}
	/**
	 * Inserts edge if not already present. Returns false if the edge
	 * was already present in the graph.
	 * @param from label of the source vertex
	 * @param to label of the target vertex
	 * @return true if the edge was not previously present in the graph
	 */
	public boolean insertEdge(int from, int to) {
		if (vertices.get(from).addAdjacency(vertices.get(to))) {
			edges++;
			return true;
		}
		return false;
	}
	/**
	 * Removes the specified edge if it is present. If the edge
	 * is not found, returns false and does nothing.
	 * @param from label of the edge's source vertex
	 * @param to label of the edge's target vertex
	 * @return true if the edge was found
	 */
	public boolean removeEdge(int from, int to) {
		if (vertices.get(from).removeAdjacency(vertices.get(to))) {
			edges--;
			return true;
		}
		return false;
	}
	/**
	 * Number of edges in the graph
	 * @return number of edges in the graph
	 */
	public long edges() { return edges; }
}
