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
	
	public DepthFirstSearch(DiGraph graph) {
		this.graph = graph;
		isAcyclic = true;
	}
	/**
	 * Conduct a depth-first search on the graph, visiting vertices
	 * in no predetermined order.
	 * This method modifies the graph in the following ways:
	 * <ol>
	 * <li>All vertices will be colored black after this method call.</li>
	 * <li>All vertices will have discoveryTime and finishTime set according
	 * to the order in which they were first discovered and finished.</li>
	 * <li>Vertices will have parent fields set as determined by the search.</li>
	 * <li>Edges will be classified as TREE, BACK, FORWARD or CROSS</li>
	 * </ol>
	 * The search() method returns a boolean value which is true iff the graph
	 * is acyclic.
	 * @return true iff the graph is acyclic.
	 */
	public boolean search() {
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
		return isAcyclic;
	}
	private void visit(Vertex u) {
		u.discoveryTime = ++t;
		u.color = Color.GRAY;
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