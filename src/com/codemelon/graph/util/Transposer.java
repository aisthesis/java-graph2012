/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version November 30, 2012
 * 
 * Utility class for transposing a graph.
 */
public class Transposer {
	private DiGraph graph;
	private DiGraph transposeGraph;
	private HashMap<CompleteVertex, CompleteVertex> vertexMap;

	/**
	 * Creates a transpose graph, which can be retrieved as needed through the
	 * getTransposeGraph() method, and a HashMap from the vertices of the original
	 * graph to the corresponding vertices of the transpose graph.
	 * All vertex data (label, color, distance, discoveryTime, etc.) is copied
	 * into the transpose graph with the exception of parent, which is set to 
	 * null for each vertex in the transpose graph. Edge data, however,
	 * is <em>not</em> copied into the transpose graph.
	 */
	public Transposer(DiGraph graph) {
		this.graph = graph;
		transposeGraph = new DiGraph(graph.vertexCount());
		transpose();
	}
	/**
	 * Returns a mapping in which the keys are the vertices of the original graph
	 * and the values are the corresponding vertices in the transpose graph. The mapping
	 * is created only when the transpose() method is called. If transpose() has not been
	 * called, this method will return null.
	 * @return a HashMap from vertices in the original graph to vertices in the
	 * transpose graph, or null, if transpose() has not yet been called.
	 */
	public HashMap<CompleteVertex, CompleteVertex> getVertexMap() {
		return vertexMap;
	}
	/**
	 * Returns the transpose of the original graph
	 * @return transpose of the original graph
	 */
	public DiGraph getTransposeGraph() {
		return transposeGraph;
	}
	
	private void transpose() {
		vertexMap = new HashMap<CompleteVertex, CompleteVertex>(graph.vertexCount());
		// insert vertices into map and result graph
		Iterator<CompleteVertex> vertexIterator = graph.vertexIterator();
		CompleteVertex v;
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			vertexMap.put(v, new CompleteVertex(v));
			transposeGraph.addVertex(vertexMap.get(v));
		}
		Set<CompleteVertex> adjacentVertices;
		vertexIterator = graph.vertexIterator();
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			adjacentVertices = v.getAdjacencies();			
			for (CompleteVertex to : adjacentVertices) {
				transposeGraph.addEdge(vertexMap.get(to), vertexMap.get(v));
			}		
		}
	}
}
