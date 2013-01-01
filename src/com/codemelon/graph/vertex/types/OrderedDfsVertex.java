package com.codemelon.graph.vertex.types;

import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ComponentVertex;
import com.codemelon.graph.vertex.interfaces.OrderedSearchVertex;

/**
 * @author Marshall Farrier
 * @version Dec 8, 2012
 */
public class OrderedDfsVertex extends DfsVertex implements OrderedSearchVertex,
		ComponentVertex {
	private int searchOrder;
	private int component;
	
	public OrderedDfsVertex() {
		super();
		searchOrder = VertexConstants.DEFAULT_SEARCH_ORDER_VALUE;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.OrderedSearchVertex#setSearchOrder(int)
	 */
	@Override
	public void setSearchOrder(int searchOrder) {
		this.searchOrder = searchOrder;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.OrderedSearchVertex#getSearchOrder()
	 */
	@Override
	public int getSearchOrder() {
		return searchOrder;
	}
	
	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ComponentVertex#setComponent(int)
	 */
	@Override
	public void setComponent(int componentNumber) {
		this.component = componentNumber;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ComponentVertex#getComponent()
	 */
	@Override
	public int getComponent() {
		return component;
	}
}