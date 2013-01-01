package com.codemelon.graph.graph.types;

import java.util.Collection;


import com.codemelon.graph.common.Color;
import com.codemelon.graph.graph.GraphConstants;
import com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex;
import com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex;

/**
 * Undirected graph supporting edge weight and edge color.
 * @author Marshall Farrier
 * @version Dec 10, 2012
 */
public class WeightedEdgeGraph<T extends WeightedEdgeVertex & ColoredEdgeVertex> extends Graph<T> {
	private double weightEpsilon;
	/**
	 * Initialize graph to a default initial capacity of 16 vertices and
	 * a default weight epsilon of 0.000001
	 */
	public WeightedEdgeGraph() {
		this(GraphConstants.DEFAULT_WEIGHT_EPSILON);
	}
	/**
	 * Initialize graph to a default initial capacity of 16 vertices and
	 * the specified weight epsilon
	 * @param weightEpsilon weight epsilon to be used to determine weight equality
	 * in this graph
	 * @throws IllegalArgumentException if weightEpsilon parameter is less than or
	 * equal to 0
	 */
	public WeightedEdgeGraph(double weightEpsilon) {
		super();
		if (weightEpsilon <= 0.0) {
			throw new IllegalArgumentException("Weight epsilon must be greater than 0!");
		}
		this.weightEpsilon = weightEpsilon;
	}
	/**
	 * Initialize graph to have capacity for the given number of vertices and
	 * a default weight epsilon of 0.000001
	 * @param initialCapacity expected number of vertices
	 */
	public WeightedEdgeGraph(int initialCapacity) { 
		this(initialCapacity, GraphConstants.DEFAULT_WEIGHT_EPSILON);
	}
	/**
	 * Initialize graph to have capacity for the given number of vertices and
	 * the specified weight epsilon
	 * @param initialCapacity expected number of vertices
	 * @param weightEpsilon weight epsilon to be used to determine weight equality
	 * in this graph
	 * @throws IllegalArgumentException if weightEpsilon parameter is less than or
	 * equal to 0
	 */
	public WeightedEdgeGraph(int initialCapacity, double weightEpsilon) {
		super(initialCapacity); 
		this.weightEpsilon = weightEpsilon;
	}
	/**
	 * Use the vertices in a collection as the initial vertices in the graph
	 * @param vertices collection of vertices that will make up the initial vertices
	 * of the graph
	 */
	public WeightedEdgeGraph(Collection<T> vertices) { 
		this(vertices, GraphConstants.DEFAULT_WEIGHT_EPSILON);
	}
	/**
	 * Use the vertices in a collection as the initial vertices in the graph and
	 * set weight epsilon to the specified value
	 * @param vertices collection of vertices that will make up the initial vertices
	 * of the graph
	 * @param weightEpsilon weight epsilon to be used to determine weight equality
	 * in this graph
	 * @throws IllegalArgumentException if weightEpsilon parameter is less than or
	 * equal to 0
	 */
	public WeightedEdgeGraph(Collection<T> vertices, double weightEpsilon) { 
		super(vertices); 
		this.weightEpsilon = weightEpsilon;
	}
	/**
	 * Determine whether 2 weights are sufficiently close to be considered
	 * equal in this graph. This will be the case when the absolute value of their
	 * difference is less than the weightEpsilon of the graph.
	 * @param weight1 weight whose equivalence with weight2 is to be tested
	 * @param weight2 weight whose equivalence with weight1 is to be tested
	 * @return true iff the difference in the 2 weights is less than the weightEpsilon
	 * of the graph.
	 */
	public boolean areEqualWeights(double weight1, double weight2) {
		return Math.abs(weight1 - weight2) < weightEpsilon;
	}
	public void setEdgeWeight(T from, T to, double weight) {
		if (from.getGraph() != this) {
			throw new IllegalArgumentException("Vertices do not belong to graph!");
		}
		from.setEdgeWeight(to, weight);
		to.setEdgeWeight(from, weight);
	}
	public double getEdgeWeight(T from, T to) {
		if (from.getGraph() != this) {
			throw new IllegalArgumentException("Vertices do not belong to graph!");
		}
		return from.getEdgeWeight(to);
	}
	public void setEdgeColor(T from, T to, Color color) {
		if (from.getGraph() != this) {
			throw new IllegalArgumentException("Vertices do not belong to graph!");
		}
		from.setEdgeColor(to, color);
		to.setEdgeColor(from, color);
	}
	public Color getEdgeColor(T from, T to) {
		if (from.getGraph() != this) {
			throw new IllegalArgumentException("Vertices do not belong to graph!");
		}
		return from.getEdgeColor(to);
	}
}
