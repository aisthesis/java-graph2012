/**
 * 
 */
package com.codemelon.graph.vertex;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class CompositeVertex<T> extends SimpleVertex implements Vertex {
	public T data;
	public CompositeVertex() {
		this(null);
	}
	public CompositeVertex(T data) {
		super();
		this.data = data;
	}
}
