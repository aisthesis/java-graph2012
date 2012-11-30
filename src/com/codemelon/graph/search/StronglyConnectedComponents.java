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
	
	public StronglyConnectedComponents(DiGraph graph) {
		this.graph = graph;
	}
	
	public void run() {
		new DepthFirstSearch(graph).search();
		Transposer transposer = new Transposer(graph);
		DiGraph transposeGraph = transposer.transpose();
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
	}
}
