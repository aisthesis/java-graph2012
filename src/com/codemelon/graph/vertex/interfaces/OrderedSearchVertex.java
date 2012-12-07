package com.codemelon.graph.vertex.interfaces;


/**
 * Vertex that supports a field for conducting searches in a specific order
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface OrderedSearchVertex extends Vertex {
	/**
	 * Set the value of the searchOrder field
	 * @param searchOrder value to which to set the searchOrder field
	 */
	public void setSearchOrder(int searchOrder);
	/**
	 * Get the value of the searchOrder field
	 * @return the value of the searchOrder field
	 */
	public int getSearchOrder();
}
