package com.codemelon.graph.spanningtree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.codemelon.graph.OldGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.edge.WeightedEdge;
import com.codemelon.graph.util.DisjointSet;
import com.codemelon.graph.util.EdgeResetter;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * Implementation of Kruskal's algorithm for growing a minimum spanning
 * tree. Follows
 * <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, pp. 631ff.
 * @author Marshall Farrier
 * @version Dec 1, 2012
 *
 */
public class Kruskal {
	/**
	 * Color to which edges belonging to minimum spanning tree will be set
	 * after running markEdges()
	 */
	public static final Color MARKER_COLOR = Color.BLACK;
	
	private OldGraph graph;
	private WeightedEdge[] edges;
	private DisjointSet<CompleteVertex> vertexDisjointSet;
	private HashMap<CompleteVertex, CompleteVertex> vertexMap;
	private OldGraph spanningTree;
	
	private static final WeightedEdge[] EMPTY_EDGE_ARRAY = new WeightedEdge[0];
	private static final Comparator<WeightedEdge> COMPARATOR = new Comparator<WeightedEdge>() {
		@Override
		public int compare(WeightedEdge e1, WeightedEdge e2) {
			if (e1.weight() < e2.weight()) { return -1; }
			if (e2.weight() < e1.weight()) { return 1; }
			return 0;
		}		
	};
	/**
	 * Initialize the graph for which a minimum spanning tree is to be found.
	 * The constructor does not modify the graph in any way.
	 * @param graph graph for which we want to find a minimum spanning tree.
	 */
	public Kruskal(OldGraph graph) {
		this.graph = graph;
		edges = null;
		vertexDisjointSet = null;
		vertexMap = null;
		spanningTree = null;
	}
	/**
	 * Set edges in minimum spanning tree to MARKER_COLOR, all other edges are 
	 * set to EdgeConstants.DEFAULT_COLOR
	 */
	public void markEdges() {
		initializeForMarking();
		for (WeightedEdge edge : edges) {
			if (vertexDisjointSet.findSet(edge.from()) 
					!= vertexDisjointSet.findSet(edge.to())) {
				graph.setEdgeColor(edge.from(), edge.to(), MARKER_COLOR);
				vertexDisjointSet.union(edge.from(), edge.to());				
			}
		}		
	}
	/**
	 * Creates and returns a minimum spanning tree. Running this method does not modify
	 * the original graph. Corresponding vertices can be determined by running getVertexMap().
	 * The 2 graphs will have the same weight epsilon for
	 * determining the accuracy used for weight equality, and the weights of edges between
	 * corresponding vertices will be the same. With the exception of edge weights and weight epsilon,
	 * however, all vertex and edge data in the spanning tree will be set to the default value,
	 * regardless of the corresponding value in the original graph.
	 * @return a spanning tree of the original graph.
	 */
	public OldGraph generateTree() {
		initializeForGenerateTree();
		for (WeightedEdge edge : edges) {
			if (vertexDisjointSet.findSet(edge.from()) 
					!= vertexDisjointSet.findSet(edge.to())) {
				spanningTree.addEdge(vertexMap.get(edge.from()), vertexMap.get(edge.to()));
				spanningTree.setEdgeWeight(vertexMap.get(edge.from()), vertexMap.get(edge.to()),
						edge.weight());
				vertexDisjointSet.union(edge.from(), edge.to());				
			}
		}		
		return spanningTree;
	}
	/**
	 * Returns a map showing which vertex in the spanning tree corresponds to a given
	 * vertex in the original graph. Edges between corresponding vertices in the 2 graphs
	 * will have the same weight. The keys of this map are the vertices in the original
	 * graph, and the values are vertices in the spanning tree generated when generateTree()
	 * is called.
	 * @return map from vertices in the original graph to vertices in the spanning tree
	 * @throws IllegalStateException if this method is called before generateTree(). In this case, the map
	 * has not been created yet.
	 */
	public Map<CompleteVertex, CompleteVertex> getVertexMap() {
		if (vertexMap == null) {
			throw new IllegalStateException("Map must first be created by running generateTree()!");
		}
		return vertexMap;
	}
	private void initializeForGenerateTree() {
		vertexMap = new HashMap<CompleteVertex, CompleteVertex>(graph.vertexCount());
		spanningTree = new OldGraph(graph.vertexCount());
		spanningTree.setWeightEpsilon(graph.getWeightEpsilon());
		Iterator<CompleteVertex> it = graph.vertexIterator();
		CompleteVertex v;
		while (it.hasNext()) {
			v = it.next();
			vertexMap.put(v, new CompleteVertex());
			spanningTree.addVertex(vertexMap.get(v));
		}
		vertexDisjointSet = new DisjointSet<CompleteVertex>(graph.getVertices());
		edges = graph.getWeightedEdges().toArray(EMPTY_EDGE_ARRAY);
		Arrays.sort(edges, COMPARATOR);		
	}
	private void initializeForMarking() {
		new EdgeResetter(graph).resetColors();
		vertexDisjointSet = new DisjointSet<CompleteVertex>(graph.getVertices());
		edges = graph.getWeightedEdges().toArray(EMPTY_EDGE_ARRAY);
		Arrays.sort(edges, COMPARATOR);
	}
}