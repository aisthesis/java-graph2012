package com.codemelon.graph.vertex.types;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex;
import com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex;
import com.codemelon.graph.edge.types.SpanningTreeEdgeData;

/**
 * Vertex supporting the features used in spanning tree algorithms.
 * The type parameter T is a container for edge data. T must support
 * color and weight.
 * 
 * @author Marshall Farrier
 * @my.created Dec 11, 2012
 * @my.edited Dec 11, 2012
 */
public class SpanningTreeVertex extends EdgeDataVertex<SpanningTreeEdgeData, SpanningTreeEdgeData.Factory>
		implements WeightedEdgeVertex, ColoredEdgeVertex {
	/**
	 * Construct a spanning tree vertex from an edge data factory. The vertex initially belongs to 
	 * no graph and has an empty adjacency list.
	 * @param edgeDataFactory
	 */
	public SpanningTreeVertex() {
		super(SpanningTreeEdgeData.Factory.INSTANCE);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex#setEdgeColor(com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex, com.codemelon.graph.common.Color)
	 */
	@Override
	public void setEdgeColor(ColoredEdgeVertex to, Color color) {
		this.getEdgeData(to).setColor(color);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex#getEdgeColor(com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex)
	 */
	@Override
	public Color getEdgeColor(ColoredEdgeVertex to) {
		return this.getEdgeData(to).getColor();
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex#getEdgeWeight(com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex)
	 */
	@Override
	public double getEdgeWeight(WeightedEdgeVertex to) {
		return this.getEdgeData(to).getWeight();
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex#setEdgeWeight(com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex, double)
	 */
	@Override
	public void setEdgeWeight(WeightedEdgeVertex to, double weight) {
		this.getEdgeData(to).setWeight(weight);
	}
}
