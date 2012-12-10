package com.codemelon.graph.search;

import java.util.Iterator;
import java.util.Map;

import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.util.Transposer;
import com.codemelon.graph.vertex.comparators.ReverseSearchOrderComparator;
import com.codemelon.graph.vertex.interfaces.ComponentVertex;
import com.codemelon.graph.vertex.interfaces.VertexFactory;
import com.codemelon.graph.vertex.types.DfsVertex;
import com.codemelon.graph.vertex.types.OrderedDfsVertex;

/**
 * Identify the strongly connected components in a graph using an int value
 * to mark the component to which each vertex belongs.
 * Cf. <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, p. 617.
 * The run() method of StronglyConnectedComponents creates a Transposer object
 * in which the target graph has vertices of type U, which must be a subclass
 * of OrderedDfsVertex. The vertices of the input graph must be subclasses
 * of DfsVertex and must support component labeling but need not themselves
 * support ordered depth-first search.
 * @author Marshall Farrier
 * @version Dec 9, 2012
 */
public class StronglyConnectedComponents<T extends DfsVertex & ComponentVertex, 
		U extends OrderedDfsVertex> {
	private DiGraph<T> graph;
	private VertexFactory<U> vertexFactory;
	
	/**
	 * Prepare to mark the graph for strongly connected components.
	 * No changes to the graph are made when the graph is passed into the constructor.
	 * @param graph graph for which strongly connected components are to be determined.
	 */
	public StronglyConnectedComponents(DiGraph<T> graph, VertexFactory<U> vertexFactory) {
		this.graph = graph;
		this.vertexFactory = vertexFactory;
	}
	
	/**
	 * Marks the strongly connected components in the graph by setting
	 * the treeNumber field of each vertex accordingly. Vertices in the
	 * same strongly connected component will have the same tree number, 
	 * and vertices belonging to different components will have different
	 * tree numbers
	 * @return the Transposer object created when setting the vertex tree numbers.
	 * This object may be discarded or maintained depending on whether or not the client has further
	 * use for the transpose graph.
	 */
	public Transposer<T, U> run() {
		new DepthFirstSearch(graph).search();
		Transposer<T, U> transposer = new Transposer<T, U>(graph, vertexFactory);
		DiGraph<U> transposeGraph = transposer.getTransposeGraph();
		Map<T, U> vertexMap = transposer.getVertexMap();
		// set searchOrder field in transpose graph to finish time in depth-first search
		Iterator<T> it = graph.vertexIterator();
		T v;
		while (it.hasNext()) {
			v = it.next();
			vertexMap.get(v).setSearchOrder(v.getFinishTime());
		}
		new OrderedDepthFirstSearch(transposeGraph, new ReverseSearchOrderComparator()).search();
		// Finally, set the appropriate tree numbers in the original graph
		it = graph.vertexIterator();
		while (it.hasNext()) {
			v = it.next();
			v.setComponent(vertexMap.get(v).getComponent());
		}
		return transposer;
	}
}