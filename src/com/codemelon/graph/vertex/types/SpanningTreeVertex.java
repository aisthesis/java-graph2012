package com.codemelon.graph.vertex.types;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.edge.interfaces.EdgeColorData;
import com.codemelon.graph.edge.interfaces.EdgeDataFactory;
import com.codemelon.graph.edge.interfaces.EdgeWeightData;
import com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex;
import com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex;

/**
 * Vertex supporting the features used in spanning tree algorithms.
 * The type parameter T is a container for edge data. T must support
 * color and weight.
 * 
 * Note: We really should factor out EdgeDataVertex<T, U extends EdgeDataFactory<T>>
 * which could then be superclass to DfsVertex as well as this class. This kind
 * of vertex would implement adjacencies as a HashMap whereas SimpleVertex (no parameter)
 * could be extended for BFS or other operations requiring no edge data.
 * BfsVertex should actually be changed to an interface so that BFS can be run
 * either on SimpleVertex or on vertices supporting edge data.
 * @author Marshall Farrier
 * @my.created Dec 11, 2012
 * @my.edited Dec 11, 2012
 */
public class SpanningTreeVertex<T extends EdgeColorData & EdgeWeightData, U extends EdgeDataFactory<T>> 
		extends EdgeDataVertex<T, U> implements WeightedEdgeVertex, ColoredEdgeVertex {
	/**
	 * Construct a spanning tree vertex from an edge data factory. The vertex initially belongs to 
	 * no graph and has an empty adjacency list.
	 * @param edgeDataFactory
	 */
	public SpanningTreeVertex(U edgeDataFactory) {
		super(edgeDataFactory);
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
		return this.getEdgeColor(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex#getEdgeWeight(com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex)
	 */
	@Override
	public double getEdgeWeight(WeightedEdgeVertex to) {
		return this.getEdgeWeight(to);
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex#setEdgeWeight(com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex, double)
	 */
	@Override
	public void setEdgeWeight(WeightedEdgeVertex to, double weight) {
		this.getEdgeData(to).setWeight(weight);
	}
}
