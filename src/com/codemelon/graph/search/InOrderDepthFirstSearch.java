/**
 * This class guarantees that the vertices will always
 * be searched in a specific order. To do so, however,
 * we have to sort the list of all vertices in the graph once,
 * then each adjacency list that is explored. Due to this overhead,
 * simple DepthFirstSearch is preferable when the order for
 * visiting vertices is immaterial to the search.
 */
package com.codemelon.graph.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.util.VertexResetter;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.comparators.SearchOrderComparator;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class InOrderDepthFirstSearch {
	private OldDiGraph graph;
	private int t;	// time in CLRS
	private int treeNumber; // used in StronglyConnectedComponents
	private List<CompleteVertex> vertices;
	private Comparator<CompleteVertex> comp;
	
	private static final CompleteVertex[] EMPTY_VERTEX_ARRAY = new CompleteVertex[0];
	/**
	 * Prepares the graph for a depth-first search where vertices
	 * are visited in the order specified by the Comparator passed
	 * to this constructor. The constructor does not modify the graph
	 * passed but merely sets up the framework to be used when the
	 * search() method is called.
	 * @param graph The graph to be searched
	 * @param comp Comparator determining the order in which vertices
	 * will be visited.
	 */
	public InOrderDepthFirstSearch(OldDiGraph graph, Comparator<CompleteVertex> comp) {
		this.graph = graph;
		vertices = new ArrayList<CompleteVertex>(graph.getVertices());
		this.comp = comp;
	}
	/**
	 * If comparator is unspecified, use SearchOrderComparator as the default.
	 * SearchOrderComparator uses for comparison the searchOrder member field of
	 * Vertex.
	 * @param graph graph to search
	 */
	public InOrderDepthFirstSearch(OldDiGraph graph) {
		this(graph, new SearchOrderComparator());
	}
	
	/**
	 * Conduct a depth-first search on the graph, visiting vertices
	 * in the order specified in the constructor.
	 * This method modifies the graph in the following ways:
	 * <ol>
	 * <li>All vertices will be colored black after this method call.</li>
	 * <li>All vertices will have discoveryTime and finishTime set according
	 * to the order in which they were first discovered and finished.</li>
	 * </ol>
	 * In contrast to the corresponding method in simple DepthFirstSearch,
	 * this search method does modify the edgeType of the graph's vertices--i.e.,
	 * it does not categorize the edges in the graph. Nor does it return a value
	 * specifying whether or not the graph is cyclic.
	 */
	public void search() {
		new VertexResetter(graph).dfsReset();
		Collections.sort(vertices, comp);
		t = 0;
		treeNumber = 0;
		for (CompleteVertex u : vertices) {
			if (u.color == Color.WHITE) {
				treeNumber++;
				visit(u);
			}		
		}
	}
	private void visit(CompleteVertex u) {
		u.discoveryTime = ++t;
		u.treeNumber = treeNumber;
		u.color = Color.GRAY;
		CompleteVertex[] adjacentVertices = u.getAdjacencies().toArray(EMPTY_VERTEX_ARRAY);
		Arrays.sort(adjacentVertices, comp);
		for (CompleteVertex v : adjacentVertices) {
			if (v.color == Color.WHITE) {
				v.parent = u;
				visit(v);
			}		
		}
		u.color = Color.BLACK;
		u.finishTime = ++t;
	}
}