package com.codemelon.graph.spanningtree;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

import com.codemelon.graph.Graph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.DisjointSet;
import com.codemelon.graph.util.EdgeResetter;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;
import com.codemelon.graph.vertex.VertexAndWeight;

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
	private static final Comparator<AbstractMap.SimpleEntry<Vertex, Double>> COMPARATOR = 
			new Comparator<AbstractMap.SimpleEntry<Vertex, Double>>() {
				@Override
				public int compare(SimpleEntry<Vertex, Double> entry1,
						SimpleEntry<Vertex, Double> entry2) {
					if (entry1.getValue() < entry2.getValue()) { return -1; }
					if (entry2.getValue() < entry1.getValue()) { return 1; }
					return 0;
				}		
	};
	private Graph graph;
	private PriorityQueue<VertexAndWeight> queue;
	// TODO This implementation replaces use of weird VertexAndWeight class altogether
	private PriorityQueue<AbstractMap.SimpleEntry<Vertex, Double>> queue2;
	
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
		VertexAndWeight u;
		while (!queue.isEmpty()) {
			u = queue.poll();
			u.vertex().color = Color.BLACK; // mark the vertices no longer in the queue
			//TODO
		}
	}
	private void initializeForMarking(Vertex root) {
		weightMap = new HashMap<Vertex, Double>(graph.vertexCount());
		// now set up priority queue using a Comparator on AbstractMap.SimpleEntry<Vertex, Double>
		
		new VertexResetter(graph).resetColors();
		queue = new PriorityQueue<VertexAndWeight>(graph.vertexCount());
		for (Vertex vertex : graph.getVertices()) {
			queue.add(new VertexAndWeight(vertex, vertex == root ? 0.0 : Double.MAX_VALUE));
			vertex.parent = null;
		}
	}
}
