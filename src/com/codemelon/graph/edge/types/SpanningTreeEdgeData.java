package com.codemelon.graph.edge.types;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.edge.interfaces.EdgeColorData;
import com.codemelon.graph.edge.interfaces.EdgeWeightData;

/**
 * Holds basic data needed for running spanning tree algorithms such
 * as Kruskal's and Prim's algorithm
 * @author Marshall Farrier
 * @my.created Dec 11, 2012
 * @my.edited Dec 11, 2012
 */
public class SpanningTreeEdgeData implements EdgeColorData, EdgeWeightData {
	private double weight;
	private Color color;
	/**
	 * Default constructor sets weight to 1.0 and color to white
	 */
	public SpanningTreeEdgeData() {
		this(EdgeConstants.DEFAULT_WEIGHT, Color.WHITE);
	}
	/**
	 * Constructs an edge data object from a weight and color specification
	 * @param weight initial weight of edge data object
	 * @param color initial color of edge data object
	 */
	public SpanningTreeEdgeData(double weight, Color color) {
		this.weight = weight;
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.edge.interfaces.EdgeWeightData#setWeight(double)
	 */
	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.edge.interfaces.EdgeWeightData#getWeight()
	 */
	@Override
	public double getWeight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.edge.interfaces.EdgeColorData#setColor(com.codemelon.graph.common.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see com.codemelon.graph.edge.interfaces.EdgeColorData#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}
	public static SpanningTreeEdgeData newEdgeData() {
		return new SpanningTreeEdgeData();
	}
}
