package com.codemelon.graph.edge.types;

import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.interfaces.ColoredEdgeVertex;
import com.codemelon.graph.vertex.interfaces.Vertex;
import com.codemelon.graph.vertex.interfaces.WeightedEdgeVertex;

/**
 * Immutable, undirected edges that maintain a floating point weight.
 * Since the edge is undirected,
 * the distinction between head and tail or from and to is arbitrary.
 * Once the edge has been initialized, it will alway show the same from()
 * and to() vertices, but it is equivalent to an edge object having opposite
 * from() and to() vertices.
 * @author Marshall Farrier
 * @my.created Dec 2, 2012
 * @my.edited Dec 11, 2012
 */
public class WeightedEdge<T extends WeightedEdgeVertex & ColoredEdgeVertex> {
	private DiGraph<? extends Vertex> graph;
	private T from;
	private T to;
	private double weight;
	/**
	 * Construct an immutable weighted edge. The weight of the edge is set when the edge object
	 * is created and is not synchronized with the graph. So, if the edge weight is updated
	 * in the graph, the corresponding weighted edge will not be changed.
	 * @param from tail vertex
	 * @param to head vertex
	 */
	public WeightedEdge(T from, T to) {
		if (from.getGraph() == null || to.getGraph() == null) {
			throw new IllegalArgumentException("Edge must belong to a graph!");
		}
		if (from.getGraph() != to.getGraph()) {
			throw new IllegalArgumentException("Vertices must belong to the same graph!");
		}
		if (!from.containsAdjacency(to)) {
			throw new IllegalArgumentException("Given edge does not exist!");
		}
		graph = from.getGraph();
		this.from = from;
		this.to = to;
		this.weight = from.getEdgeWeight(to);
	}
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will always show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public T from() { return from; }
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will alway show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public T to() { return to; }
	/**
	 * Returns the edge's weight.
	 * @return the edge's weight
	 */
	public double weight() { return weight; }
	/**
	 * Get the graph to which the edge belongs
	 * @return the graph to which the edge belongs
	 */
	public DiGraph<? extends Vertex> getGraph() {
		return graph;
	}
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
		return (from == ((WeightedEdge<?>) o).from && to == ((WeightedEdge<?>) o).to) 
				|| (from == ((WeightedEdge<?>) o).to && to == ((WeightedEdge<?>) o).from);
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