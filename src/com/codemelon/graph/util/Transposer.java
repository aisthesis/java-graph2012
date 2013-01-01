/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.VertexFactory;

/**
 * @author Marshall Farrier
 * @version November 30, 2012
 * 
 * Utility class for transposing a graph. The type parameter T specifies the type
 * of the vertices in the original graph, and the type U specifies the type
 * of vertices in the transpose graph created by the transposer object. These
 * 2 types will often be the same but do not have to be.
 * The contract of the transposer is: 
 * <ol>
 * <li>to produce a vertex of type U corresponding to each vertex in the input graph, and</li>
 * <li>to create an edge in the new graph whose tail corresponds to the head and whose
 * head corresponds to the tail of the respective edge in the input graph</li>
 * </ol>
 * No satellite data is preserved by the transpose operation. It must be set in subsequent
 * operations on the transpose graph.
 */
public class Transposer<T extends Vertex, U extends Vertex> {
	private DiGraph<T> graph;
	private DiGraph<U> transposeGraph;
	private IdentityHashMap<T, U> vertexMap;
	private VertexFactory<U> vertexFactory;

	/**
	 * Creates a transpose graph, which can be retrieved as needed through the
	 * getTransposeGraph() method, and a HashMap from the vertices of the original
	 * graph to the corresponding vertices of the transpose graph.
	 * Vertex and edge data (color, distance, discoveryTime, etc.) are <em>not</em> copied
	 * into the transpose graph. Satellite data can be set accordingly using the vertex
	 * mapping created during the transpose operation.
	 * @param graph graph for which the transpose is to be created.
	 * @param vertexFactory factory method used to create vertices in the transpose
	 * graph
	 */
	public Transposer(DiGraph<T> graph, VertexFactory<U> vertexFactory) {
		this.graph = graph;
		transposeGraph = new DiGraph<U>(graph.vertexCount());
		this.vertexFactory = vertexFactory;
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
	public Map<T, U> getVertexMap() {
		return vertexMap;
	}
	/**
	 * Returns the transpose of the original graph
	 * @return transpose of the original graph
	 */
	public DiGraph<U> getTransposeGraph() {
		return transposeGraph;
	}
	
	private void transpose() {
		vertexMap = new IdentityHashMap<T, U>(graph.vertexCount());
		// insert vertices into map and result graph
		Iterator<T> it = graph.vertexIterator();
		T v;
		while (it.hasNext()) {
			v = it.next();
			vertexMap.put(v, vertexFactory.newVertex());
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
