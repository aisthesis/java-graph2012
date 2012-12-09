package com.codemelon.graph.vertex.util;

import com.codemelon.graph.vertex.interfaces.Vertex;

/**
 * @author Marshall Farrier
 * @version Dec 9, 2012
 */
public interface VertexFactory<T extends Vertex> {
	/**
	 * Creates an instance of the given type of vertex
	 * @return a new instance of the given vertex type
	 */
	public T createInstance();
}
