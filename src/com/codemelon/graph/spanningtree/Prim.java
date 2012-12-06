package com.codemelon.graph.spanningtree;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.codemelon.graph.Graph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.DisjointSet;
import com.codemelon.graph.util.EdgeResetter;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;
import com.codemelon.graph.vertex.VertexAndWeight;
import com.codemelon.graph.vertex.VertexConstants;

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
	private static final Comparator<Map.Entry<Vertex, Double>> COMPARATOR = 
			new Comparator<Map.Entry<Vertex, Double>>() {
				@Override
				public int compare(Map.Entry<Vertex, Double> entry1,
						Map.Entry<Vertex, Double> entry2) {
					if (entry1.getValue() < entry2.getValue()) { return -1; }
					if (entry2.getValue() < entry1.getValue()) { return 1; }
					return 0;
				}		
	};
	private Graph graph;
	private PriorityQueue<VertexAndWeight> queueOld;
	// TODO This implementation replaces use of weird VertexAndWeight class altogether
	private PriorityQueue<Map.Entry<Vertex, Double>> queue;
	
	private Map<Vertex, Double> weightMap;
	
	public Prim(Graph graph) {
		this.graph = graph;
		queue = null;
	}
	/**
	 * Set edges in minimum spanning tree to MARKER_COLOR, all other edges are 
	 * set to EdgeConstants.DEFAULT_COLOR. Parents of all vertices in the graph are also modified
	 * to show a path leading back to the specified root.
	 */
	public void markEdges(Vertex root) {
		initializeForMarking(root);
		Map.Entry<Vertex, Double> u;
		Set<Vertex> adj;
		while (!queue.isEmpty()) {
			u = queue.poll();
			adj = u.getKey().getAdjacencies();
			for (Vertex v : adj) {
				// initial color means that v is still in the queue
				if (v.color == VertexConstants.INITIAL_COLOR && 
						graph.getEdgeWeight(u.getKey(), v) < weightMap.get(v)) {
					v.parent = u.getKey();
					// TODO
					/*
					 * This is where this implementation breaks down. Now it is 
					 * impossible to retrieve the Entry object without iterating through
					 * the entire collection
					 */
				}
			}
		}
	}
	private void initializeForMarking(Vertex root) {
		new VertexResetter(graph).primReset();
		weightMap = new HashMap<Vertex, Double>(graph.vertexCount());
		Set<Vertex> vertices = graph.getVertices();
		for (Vertex vertex : vertices) {
			weightMap.put(vertex, Double.MAX_VALUE);
		}
		weightMap.put(root, 0.0);
		queue = new PriorityQueue<Map.Entry<Vertex, Double>>(graph.vertexCount(), COMPARATOR);
		Set<Map.Entry<Vertex, Double>> entrySet = weightMap.entrySet();
		for (Map.Entry<Vertex, Double> entry : entrySet) {
			queue.add(entry);
		}
	}
}
