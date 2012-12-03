/**
 * Graph class supporting insertion and removal of both vertices and
 * edges.
 * Labeling for vertices is assumed to occur outside of this class.
 * The advantage is that various checks on vertices can then occur
 * much faster than they would if we had to run through the values in 
 * a hash.
 * Before inserting or removing an edge, we need to check whether the
 * graph contains its vertices.
 * And when inserting a vertex, we need to check that it isn't already present.
 * Both of these checks would be cumbersome if we implemented the graph
 * as a map of a label to a vertex.
 */
package com.codemelon.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.edge.EdgeConstants;
import com.codemelon.graph.vertex.Vertex;

/**
 * Graph class supporting insertion and removal of both vertices and
 * edges.
 * Labeling for vertices is assumed to occur outside of this class.
 * The advantage is that various checks on vertices can then occur
 * much faster than they would if we had to run through the values in 
 * a hash.
 * Before inserting or removing an edge, we need to check whether the
 * graph contains its vertices.
 * And when inserting a vertex, we need to check that it isn't already present.
 * Both of these checks would be cumbersome if we implemented the graph
 * as a map of a label to a vertex.
 * 
 * @author Marshall Farrier
 * @version 11-23-2012
 */
public class DiGraph {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private Set<Vertex> vertices;
	private double weightEpsilon;
	
