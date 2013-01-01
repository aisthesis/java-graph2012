package com.codemelon.graph.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public class DiGraph {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private Set<Vertex> vertices;
	/**
	 * Initialize graph to a default initial capacity of 16 vertices
	 */
	public DiGraph() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	/**
	 * Initialize graph to have capacity for the given number of vertices
	 * @param initialCapacity expected number of vertices
	 */
	public DiGraph(int initialCapacity) {
		vertices = new HashSet<Vertex>(initialCapacity);
	}
	public DiGraph(Collection<Vertex> vertices) {
		this.vertices = new HashSet<Vertex>(vertices);
		for (Vertex v : vertices) {
			v.clearAdjacencies();
			v.setGraph(this);
		}		
	}
}
