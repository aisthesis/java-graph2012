package com.codemelon.graph.vertex.interfaces;

import java.util.Set;

import com.codemelon.graph.graph.DiGraph;

/**
 * Basic specification which any Vertex class must implement
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface Vertex {
	/**
	 * Set the graph to which the vertex belongs
	 * @param diGraph graph with which the vertex is to be associated
	 */
	public void setGraph(DiGraph<Vertex> diGraph);/**
	 * Returns the graph to which the vertex belongs.
	 * @return the graph to which the vertex belongs
	 */
	public DiGraph<Vertex> getGraph();
	/**
	 * Adds the specified vertex to the set of adjacencies
	 * @param to vertex to be added
	 * @return true if the adjacency list did not already contain to
	 */
	public boolean addAdjacency(Vertex to);
	/**
	 * Removes the specified vertex from the adjacency set
	 * @param to vertex to be removed
	 * @return true if the vertex was found in the adjacency set
	 */
	public boolean removeAdjacency(Vertex to);/**
	 * Removes all vertices from the adjacency set. The adjacency set will be
	 * empty after this call returns.
	 */
	public void clearAdjacencies();/**
	 * Returns true if the adjacency set contains the specified vertex
	 * @param to vertex whose presence is to be tested
	 * @return true if to is found in the adjacency set
	 */
	public boolean containsAdjacency(Vertex to);/**
	 * Returns the number of vertices in the adjacency set
	 * @return the number of vertices in this adjacency set (its cardinality)
	 */
	public int adjacencyCount();
	/**
	 * Returns a set of all adjacent vertices
	 * @return a set of all adjacent vertices
	 */
	public Set<Vertex> getAdjacencies();/**
	 * Returns true if the vertex is the tail of at least one edge
	 * @return true if the vertex has at least one adjacency
	 */
	public boolean hasAdjacencies();
}
