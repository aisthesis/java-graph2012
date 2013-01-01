package com.codemelon.graph.vertex.types;

import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.OrderedSearchVertex;

/**
 * @author Marshall Farrier
 * @version Dec 8, 2012
 */
public class OrderedDfsVertex extends DfsVertex implements OrderedSearchVertex {
	private int searchOrder;
	
	public OrderedDfsVertex() {
		super();
		searchOrder = VertexConstants.DEFAULT_SEARCH_ORDER_VALUE;
	}

	@Override
	public void setSearchOrder(int searchOrder) {
		this.searchOrder = searchOrder;
	}

	@Override
	public int getSearchOrder() {
		return searchOrder;
	}

}
