package com.codemelon.graph.vertex.types;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.common.VertexConstants;
import com.codemelon.graph.vertex.interfaces.ChildVertex;
import com.codemelon.graph.vertex.interfaces.ColoredVertex;
import com.codemelon.graph.vertex.interfaces.DistanceVertex;

/**
 * Graph with components required for breadth-first search
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public class BfsVertex extends SimpleVertex implements ColoredVertex, DistanceVertex,
		ChildVertex {
	private ChildVertex parent;
	private int distance;
	private Color color;
	
	/**
	 * Create a vertex belonging to no graph (graph is null), with no adjacencies,
	 * no parent (parent is null) and default values for color and distance.
	 */
	public BfsVertex() {
		super();
		parent = null;
		distance = VertexConstants.INITIAL_DISTANCE;
		color = Color.WHITE;
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
	 * @see com.codemelon.graph.vertex.interfaces.DistanceVertex#setDistance(int)
	 */
	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.DistanceVertex#getDistance()
	 */
	@Override
	public int getDistance() {
		return distance;
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