/**
 * 
 */
package com.codemelon.graph.search;

import java.util.HashMap;
import java.util.Iterator;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.util.Transposer;
import com.codemelon.graph.vertex.ReverseSearchOrderComparator;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 29, 2012
 * Cf. CLRS, p. 617
 */
public class StronglyConnectedComponents {
	private DiGraph graph;
	
	/**
	 * Prepare to mark the graph for strongly connected components.
	 * No changes to the graph are made when the graph is passed into the constructor.
	 * @param graph graph for which strongly connected components are to be determined.
	 */
	public StronglyConnectedComponents(DiGraph graph) {
		this.graph = graph;
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
		new DepthFirstSearch(graph).search();
		Transposer transposer = new Transposer(graph);
		DiGraph transposeGraph = transposer.getTransposeGraph();
		// set searchOrder field in transpose graph to finish time in depth-first search
		Iterator<Vertex> it = transposeGraph.vertexIterator();
		Vertex v;
		while (it.hasNext()) {
			v = it.next();
			v.searchOrder = v.finishTime;
		}
		new InOrderDepthFirstSearch(transposeGraph, new ReverseSearchOrderComparator()).search();
		// Finally, set the appropriate tree numbers in the original graph
		it = graph.vertexIterator();
		HashMap<Vertex, Vertex> vertexMap = transposer.getVertexMap();
		while (it.hasNext()) {
			v = it.next();
			v.treeNumber = vertexMap.get(v).treeNumber;
		}
		return transposer;
	}
}