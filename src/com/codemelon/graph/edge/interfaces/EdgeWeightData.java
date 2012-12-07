package com.codemelon.graph.edge.interfaces;

/**
 * Requires that an EdgeData object maintain a floating point weight value
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface EdgeWeightData {
	/**
	 * Set the weight contained in this EdgeData object
	 * @param weight value to which to set the weight
	 */
	public void setWeight(double weight);
	/**
	 * Retrieve the weight contained in this EdgeData object
	 * @return weight stored in this EdgeData object
	 */
	public double getWeight();
}
