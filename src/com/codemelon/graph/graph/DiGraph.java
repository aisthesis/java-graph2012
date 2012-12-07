package com.codemelon.graph.graph;

import java.util.Set;

import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public class DiGraph<T extends Vertex> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private Set<Vertex> vertices;
}
