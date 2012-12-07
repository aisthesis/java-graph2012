/**
 * Since there is no guarantee that an edge must map to 
 * a unique EdgeData, it is advantageous for this class to
 * be immutable.
 */
package com.codemelon.graph.edge;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;


/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class CompleteEdgeData {
	private Color color;
	private EdgeType edgeType;
	private double weight;
	
	/**
	 * Default constructor. Edge color is initially set to white, type to unknown, and weight to 1.0.
	 */
	public CompleteEdgeData() { this(EdgeConstants.DEFAULT_COLOR, EdgeConstants.DEFAULT_EDGE_TYPE, 
			EdgeConstants.DEFAULT_WEIGHT); }
	/**
	 * Sets initial edge color to the specified color, type to unknown and weight to 1.0
	 * @param color color to which the edge is set
	 */
	public CompleteEdgeData(Color color) { this(color, EdgeConstants.DEFAULT_EDGE_TYPE, 
			EdgeConstants.DEFAULT_WEIGHT); }
	/**
	 * Sets initial edge type to the specified type, color white, and weight to 1.0
	 * @param edgeType type to which edge is initialized
	 */
	public CompleteEdgeData(EdgeType edgeType) { this(EdgeConstants.DEFAULT_COLOR, edgeType, 
			EdgeConstants.DEFAULT_WEIGHT); }
	/**
	 * Sets initial edge type to the specified type, color white, and weight to 1.0
	 * @param weight weight to which edge is initialized
	 */
	public CompleteEdgeData(double weight) { this(EdgeConstants.DEFAULT_COLOR, 
			EdgeConstants.DEFAULT_EDGE_TYPE, weight); }
	/**
	 * Sets initial type to the specified type, color to the specified color and weight to 1.0
	 * @param color color that the edge will initially have
	 * @param edgeType initial edge type
	 */
	public CompleteEdgeData(Color color, EdgeType edgeType) { this(color, edgeType, EdgeConstants.DEFAULT_WEIGHT); }
	/**
	 * Specify all edge parameters explicitly
	 * @param color initial edge color
	 * @param edgeType initial edge type
	 * @param weight initial edge weight
	 */
	public CompleteEdgeData(Color color, EdgeType edgeType, double weight) {
		this.color = color;
		this.edgeType = edgeType;
		this.weight = weight;
	}
	/**
	 * Get the edge color
	 * @return the edge's color
	 */
	public Color getColor() { return color; }
	/**
	 * Get the edge type
	 * @return the edge's type
	 */
	public EdgeType getType() { return edgeType; }
	/**
	 * Get edge weight
	 * @return the edge's weight
	 */
	public double getWeight() { return weight; }
	/**
	 * Set the edge color
	 * @param color color to which edge color should be set
	 */
	public void setColor(Color color) { this.color = color; }
	/**
	 * Set the edge type
	 * @param edgeType type to which the edge should be set
	 */
	public void setType(EdgeType edgeType) { this.edgeType = edgeType; }
	/**
	 * Set edge weight
	 * @param weight weight to which the edge is to be set
	 */
	public void setWeight(double weight) { this.weight = weight; }
}
