/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class Transposer {
	private DiGraph graph;
	private DiGraph transposeGraph;
	private HashMap<Vertex, Vertex> vertexMap;

	/**
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
	public HashMap<Vertex, Vertex> getVertexMap() {
		return vertexMap;
	}
	/**
	 * Returns the transpose graph created when transpose() was called.
	 * If transpose() has not been called, the transposeGraph will be empty.
	 * @return
	 */
	public DiGraph getTransposeGraph() {
		return transposeGraph;
	}
	
	private void transpose() {
		vertexMap = new HashMap<Vertex, Vertex>(graph.vertexCount());
		// insert vertices into map and result graph
		Iterator<Vertex> vertexIterator = graph.vertexIterator();
		Vertex v;
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			vertexMap.put(v, new Vertex(v));
			transposeGraph.addVertex(vertexMap.get(v));
		}
		Set<Vertex> adjacentVertices;
		vertexIterator = graph.vertexIterator();
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			adjacentVertices = v.getAdjacencies();			
			for (Vertex to : adjacentVertices) {
				transposeGraph.addEdge(vertexMap.get(to), vertexMap.get(v));
			}		
		}
	}
}
