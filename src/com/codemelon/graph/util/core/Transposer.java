/**
 * Utility class for transposing a graph
 */
package com.codemelon.graph.util.core;

import java.util.Enumeration;
import java.util.Iterator;
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
		Iterator<Vertex> it = graph.vertexIterator();
		Vertex v;
		while (it.hasNext()) {
			v = it.next();
			vertexMap.put(v, new Vertex(v));
			result.addVertex(vertexMap.get(v));
		}
		Enumeration<Vertex> adj;
		Vertex to;
		it = graph.vertexIterator();
		while (it.hasNext()) {
			v = it.next();
			adj = v.getAdjacencies();
			while (adj.hasMoreElements()) {
				to = adj.nextElement();
				result.addEdge(vertexMap.get(to), vertexMap.get(v));
			}			
		}
		return result;
	}
}
