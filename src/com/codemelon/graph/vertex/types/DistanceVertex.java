package com.codemelon.graph.vertex.types;

/**
 * Vertex that maintains a distance field
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface DistanceVertex extends Vertex {
	/**
	 * Set the distance field
	 * @param distance value to which to set the distance field
	 */
	public void setDistance(int distance);
	/**
	 * Get the value of the distance field
	 * @return the value of the distance field
	 */
	public int getDistance();
}
