package com.codemelon.graph.spanningtree;

import java.util.Arrays;
import java.util.Comparator;

import com.codemelon.graph.Graph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.edge.WeightedEdge;
import com.codemelon.graph.util.DisjointSet;
import com.codemelon.graph.vertex.Vertex;

/**
 * Implementation of Kruskal's algorithm for growing a minimum spanning
 * tree. Follows
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 631ff.
 * @author Marshall Farrier
 * @version Dec 1, 2012
 *
 */
public class Kruskal {
	private Graph graph;
	private WeightedEdge[] edges;
	private DisjointSet<Vertex> vertexDisjointSet;
	
	private static final WeightedEdge[] EMPTY_EDGE_ARRAY = new WeightedEdge[0];
	private static final Comparator<WeightedEdge> COMPARATOR = new Comparator<WeightedEdge>() {
		@Override
		public int compare(WeightedEdge e1, WeightedEdge e2) {
			if (e1.weight() < e2.weight()) { return -1; }
			if (e2.weight() < e1.weight()) { return 1; }
			return 0;
		}		
	};
	
	public Kruskal(Graph graph) {
		this.graph = graph;
		edges = graph.getWeightedEdges().toArray(EMPTY_EDGE_ARRAY);
		Arrays.sort(edges, COMPARATOR);
		vertexDisjointSet = new DisjointSet<Vertex>(graph.getVertices());
	}
	/**
	 * Set edges in minimum spanning tree to BLACK, all other edges are WHITE
	 */
	public void markEdges() {
		//TODO reset edge color to white
		for (WeightedEdge edge : edges) {
			if (vertexDisjointSet.findSet(edge.from()) 
					!= vertexDisjointSet.findSet(edge.to())) {
				graph.setEdgeColor(edge.from(), edge.to(), Color.BLACK);
				vertexDisjointSet.union(edge.from(), edge.to());				
			}
		}		
	}
}