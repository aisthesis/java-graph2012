package com.codemelon.graph.edge;

import com.codemelon.graph.vertex.Vertex;

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
	private Vertex from;
	private Vertex to;
	private double weight;
	public WeightedEdge(Vertex from, Vertex to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will alway show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public Vertex from() { return from; }
	/**
	 * Returns one end of the given edge. Note that the edge is undirected,
	 * so the distinction between head and tail or from and to is arbitrary.
	 * Once the edge has been initialized, it will alway show the same from()
	 * and to() vertices, but it is equivalent to an edge object having opposite
	 * from() and to() vertices.
	 * @return the "from" vertex specified in the constructor
	 */
	public Vertex to() { return to; }
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
}
