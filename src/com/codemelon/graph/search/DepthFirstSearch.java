/**
 * 
 */
package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;
/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 * cf. CLRS, pp. 604ff.
 */
public class DepthFirstSearch {
	private DiGraph graph;
	private int t;	// time in CLRS
	private boolean isAcyclic;
	private int treeNumber;
	
	public DepthFirstSearch(DiGraph graph) {
		this.graph = graph;
		isAcyclic = true;
	}
	
	public boolean search() {
		new VertexResetter(graph).dfsReset();
		t = 0;
		treeNumber = 0;
		Iterator<Vertex> it = graph.vertexIterator();
		Vertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.color == Color.WHITE) {
				treeNumber++;
				visit(u);
			}
		}
		return isAcyclic;
	}
	private void visit(Vertex u) {
		u.discoveryTime = ++t;
		u.color = Color.GRAY;
		u.treeNumber = treeNumber;
		Set<Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			switch (v.color) {
			case WHITE:
				v.parent = u;
				u.setEdgeType(v, EdgeType.TREE);
				visit(v);
				break;
			case GRAY:
				u.setEdgeType(v, EdgeType.BACK);
				isAcyclic = false;
				break;
			case BLACK:
				if (u.discoveryTime < v.discoveryTime) { u.setEdgeType(v, EdgeType.FORWARD); }
				else { u.setEdgeType(v, EdgeType.CROSS); }
			}		
		}
		u.color = Color.BLACK;
		u.finishTime = ++t;
	}
}