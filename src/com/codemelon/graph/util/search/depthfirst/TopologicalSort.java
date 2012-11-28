/**
 * 
 */
package com.codemelon.graph.util.search.depthfirst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.core.VertexResetter;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSort {
	private DiGraph graph;
	
	public TopologicalSort(DiGraph graph) {
		this.graph = graph;
	}

	/**
	 * CLRS, p. 613
	 * @return a list of topologically sorted vertices
	 */
	public LinkedList<Vertex> topologicalSort() {
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		new VertexResetter(graph).resetColors();
		Iterator<Vertex> it = graph.vertexIterator();
		Vertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.color == Color.WHITE) {
				topologicalSortVisit(u, result);
			}
		}
		return result;
	}
	
	private void topologicalSortVisit(Vertex u, LinkedList<Vertex> result) {
		u.color = Color.GRAY;
		Set<Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				topologicalSortVisit(v, result);
			}		
		}
		u.color = Color.BLACK;
		result.addFirst(u);	
	}
}
