package com.codemelon.graph.vertex.types;

/**
 * Vertex supporting a floating point weight value
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface WeightedVertex extends Vertex {
	/**
	 * Set the vertex weight
	 * @param weight value to which to set vertex's weight
	 */
	public void setWeight(double weight);
	/**
	 * Retrieve the vertex's weight
	 * @return the vertex's weight
	 */
	public double getWeight();
}
