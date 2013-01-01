/**
 * 
 */
package com.codemelon.graph.search;

import java.util.HashMap;
import java.util.Iterator;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.util.Transposer;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.comparators.ReverseSearchOrderComparator;
import com.codemelon.graph.vertex.interfaces.VertexFactory;
import com.codemelon.graph.vertex.types.DfsVertex;

/**
 * Identify the strongly connected components in a graph using an int value
 * to mark the component to which each vertex belongs.
 * Cf. <a href="http://mitpress.mit.edu/algorithms/">CLRS</a>, p. 617
 * @author Marshall Farrier
 * @version Dec 9, 2012
 */
public class StronglyConnectedComponents {
	private DiGraph<? extends DfsVertex> graph;
	private VertexFactory<? extends DfsVertex> vertexFactory;
	
	private OldDiGraph oldGraph;
	
	/**
	 * Prepare to mark the graph for strongly connected components.
	 * No changes to the graph are made when the graph is passed into the constructor.
	 * @param graph graph for which strongly connected components are to be determined.
	 */
	public StronglyConnectedComponents(DiGraph<? extends DfsVertex> graph,
			VertexFactory<? extends DfsVertex> vertexFactory) {
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
	public Transposer run() {
		//new DepthFirstSearch(graph).search();
		Transposer transposer = new Transposer(oldGraph);
		OldDiGraph transposeGraph = transposer.getOldTransposeGraph();
		// set searchOrder field in transpose graph to finish time in depth-first search
		Iterator<CompleteVertex> it = transposeGraph.vertexIterator();
		CompleteVertex v;
		while (it.hasNext()) {
			v = it.next();
			v.searchOrder = v.finishTime;
		}
		//new InOrderDepthFirstSearch(transposeGraph, new ReverseSearchOrderComparator()).search();
		// Finally, set the appropriate tree numbers in the original graph
		it = oldGraph.vertexIterator();
		//HashMap<CompleteVertex, CompleteVertex> vertexMap = transposer.getVertexMap();
		while (it.hasNext()) {
			v = it.next();
//			v.treeNumber = vertexMap.get(v).treeNumber;
		}
		return transposer;
	}
}