	/**
	 * Default constructor. Creates an empty graph with a default initial
	 * capacity of 16 vertices.
	 */
	public DiGraph() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	/**
	 * Constructor explicitly specifying the expected number of vertices
	 * the graph will contain. For large graphs in which the approximate
	 * number of vertices is known in advance, use of this constructor will
	 * result in significantly improved performance.
	 * @param initialCapacity
	 */
	public DiGraph(int initialCapacity) {
		vertices = new HashSet<Vertex>(initialCapacity);
	}
	/**
	 * Initializes the Graph to contain the vertices in the collection
	 * and clears the adjacency set of all vertices in the collection.
	 * @param initialVertices collection of vertices to include in the Graph
	 */
	public DiGraph(Collection<Vertex> initialVertices) {
		vertices = new HashSet<Vertex>(initialVertices);
		weightEpsilon = EdgeConstants.DEFAULT_WEIGHT_EPSILON;
		for (Vertex v : initialVertices) {
			v.clearAdjacencies();
			v.setGraph(this);
		}
	}
	/**
	 * Inserts v if it is not already present.
	 * @param v vertex to be inserted
	 * @return true if the graph did not already contained the specified vertex
	 */
	public boolean addVertex(Vertex v) {
		if (vertices.add(v)) {
			// vertex was not already present
			v.clearAdjacencies();
			v.setGraph(this);
			return true;
		}
		return false;
	}
	/**
	 * Remove a vertex from the graph. The vertex is removed along with
	 * its adjacency list, and it is also removed from the adjacency set of all
	 * other vertices in the graph. After this method call, not only will
	 * the vertex not belong to the graph, but the adjacency list of the 
	 * vertex will be cleared.
	 * @param v vertex to be removed from the graph
	 * @return true if the vertex was found in the graph
	 */
	public boolean removeVertex(Vertex v) {
		if (vertices.remove(v)) {
			v.clearAdjacencies();
			for (Vertex u : vertices) {
				if (u.removeAdjacency(v)) {
				}
			}
			v.setGraph(null);
			return true;
		}
		return false;
	}
 	/**
	 * Inserts edge if not already present. Returns false if the edge
	 * was already present in the graph.
	 * @param from source vertex
	 * @param to target vertex
	 * @return true if the edge was not previously present in the graph
	 */
	public boolean addEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("Cannot add edge for vertex not present in graph!");
		}
		return from.addAdjacency(to);
	}
	/**
	 * Removes the specified edge if it is present. If the edge
	 * is not found, returns false and does nothing.
	 * @param from edge's source vertex
	 * @param to edge's target vertex
	 * @return true if the edge was found
	 */
	public boolean removeEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("Cannot remove edge for vertex not present in graph!");
		}
		return from.removeAdjacency(to);
	}
	/**
	 * Shows whether or not the graph contains the specified edge
	 * @param from the tail or source vertex of the edge for which we are testing
	 * @param to the head or target vertex of the edge for which we are testing
	 * @return true if the edge from the given tail to the given head exists in the graph.
	 */
	public boolean containsEdge(Vertex from, Vertex to) {
		if (!vertices.contains(from) || !vertices.contains(to)) {
			throw new IllegalArgumentException("One or more vertices not present in graph!");
		}
		return from.containsAdjacency(to);
	}
	/**
	 * Returns an iterator over the vertices in the graph
	 * @return iterator over the graph's vertices
	 */
	public Iterator<Vertex> vertexIterator() {
		return vertices.iterator();
	}
	/**
	 * Shows whether or not the graph contains the given vertex
	 * @param v the vertex whose presence in the graph is to be tested
	 * @return true if the vertex is present in the graph
	 */
	public boolean containsVertex(Vertex v) {
		return vertices.contains(v);
	}
	/**
	 * Returns a list of the vertices in the graph.
	 * @return a list of the vertices in the graph
	 */
	public Set<Vertex> getVertices() {
		return vertices;
	}
	/**
	 * Number of vertices in the graph
	 * @return number of vertices in the graph
	 */
	public int vertexCount() {
		return vertices.size();
	}
	/**
	 * Number of edges in the graph
	 * @return number of edges in the graph
	 */
	public int edgeCount() { 
		int result = 0;
		for (Vertex v : vertices) {
			result += v.adjacencyCount();
		}
		return result; 
	}
	/**
	 * Set the color of an edge in the graph. Throws an exception if the edge doesn't belong
	 * to the graph
	 * @param from tail of the edge to be colored
	 * @param to head of the edge to be colored
	 * @param color color to assign to the edge.
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph.
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public void setEdgeColor(Vertex from, Vertex to, Color color) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		from.setEdgeColor(to, color);
	}
	/**
	 * Returns the color of the specified edge
	 * @param from tail of the edge whose color is to be returned
	 * @param to head of the edge whose color is to be returned
	 * @return the edge's color
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public Color getEdgeColor(Vertex from, Vertex to) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		return from.getEdgeColor(to);
	}
	/**
	 * Set the weight of an edge in the graph. Throws an exception if the edge doesn't belong
	 * to the graph
	 * @param from tail of the edge to be colored
	 * @param to head of the edge to be colored
	 * @param weight weight to assign to the edge.
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph.
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public void setEdgeWeight(Vertex from, Vertex to, double weight) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		from.setEdgeWeight(to, weight);
	}
	/**
	 * Returns the weight of the specified edge
	 * @param from tail of the edge whose weight is to be returned
	 * @param to head of the edge whose weight is to be returned
	 * @return the edge's weight
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public double getEdgeWeight(Vertex from, Vertex to) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		return from.getEdgeWeight(to);
	}
	/**
	 * Set the edge type (TREE, BACK, FORWARD, CROSS) of an edge in the graph. 
	 * Throws an exception if the edge doesn't belong to the graph.
	 * @param from tail of the edge whose type is to be set
	 * @param to head of the edge whose type is to be set
	 * @param edgeType type to assign to the edge (TREE, BACK, FORWARD, or CROSS) 
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph.
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public void setEdgeType(Vertex from, Vertex to, EdgeType edgeType) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		from.setEdgeType(to, edgeType);
	}
	/**
	 * Returns the type (TREE, BACK, FORWARD, CROSS, UNKNOWN) of the specified edge
	 * @param from tail of the edge whose type is to be returned
	 * @param to head of the edge whose type is to be returned
	 * @return the edge's type
	 * @throws IllegalArgumentException if the source vertex doesn't belong to the graph
	 * @throws NullPointerException if there is no such edge in the graph (including the
	 * case where the target vertex doesn't belong to the graph)
	 */
	public EdgeType getEdgeType(Vertex from, Vertex to) {
		if (!vertices.contains(from)) {
			throw new IllegalArgumentException("No such vertex!");
		}
		return from.getEdgeType(to);
	}
	/**
	 * Set the difference (epsilon) used to determine floating point equality for weight.
	 * This value initially defaults to 10<sup>-6</sup>. If the difference in 2 weights
	 * falls below this threshold, the weights are to be considered equal.
	 * <p>Including this method allows us to use a custom value here if needed.
	 * @param weightEpsilon value to which to set the epsilon threshold for weight equality.
	 * @throws IllegalArgumentException if the given epsilon is not stricly positive (great than 0).
	 */
	public void setWeightEpsilon(double weightEpsilon) {
		if (weightEpsilon <= 0) {
			throw new IllegalArgumentException("Epsilon must be positive!");
		}
		this.weightEpsilon = weightEpsilon;
	}
	/**
	 * Get the difference (epsilon) used to determine floating point equality for weight.
	 * This value initially defaults to 10<sup>-6</sup>. If the difference in 2 weights
	 * falls below this threshold, the weights are to be considered equal.
	 * @return epsilon value below which 2 weights are
	 * to be considered equal
	 */
	public double getWeightEpsilon() { return weightEpsilon; }
	/**
	 * Determines whether 2 weight values are to be considered equal in this graph.
	 * @param w1 first weight to compare
	 * @param w2 second weight to compare
	 * @return true iff the absolute value of the difference between the 2 weights is less than
	 * the weightEpsilon for this graph
	 */
	public boolean areEqualWeights(double w1, double w2) {
		return Math.abs(w1 - w2) < weightEpsilon;
	}
}