/**
 * This class guarantees that the vertices will always
 * be searched in a specific order. To do so, however,
 * we have to sort the list of all vertices in the graph once,
 * then each adjacency list that is explored.
 */
package com.codemelon.graph.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.Vertex;
import com.codemelon.graph.vertex.SearchOrderComparator;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class DeterministicDepthFirstSearch {
	private DiGraph graph;
	private int t;	// time in CLRS
	private ArrayList<Vertex> vertices;
	private Comparator<Vertex> comp;
	
	private static final Vertex[] emptyVertexArray = new Vertex[0];
	
	public DeterministicDepthFirstSearch(DiGraph graph, Comparator<Vertex> comp) {
		this.graph = graph;
		vertices = graph.getVertices();
		this.comp = comp;
		t = 0;
	}
	/**
	 * If comparator is unspecified, use SearchOrderComparator
	 * @param graph graph to search
	 */
	public DeterministicDepthFirstSearch(DiGraph graph) {
		this(graph, new SearchOrderComparator());
	}
	
	public void search() {
		new VertexResetter(graph).dfsReset();
		Collections.sort(vertices, comp);
		t = 0;
		for (Vertex u : vertices) {
			if (u.color == Color.WHITE) {
				visit(u);
			}		
		}
	}
	private void visit(Vertex u) {
		u.discoveryTime = ++t;
		u.color = Color.GRAY;
		Vertex[] adjacentVertices = u.getAdjacencies().toArray(emptyVertexArray);
		Arrays.sort(adjacentVertices, comp);
		for (Vertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				v.parent = u;
				visit(v);
			}		
		}
		u.color = Color.BLACK;
		u.finishTime = ++t;
	}
}