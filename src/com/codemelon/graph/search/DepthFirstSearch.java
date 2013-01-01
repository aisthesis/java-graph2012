package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.interfaces.EdgeColorData;
import com.codemelon.graph.edge.interfaces.EdgeDataFactory;
import com.codemelon.graph.edge.interfaces.EdgeTypeData;
import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.types.DfsVertex;
import com.codemelon.graph.vertex.util.VertexResetter;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.EdgeTypeVertex;
import com.codemelon.graph.vertex.interfaces.VisitedVertex;

/**
 * Implementation of depth-first search following 
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 603ff.
 * @author Marshall Farrier
 * @version Nov 26, 2012
 * cf. CLRS, pp. 604ff.
 */
public class DepthFirstSearch<T extends EdgeTypeData & EdgeColorData, U extends EdgeDataFactory<T>> {
	/**
	 * The discovery time shown for the vertex first visited in the search
	 */
	public static final int FIRST_DISCOVERY_TIME = 0;
	private DiGraph<? extends DfsVertex<T, U>> graph;
	private int t;	// time in CLRS
	private boolean isAcyclic;
	
	/**
	 * Prepare the depth-first search.
	 * No changes are made to the graph when it is passed into the constructor.
	 * @param graph graph on which the search will be run
	 */
	public DepthFirstSearch(DiGraph<? extends DfsVertex<T, U>> graph) {
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
		Iterator<? extends DfsVertex<T, U>> it = graph.vertexIterator();
		DfsVertex<T, U> u;
		while (it.hasNext()) {
			u = it.next();
			if (u.getColor() == Color.WHITE) {
				visit(u);
			}
		}
		return isAcyclic;
	}
	private void visit(DfsVertex<T, U> u) {
		u.setDiscoveryTime(++t);
		u.setColor(Color.GRAY);
		Set<? extends Vertex> adjacencies = u.getAdjacencies();
		for (Vertex v : adjacencies) {
			switch(((ColoredVertex) v).getColor()) {
			case WHITE:
				((ChildVertex) v).setParent(u);
				u.setEdgeType((EdgeTypeVertex) v, EdgeType.TREE);
				visit(((DfsVertex<T, U>) v));
				break;
			case GRAY:
				u.setEdgeType(((EdgeTypeVertex) v), EdgeType.BACK);
				isAcyclic = false;
				break;
			case BLACK:
				if (u.getDiscoveryTime() < ((VisitedVertex) v).getDiscoveryTime()) {
					u.setEdgeType((EdgeTypeVertex) v, EdgeType.FORWARD);
				}
				else {
					u.setEdgeType((EdgeTypeVertex) v, EdgeType.CROSS);					
				}
			}
		}
		u.setColor(Color.BLACK);
		u.setFinishTime(++t);
	}
}