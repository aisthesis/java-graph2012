package com.codemelon.graph.edge;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * Immutable, undirected edges that maintain a floating point weight.
 * Since the edge is undirected,
 * the distinction between head and tail or from and to is arbitrary.
 * Once the edge has been initialized, it will alway show the same from()
 * and to() vertices, but it is equivalent to an edge object having opposite
 * from() and to() vertices.
 * @author Marshall Farrier
 * @version Dec 2, 2012
 */
public class WeightedEdge {
	private DiGraph graph;
	private CompleteVertex from;
	private CompleteVertex to;
	private double weight;
	/**
	 * Construct an immutable weighted edge. The weight of the edge is set when the edge object
	 * is created and is not synchronized with the graph. So, if the edge weight is updated
	 * in the graph, the corresponding weighted edge will not be changed.
	 * @param from tail vertex
	 * @param to head vertex
	 * @throws IllegalArgumentException if from or to does not belong to a graph
	 * @throws IllegalArgumentException if from or to do not belong to the same graph
	 * @throws IllegalArgumentException if the graph does not contain the given edge
	 */
	public WeightedEdge(CompleteVertex from, CompleteVertex to) {
		graph = from.getGraph();
		if (graph == null || to.getGraph() == null) {
			throw new IllegalArgumentException("Edge must belong to a graph!");
		}
		if (graph != to.getGraph()) {
			throw new IllegalArgumentException("Vertices must belong to the same graph!");
		}
		if (!from.containsAdjacency(to)) {
			throw new IllegalArgumentException("Given edge does not exist!");
		}
		this.from = from;
		this.to = to;
		this.weight = from.getEdgeWeight(to);
	}
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will alway show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public CompleteVertex from() { return from; }
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will alway show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public CompleteVertex to() { return to; }
	/**
	 * Returns the edge's weight.
	 * @return the edge's weight
	 */
	public double weight() { return weight; }
	/**
	 * Since weighted edges are undirected, they are equal not only if they
	 * have the same tail and head but also if they have opposite tails and heads.
	 * That is, 2 edges e1 and e2 are equal if either of the following conditions holds:
	 * <ol>
	 * <li>e1.from() == e2.from() && e1.to() == e2.to(), <em>or</em></li>
	 * <li>e1.from() == e2.to() && e1.to() == e2.from()</li>
	 * </ol>
	 */
	@Override
	public boolean equals(Object o) {
		return (from == ((WeightedEdge) o).from && to == ((WeightedEdge) o).to) 
				|| (from == ((WeightedEdge) o).to && to == ((WeightedEdge) o).from);
	}
	/**
	 * Overridden so that adding weighted edges to a HashSet will automatically eliminate
	 * duplicates. We want edges that are equal (according to the equals() method) to have
	 * the same hash code.
	 */
	@Override
	public int hashCode() {
		return from.hashCode() ^ to.hashCode();
	}
}