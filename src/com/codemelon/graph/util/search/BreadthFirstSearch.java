/**
 * 
 */
package com.codemelon.graph.util.search;

import java.util.LinkedList;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.core.VertexResetter;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class BreadthFirstSearch {
	private DiGraph graph;
	
	public BreadthFirstSearch(DiGraph graph) {
		this.graph = graph;
	}
	
	public void search(Vertex s) {
		if (!graph.containsVertex(s)) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		new VertexResetter(graph).bfsReset();
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		s.color = Color.GRAY;
		s.distance = 0;
		queue.add(s);
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
}
