/**
 * 
 */
package com.codemelon.graph.util.search;

import java.util.Enumeration;
import java.util.LinkedList;

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
	
	public void breadthFirstSearch(Vertex v) {
		if (!graph.containsVertex(v)) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		new VertexResetter(graph).bfsReset();
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		v.color = Color.GRAY;
		v.distance = 0;
		queue.add(v);
		Vertex t, u;
		Enumeration<Vertex> adjacencyEnumeration;
		while (!queue.isEmpty()) {
			u = queue.removeFirst();
			adjacencyEnumeration = u.getAdjacencies();
			while (adjacencyEnumeration.hasMoreElements()) {
				t = adjacencyEnumeration.nextElement();
				if (t.color == Color.WHITE) {
					t.color = Color.GRAY;
					t.distance = u.distance + 1;
					t.parent = u;
					queue.addLast(t);
				}
			}
			u.color = Color.BLACK;
		}
	}
}
