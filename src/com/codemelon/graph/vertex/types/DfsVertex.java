package com.codemelon.graph.vertex.types;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.interfaces.EdgeColorData;
import com.codemelon.graph.edge.interfaces.EdgeDataFactory;
import com.codemelon.graph.edge.interfaces.EdgeTypeData;
import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.EdgeTypeVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.VisitedVertex;

/**
 * Needs to be refactored to extend EdgeDataVertex:
 * DfsVertex<T extends EdgeTypeData, U extends EdgeDataFactory<T>> extends EdgeDataVertex<T, U>
 * implements ColoredVertex, ChildVertex, VisitedVertex
 * @author Marshall Farrier
 * @my.created Dec 7, 2012
 * @my.edited Dec 12, 2012
 */
public class DfsVertex<T extends EdgeTypeData & EdgeColorData, U extends EdgeDataFactory<T>>
		extends EdgeDataVertex<T, U> implements Vertex, ColoredVertex, ChildVertex,
		VisitedVertex, EdgeTypeVertex {
	//private DiGraph<? extends Vertex> graph;
	//private IdentityHashMap<Vertex, EdgeType> adjacencies;
	private ChildVertex parent;
	private Color color;
	private int discoveryTime;
	private int finishTime;
	
	public DfsVertex(U edgeDataFactory) {
		super(edgeDataFactory);
		parent = null;
		color = Color.WHITE;
		discoveryTime = VertexConstants.INITIAL_DISCOVERY_TIME;
		finishTime = VertexConstants.INITIAL_FINISH_TIME;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#setEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex, com.codemelon.graph.common.EdgeType)
	 */
	@Override
	public void setEdgeType(EdgeTypeVertex to, EdgeType edgeType) {
		if (!this.containsAdjacency(to)) {
			throw new IllegalArgumentException("Edge does not exist!");
		}
		this.getEdgeData(to).setEdgeType(edgeType);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.EdgeTypeVertex#getEdgeType(com.codemelon.graph.vertex.interfaces.EdgeTypeVertex)
	 */
	@Override
	public EdgeType getEdgeType(EdgeTypeVertex to) {
		return this.getEdgeType(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setDiscoveryTime(int)
	 */
	@Override
	public void setDiscoveryTime(int discoveryTime) {
		this.discoveryTime = discoveryTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#setFinishTime(int)
	 */
	@Override
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getDiscoveryTime()
	 */
	@Override
	public int getDiscoveryTime() {
		return discoveryTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.VisitedVertex#getFinishTime()
	 */
	@Override
	public int getFinishTime() {
		return finishTime;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#setParent(com.codemelon.graph.vertex.interfaces.ChildVertex)
	 */
	@Override
	public void setParent(ChildVertex parent) {
		if (parent == null) {
			this.parent = parent;
			return;
		}
		if (this.getGraph() == null) {
			throw new IllegalArgumentException("Vertex must belong to a graph to have a parent!");
		}
		if (parent.getGraph() != this.getGraph()) {
			throw new IllegalArgumentException("Parent must belong to the same graph!");
		}
		this.parent = parent;

	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ChildVertex#getParent()
	 */
	@Override
	public ChildVertex getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#setColor(com.codemelon.graph.common.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredVertex#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}
}