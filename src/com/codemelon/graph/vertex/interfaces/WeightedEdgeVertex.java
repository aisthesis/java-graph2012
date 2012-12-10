package com.codemelon.graph.vertex.interfaces;

/**
 * Vertex type that supports floating point edge weights.
 * @author Marshall Farrier
 * @version Dec 9, 2012
 */
public interface WeightedEdgeVertex extends Vertex {
	/**
	 * Retrieve the weight assigned to the given edge.
	 * @param to the head of the edge whose weight is to be retrieved.
	 * @return the edge's weight.
	 */
	public double getEdgeWeight(WeightedEdgeVertex to);
	/**
	 * Set the weight of the given edge.
	 * @param to head of the edge whose weight is to be set
	 * @param weight value to which the edge's weight is to be set
	 */
	public void setEdgeWeight(WeightedEdgeVertex to, double weight);
}
