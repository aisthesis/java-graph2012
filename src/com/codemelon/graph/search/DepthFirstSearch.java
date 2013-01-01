package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.types.DfsVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
/**
 * Implementation of depth-first search following 
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 603ff.
 * @author Marshall Farrier
 * @version Nov 26, 2012
 * cf. CLRS, pp. 604ff.
 */
public class DepthFirstSearch {
	/**
	 * The discovery time shown for the vertex first visited in the search
	 */
	public static final int FIRST_DISCOVERY_TIME = 0;
	private DiGraph<? extends DfsVertex> graph;
	private int t;	// time in CLRS
	private boolean isAcyclic;
	
	/**
	 * Prepare the depth-first search.
	 * No changes are made to the graph when it is passed into the constructor.
	 * @param graph graph on which the search will be run
	 */
	public DepthFirstSearch(DiGraph<? extends DfsVertex> graph) {
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
	 * to the order in which they were first discovered and finished. The first
	 * discovery time will be 0.</li>
	 * <li>Vertices will have parent fields set as determined by the search.</li>
	 * <li>Edges will be classified as TREE, BACK, FORWARD or CROSS</li>
	 * </ol>
	 * The search() method returns a boolean value which is true iff the graph
	 * is acyclic.
	 * @return true iff the graph is acyclic.
	 */
	public boolean search() {
		VertexResetter.resetForDfs(graph);
		t = FIRST_DISCOVERY_TIME - 1;	// so that first discovery time will be 0
		Iterator<? extends DfsVertex> it = graph.vertexIterator();
		DfsVertex u;
		while (it.hasNext()) {
			u = it.next();
			if (u.getColor() == Color.WHITE) {
				visit(u);
			}
		}
		return isAcyclic;
	}
	private void visit(DfsVertex u) {
		u.setDiscoveryTime(++t);
		u.setColor(Color.GRAY);
		Set<Vertex> adjacentVertices = u.getAdjacencies();
		for (Vertex v : adjacentVertices) {
			switch(((DfsVertex) v).getColor()) {
			case WHITE:
				((DfsVertex) v).setParent(u);
				u.setEdgeType(((DfsVertex) v), EdgeType.TREE);
				visit(((DfsVertex) v));
				break;
			case GRAY:
				u.setEdgeType(((DfsVertex) v), EdgeType.BACK);
				isAcyclic = false;
				break;
			case BLACK:
				if (u.getDiscoveryTime() < ((DfsVertex) v).getDiscoveryTime()) {
					u.setEdgeType((DfsVertex) v, EdgeType.FORWARD);
				}
				else {
					u.setEdgeType((DfsVertex) v, EdgeType.CROSS);					
				}
			}
		}
		u.setColor(Color.BLACK);
		u.setFinishTime(++t);
	}
}