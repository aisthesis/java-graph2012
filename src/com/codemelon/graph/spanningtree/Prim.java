package com.codemelon.graph.spanningtree;


import java.util.PriorityQueue;
import java.util.Set;

import com.codemelon.graph.OldGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.VertexConstants;
import com.codemelon.graph.vertex.comparators.WeightComparator;

/**
 * Implementation of Prim's algorithm for growing a minimum spanning
 * tree. Follows
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 634ff.
 * @author Marshall Farrier
 * @version Dec 4, 2012
 */
public class Prim {
	/**
	 * Color to which edges belonging to minimum spanning tree will be set
	 * after running markEdges()
	 */
	public static final Color MARKER_COLOR = Color.BLACK;
	
	private OldGraph graph;
	private PriorityQueue<CompleteVertex> queue;
	
	public Prim(OldGraph graph) {
		this.graph = graph;
		queue = null;
	}
	/**
	 * Set edges in minimum spanning tree to MARKER_COLOR, all other edges are 
	 * set to EdgeConstants.DEFAULT_COLOR. Parents of all vertices in the graph are also modified
	 * to show a path leading back to the specified root.
	 */
	public void markEdges(CompleteVertex root) {
		initializeForMarking(root);
		CompleteVertex u;
		Set<CompleteVertex> adj;
		while (!queue.isEmpty()) {
			u = queue.poll();
			u.color = MARKER_COLOR;
			adj = u.getAdjacencies();
			for (CompleteVertex v : adj) {
				// initial color means that v is still in the queue
				if (v.color == VertexConstants.INITIAL_COLOR && 
						graph.getEdgeWeight(u, v) < v.weight) {
					v.parent = u;
					queue.remove(v);
					v.weight = graph.getEdgeWeight(u, v);
					queue.add(v);
				}
			}
		}
	}
	
	private void initializeForMarking(CompleteVertex root) {
		new VertexResetter(graph).primReset();
		root.weight = 0.0;
		queue = new PriorityQueue<CompleteVertex>(graph.vertexCount(), new WeightComparator());
		Set<CompleteVertex> vertices = graph.getVertices();
		for (CompleteVertex vertex : vertices) {
			queue.add(vertex);
		}
	}
}
