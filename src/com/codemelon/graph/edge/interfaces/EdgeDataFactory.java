package com.codemelon.graph.edge.interfaces;

/**
 * Factory for creating new EdgeData objects of any desired type
 * @author Marshall Farrier
 * @my.created Dec 11, 2012
 * @my.edited Dec 11, 2012
 */
public interface EdgeDataFactory<T> {
	/**
	 * Factory method for creating new EdgeData objects of a particular type
	 * @return new EdgeData object
	 */
	public T newEdgeData();
}
