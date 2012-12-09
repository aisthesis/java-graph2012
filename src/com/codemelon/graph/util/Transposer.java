/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.util.VertexFactory;

/**
 * @author Marshall Farrier
 * @version November 30, 2012
 * 
 * Utility class for transposing a graph.
 */
public class Transposer<T extends Vertex> {
	private DiGraph<T> graph;
	private DiGraph<T> transposeGraph;
	private IdentityHashMap<T, T> vertexMap;
	private VertexFactory<T> vertexFactory;
	
	
	private OldDiGraph oldGraph;
	private OldDiGraph oldTransposeGraph;
	private HashMap<CompleteVertex, CompleteVertex> oldVertexMap;

	/**
	 * Creates a transpose graph, which can be retrieved as needed through the
	 * getTransposeGraph() method, and a HashMap from the vertices of the original
	 * graph to the corresponding vertices of the transpose graph.
	 * All vertex data (label, color, distance, discoveryTime, etc.) is copied
	 * into the transpose graph with the exception of parent, which is set to 
	 * null for each vertex in the transpose graph. Edge data, however,
	 * is <em>not</em> copied into the transpose graph.
	 */
	public Transposer(DiGraph<T> graph, VertexFactory<T> vertexFactory) {
		this.graph = graph;
		transposeGraph = new DiGraph<T>(graph.vertexCount());
		this.vertexFactory = vertexFactory;
		transpose();
	}
	public Transposer(OldDiGraph graph) {
		this.oldGraph = graph;
		oldTransposeGraph = new OldDiGraph(graph.vertexCount());
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
	public Map<T, T> getVertexMap() {
		return vertexMap;
	}
	/**
	 * Returns the transpose of the original graph
	 * @return transpose of the original graph
	 */
	public DiGraph<T> getTransposeGraph() {
		return transposeGraph;
	}
	public OldDiGraph getOldTransposeGraph() {
		return oldTransposeGraph;
	}
	
	private void transpose() {
		vertexMap = new IdentityHashMap<T, T>(graph.vertexCount());
		// insert vertices into map and result graph
		Iterator<T> it = graph.vertexIterator();
		T v;
		while (it.hasNext()) {
			v = it.next();
			vertexMap.put(v, vertexFactory.createInstance());
			transposeGraph.addVertex(vertexMap.get(v));
		}
		Set<? extends Vertex> adjacentVertices;
		it = graph.vertexIterator();
		while (it.hasNext()) {
			v = it.next();
			adjacentVertices = v.getAdjacencies();			
			for (Vertex to : adjacentVertices) {
				transposeGraph.addEdge(vertexMap.get(to), vertexMap.get(v));
			}		
		}
	}
}
