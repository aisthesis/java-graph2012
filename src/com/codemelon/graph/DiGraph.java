/**
 * Graph class supporting insertion and removal of both vertices and
 * edges.
 * Labeling for vertices is assumed to occur outside of this class.
 * The advantage is that various checks on vertices can then occur
 * much faster than they would if we had to run through the values in 
 * a hash.
 * Before inserting or removing an edge, we need to check whether the
 * graph contains its vertices.
 * And when inserting a vertex, we need to check that it isn't already present.
 * Both of these checks would be cumbersome if we implemented the graph
 * as a map of a label to a vertex.
 */
package com.codemelon.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class DiGraph {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private Set<Vertex> vertices;
	private long edges;
	
	public DiGraph() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	public DiGraph(int initialCapacity) {
		vertices = Collections.synchronizedSet(new HashSet<Vertex>(initialCapacity));
		edges = 0L;
	}
	/**
	 * Initializes the Graph to contain the vertices in the collection
	 * and clears the adjacency set of all vertices in the collection.
	 * @param initialVertices collection of vertices to include in the Graph
	 */
	public DiGraph(Collection<Vertex> initialVertices) {
		vertices = Collections.synchronizedSet(new HashSet<Vertex>(initialVertices));
		edges = 0L;
		for (Vertex v : initialVertices) {
			v.clearAdjacencies();
		}
	}
	/**
	 * Inserts v if it is not already present.
	 * @param v vertex to be inserted
	 * @return true if the graph did not already contained the specified vertex
	 */
	public boolean addVertex(Vertex v) {
		if (vertices.add(v)) {
			// vertex was not already present
			v.clearAdjacencies();
			v.setGraph(this);
			return true;
		}
		return false;
	}
	public boolean removeVertex(Vertex v) {
		if (vertices.remove(v)) {
			edges -= v.adjacencyCount();
			v.clearAdjacencies();
			for (Vertex u : vertices) {
				if (u.removeAdjacency(v)) {
					edges--;
				}
			}
			v.setGraph(null);
			return true;
		}
		return false;
	}
 	/**
	 * Inserts edge if not already present. Returns false if the edge
	 * was already present in the graph.
	 * @param from source vertex
	 * @param to target vertex
	 * @return true if the edge was not previously present in the graph
	 */
	public boolean addEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("Cannot add edge for vertex not present in graph!");
		}
		if (from.addAdjacency(to)) {
			edges++;
			return true;
		}
		return false;
	}
	/**
	 * Removes the specified edge if it is present. If the edge
	 * is not found, returns false and does nothing.
	 * @param from edge's source vertex
	 * @param to edge's target vertex
	 * @return true if the edge was found
	 */
	public boolean removeEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("Cannot remove edge for vertex not present in graph!");
		}
		if (from.removeAdjacency(to)) {
			edges--;
			return true;
		}
		return false;
	}
	
	public boolean containsEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("One or more vertices not present in graph!");
		}
		return from.containsAdjacency(to);
	}
	/**
	 * Returns an iterator over the vertices in the graph
	 * @return iterator over the graph's vertices
	 */
	public Iterator<Vertex> vertexIterator() {
		return vertices.iterator();
	}
	
	public boolean containsVertex(Vertex v) {
		return vertices.contains(v);
	}
	public ArrayList<Vertex> getVertices() {
		return new ArrayList<Vertex>(vertices);
	}
	/**
	 * Number of vertices in the graph
	 * @return number of vertices in the graph
	 */
	public int vertexCount() {
		return vertices.size();
	}
	/**
	 * Number of edges in the graph
	 * @return number of edges in the graph
	 */
	public long edgeCount() { return edges; }
}