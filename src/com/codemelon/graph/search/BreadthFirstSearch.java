/**
 * 
 */
package com.codemelon.graph.search;

import java.util.LinkedList;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class BreadthFirstSearch {
	private DiGraph graph;
	private Vertex source;
	
	/**
	 * Prepares the search on the given graph
	 * @param graph graph that will be searched
	 */
	public BreadthFirstSearch(DiGraph graph) {
		this.graph = graph;
		source = null;
	}
	
	/**
	 * Executes the search on the input graph. Sets the parent of the source
	 * vertex to null and sets parents for all vertices reachable from source
	 * according to a shortest path from source to the given vertex. Unreachable
	 * vertices will have parent set to null after this method call. Reachable
	 * vertices will further have distances entered according to the minimum number of 
	 * steps (edges) required to reach them from source. The source vertex will
	 * show a distance of 0, and unreachable vertices will have distances set to -1
	 * @param source source vertex from which shortest paths are to be determined
	 */
	public void search(Vertex source) {
		if (!graph.containsVertex(source)) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		this.source = source;
		new VertexResetter(graph).bfsReset();
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		source.color = Color.GRAY;
		source.distance = 0;
		queue.add(source);
		Vertex u;
		Set<Vertex> adjacentVertices;
		while (!queue.isEmpty()) {
			u = queue.removeFirst();
			adjacentVertices = u.getAdjacencies();
			for (Vertex v : adjacentVertices) {
				if (v.color == Color.WHITE) {
					v.color = Color.GRAY;
					v.distance = u.distance + 1;
					v.parent = u;
					queue.addLast(v);
				}
			}
			u.color = Color.BLACK;
		}
	}
	/**
	 * Returns the source vertex specified when search() was called.
	 * @return the source vertex specified when search() was called
	 */
	public Vertex getSourceVertex() { return source; }
	
	/**
	 * Shows a shortest path from the source vertex passed in the search() method
	 * to any given vertex in the graph.
	 * For actually getting the list rather than just printing, a loop seems easier
	 * than the recursion used by CLRS (p. 601)
	 * @param target the vertex to which the path is returned
	 * @return the shortest path from the source vertex passed in a prior call of search()
	 * to the target vertex
	 * @throws IllegalStateException if search() has not yet been called
	 */
	public LinkedList<Vertex> path(Vertex target) {
		if (source == null) {
			throw new IllegalStateException("Source vertex has not been specified by calling search()!");
		}
		if (target.parent == null && target != source) { return null; }
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		Vertex tmp = target;
		while (tmp != source) {
			result.addFirst(tmp);
			tmp = tmp.parent;
		}
		result.addFirst(source);
		return result;
	}
}
