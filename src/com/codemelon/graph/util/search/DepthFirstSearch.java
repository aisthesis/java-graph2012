/**
 * 
 */
package com.codemelon.graph.util.search;

import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.core.VertexResetter;
import com.codemelon.graph.vertex.Vertex;
/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 * cf. CLRS, pp. 604ff.
 */
public class DepthFirstSearch {
	private DiGraph graph;
	private int t;	// time in CLRS
	
	public DepthFirstSearch(DiGraph graph) {
		this.graph = graph;
		t = 0;
	}
	
	public void search() {
		new VertexResetter(graph).dfsReset();
		t = 0;
		Iterator<Vertex> it = graph.vertexIterator();
		Vertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.color == Color.WHITE) {
				visit(u);
			}
		}
	}
	private void visit(Vertex u) {
		t++;
		u.discoveryTime = t;
		u.color = Color.GRAY;
		Set<Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				v.parent = u;
				visit(v);
			}		
		}
		u.color = Color.BLACK;
		t++;
		u.finishTime = t;
	}
}
