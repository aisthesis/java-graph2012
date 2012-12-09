package com.codemelon.graph.vertex.types;

import com.codemelon.graph.vertex.interfaces.VertexFactory;

/**
 * @author Marshall Farrier
 * @version Dec 9, 2012
 */
public enum OrderedDfsVertexFactory implements VertexFactory<OrderedDfsVertex> {
	INSTANCE;
	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VertexFactory#newVertex()
	 */
	@Override
	public OrderedDfsVertex newVertex() {
		return new OrderedDfsVertex();
	}
}
