package com.codemelon.graph.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.comparators.SearchOrderComparator;
import com.codemelon.graph.vertex.types.OrderedDfsVertex;
import com.codemelon.graph.vertex.util.VertexResetter;
import com.codemelon.graph.vertex.interfaces.ComponentVertex;
import com.codemelon.graph.vertex.interfaces.OrderedSearchVertex;

/**
 * This class guarantees that the vertices will always
 * be searched in a specific order. To do so, however,
 * we have to sort the list of all vertices in the graph once,
 * then each adjacency list that is explored. Due to this overhead,
 * simple DepthFirstSearch is preferable when the order for
 * visiting vertices is immaterial to the search.
 * @author Marshall Farrier
 * @version Dec 8, 2012
 *
 */
public class OrderedDepthFirstSearch {
	private DiGraph<? extends OrderedDfsVertex> graph;
	private int t;	// time in CLRS
	private int treeNumber; // used in StronglyConnectedComponents
	private List<OrderedDfsVertex> vertices;
	private Comparator<OrderedSearchVertex> comp;
	
	private static final OrderedDfsVertex[] EMPTY_VERTEX_ARRAY = new OrderedDfsVertex[0];
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
	public OrderedDepthFirstSearch(DiGraph<? extends OrderedDfsVertex> graph, 
			Comparator<OrderedSearchVertex> comp) {
		this.graph = graph;
		vertices = new ArrayList<OrderedDfsVertex>(graph.getVertices());
		this.comp = comp;
	}
	/**
	 * If comparator is unspecified, use SearchOrderComparator as the default.
	 * SearchOrderComparator uses for comparison the searchOrder member field of
	 * Vertex.
	 * @param graph graph to search
	 */
	public OrderedDepthFirstSearch(DiGraph<? extends OrderedDfsVertex> graph) {
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
	 * this search method does not modify the edgeType of the graph's vertices--i.e.,
	 * it does not categorize the edges in the graph. Nor does it return a value
	 * specifying whether or not the graph is cyclic.
	 */
	public void search() {
		VertexResetter.resetForDfs(graph);
		Collections.sort(vertices, comp);
		t = DepthFirstSearch.FIRST_DISCOVERY_TIME - 1;
		treeNumber = ComponentVertex.FIRST_COMPONENT_NUMBER - 1;
		for (OrderedDfsVertex u : vertices) {
			if (u.getColor() == Color.WHITE) {
				treeNumber++;
				visit(u);
			}		
		}
	}
	private void visit(OrderedDfsVertex u) {
		u.setDiscoveryTime(++t);
		u.setComponent(treeNumber);
		u.setColor(Color.GRAY);
		OrderedDfsVertex[] adjacentVertices = u.getAdjacencies().toArray(EMPTY_VERTEX_ARRAY);
		Arrays.sort(adjacentVertices, comp);
		for (OrderedDfsVertex v : adjacentVertices) {
			if (v.getColor() == Color.WHITE) {
				v.setParent(u);
				visit(v);
			}		
		}
		u.setColor(Color.BLACK);
		u.setFinishTime(++t);
	}
}