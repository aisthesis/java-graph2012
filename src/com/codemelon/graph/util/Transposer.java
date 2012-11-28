/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class Transposer {
	private DiGraph graph;
	
	public Transposer(DiGraph graph) {
		this.graph = graph;
	}
		
	public DiGraph transpose() {
		DiGraph result = new DiGraph(graph.vertexCount());
		// create a datastructure for tracking corresponding vertices
		ConcurrentHashMap<Vertex, Vertex> vertexMap = new 
				ConcurrentHashMap<Vertex, Vertex>(graph.vertexCount());
		// insert vertices into map and result graph
		Iterator<Vertex> vertexIterator = graph.vertexIterator();
		Vertex v;
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			vertexMap.put(v, new Vertex(v));
			result.addVertex(vertexMap.get(v));
		}
		Set<Vertex> adjacentVertices;
		vertexIterator = graph.vertexIterator();
		while (vertexIterator.hasNext()) {
			v = vertexIterator.next();
			adjacentVertices = v.getAdjacencies();			
			for (Vertex to : adjacentVertices) {
				result.addEdge(vertexMap.get(to), vertexMap.get(v));
			}		
		}
		return result;
	}
}
